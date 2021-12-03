package com.pipiolo.home.repository.querydsl;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.domain.Home;
import com.pipiolo.home.domain.QHome;
import com.pipiolo.home.dto.HomeResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class HomeRepositoryCustomImpl
        extends QuerydslRepositorySupport
        implements HomeRepositoryCustom
{

    public HomeRepositoryCustomImpl() {
        super(Home.class);
    }

    @Override
    public Page<HomeResponse> findHomeBySearchParams(
            String region,
            SubscriptionType subscriptionType,
            HouseType houseType,
            Pageable pageable
    ) {
        QHome home = QHome.home;
        JPQLQuery<HomeResponse> query = from(home)
                .select(Projections.constructor(
                        HomeResponse.class,
                        home.noticeId,
                        home.houseManagementId,
                        home.houseName,
                        home.constructionCompany,
                        home.region,
                        home.subscriptionType,
                        home.houseType,
                        home.recruitmentDay,
                        home.subscriptionStartDay,
                        home.subscriptionEndDay,
                        home.announcementDay
                ));

        if (region != null && !region.isBlank()) {
            query.where(home.region.eq(region));
        }
        if (subscriptionType != null) {
            query.where(home.subscriptionType.eq(subscriptionType));
        }
        if (houseType != null) {
            query.where(home.houseType.eq(houseType));
        }

        List<HomeResponse> homeList = Optional.ofNullable(getQuerydsl())
                .orElseThrow(() -> new IllegalArgumentException("Spring Data JPA Querydsl 인스턴스 생성 오류"))
                .applyPagination(pageable, query)
                .fetch();

        return new PageImpl<>(homeList, pageable, query.fetchCount());
    }
}

package com.pipiolo.home.repository.querydsl;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.domain.Home;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HomeRepositoryCustom {

    Page<Home> findHomeBySearchParams(
            String region,
            SubscriptionType subscriptionType,
            HouseType houseType,
            Pageable pageable
    );
}

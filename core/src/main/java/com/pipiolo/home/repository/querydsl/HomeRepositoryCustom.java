package com.pipiolo.home.repository.querydsl;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.dto.HomeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HomeRepositoryCustom {

    Page<HomeResponse> findHomeBySearchParams(
            String houseName,
            String region,
            SubscriptionType subscriptionType,
            HouseType houseType,
            Pageable pageable
    );

    List<HomeResponse> findHomesNoOffset(
            Long homeId,
            int pageSize
    );
}

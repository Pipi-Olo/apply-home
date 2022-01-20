package com.pipiolo.home.dto;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import org.springframework.lang.Nullable;

public record HomeSearchRequest(
        @Nullable String region,
        @Nullable SubscriptionType subscriptionType,
        @Nullable HouseType houseType
) {

}

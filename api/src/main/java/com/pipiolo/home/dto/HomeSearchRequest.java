package com.pipiolo.home.dto;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class HomeSearchRequest {

    @Nullable
    private final String region;

    @Nullable
    private final SubscriptionType subscriptionType;

    @Nullable
    private final HouseType houseType;
}

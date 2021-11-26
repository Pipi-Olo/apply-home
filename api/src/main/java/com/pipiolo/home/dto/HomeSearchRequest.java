package com.pipiolo.home.dto;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import lombok.Data;

@Data
public class HomeSearchRequest {
    private String region;
    private SubscriptionType subscriptionType;
    private HouseType houseType;
}

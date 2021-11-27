package com.pipiolo.home.dto;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class HomeRequest {

    private Long noticeId;
    private Long houseManagementId;
    private String houseName;
    private String constructionCompany;
    private String region;
    private SubscriptionType subscriptionType;
    private HouseType houseType;
    private LocalDate recruitmentDay;
    private LocalDate subscriptionStartDay;
    private LocalDate subscriptionEndDay;
    private LocalDate announcementDay;
}

package com.pipiolo.home.dto;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.domain.Home;

import java.time.LocalDate;

public record HomeRequest(
        Long noticeId,
        Long houseManagementId,
        String houseName,
        String constructionCompany,
        String region,
        SubscriptionType subscriptionType,
        HouseType houseType,
        LocalDate recruitmentDay,
        LocalDate subscriptionStartDay,
        LocalDate subscriptionEndDay,
        LocalDate announcementDay
) {
    public Home toEntity() {
        return Home.builder()
                .noticeId(this.noticeId)
                .houseManagementId(this.houseManagementId)
                .houseName(this.houseName)
                .constructionCompany(this.constructionCompany)
                .region(this.region)
                .subscriptionType(this.subscriptionType)
                .houseType(this.houseType)
                .recruitmentDay(this.recruitmentDay)
                .subscriptionStartDay(this.subscriptionStartDay)
                .subscriptionEndDay(this.subscriptionEndDay)
                .announcementDay(this.announcementDay)
                .build();
    }

    public static HomeRequest of(
            Long noticeId,
            Long houseManagementId,
            String houseName,
            String constructionCompany,
            String region,
            SubscriptionType subscriptionType,
            HouseType houseType,
            LocalDate recruitmentDay,
            LocalDate subscriptionStartDay,
            LocalDate subscriptionEndDay,
            LocalDate announcementDay
    ) {
        return new HomeRequest(
                noticeId,
                houseManagementId,
                houseName,
                constructionCompany,
                region,
                subscriptionType,
                houseType,
                recruitmentDay,
                subscriptionStartDay,
                subscriptionEndDay,
                announcementDay
        );
    }

}

package com.pipiolo.home.dto;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.domain.Home;

import java.time.LocalDate;

public record HomeResponse(
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
        public HomeResponse(
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
                this.noticeId = noticeId;
                this.houseManagementId = houseManagementId;
                this.houseName = houseName;
                this.constructionCompany = constructionCompany;
                this.region = region;
                this.subscriptionType = subscriptionType;
                this.houseType = houseType;
                this.recruitmentDay = recruitmentDay;
                this.subscriptionStartDay = subscriptionStartDay;
                this.subscriptionEndDay = subscriptionEndDay;
                this.announcementDay = announcementDay;
        }

        public static HomeResponse from(Home home) {
                return HomeResponse.of(
                        home.getNoticeId(),
                        home.getHouseManagementId(),
                        home.getHouseName(),
                        home.getConstructionCompany(),
                        home.getRegion(),
                        home.getSubscriptionType(),
                        home.getHouseType(),
                        home.getRecruitmentDay(),
                        home.getSubscriptionStartDay(),
                        home.getSubscriptionEndDay(),
                        home.getAnnouncementDay()
                );
        }

        public static HomeResponse of(
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
                return new HomeResponse(
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

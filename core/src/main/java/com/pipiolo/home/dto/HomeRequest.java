package com.pipiolo.home.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HomeRequest {
    //    private String houseManagementId;
    private Long noticeId;
    private String houseName;
    private String constructionCompany;
    private String region;
    //    private LocalDate subscriptionType;
    private LocalDate subscriptionStartDay;
    private LocalDate subscriptionEndDay;
    private LocalDate announcementDay;
//    private String houseType;
}

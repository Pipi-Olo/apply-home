package com.pipiolo.home.dto;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@XmlRootElement(name = "item")
public class HomeDto {

    @XmlElement(name = "housemanageno")
    private String houseManagementId;

    @XmlElement(name = "pblancno")
    private String noticeId;

    public Long getNoticeId() {
        return Long.parseLong(noticeId.trim());
    }

    @XmlElement(name = "housename")
    private String houseName;

    @XmlElement(name = "housedetailsecdnm")
    private String houseType;

    @XmlElement(name = "rentsecdnm")
    private String subscriptionType;

    @XmlElement(name = "bsnsmbynm")
    private String constructionCompany;

    @XmlElement(name = "sido")
    private String region;

    @XmlElement(name = "rceptbgnde")
    private String subscriptionStartDay;

    public LocalDate getSubscriptionStartDay() {
        return LocalDate.parse(subscriptionStartDay,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @XmlElement(name = "rceptendde")
    private String subscriptionEndDay;

    public LocalDate getSubscriptionEndDay() {
        return LocalDate.parse(subscriptionEndDay,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @XmlElement(name = "przwnerpresnatnde")
    private String announcementDay;

    public LocalDate getAnnouncementDay() {
        return LocalDate.parse(announcementDay,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
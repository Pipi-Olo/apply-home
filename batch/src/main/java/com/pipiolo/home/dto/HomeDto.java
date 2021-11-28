package com.pipiolo.home.dto;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.pipiolo.home.constant.HouseType.NATIONAL_HOUSE;
import static com.pipiolo.home.constant.HouseType.PRIVATE_HOUSE;
import static com.pipiolo.home.constant.SubscriptionType.PARCEL;
import static com.pipiolo.home.constant.SubscriptionType.RENT;

@Getter
@ToString
@XmlRootElement(name = "item")
public class HomeDto {

    /* 건설 업체 */
    @XmlElement(name = "bsnsMbyNm")
    private String constructionCompany;

    /* 주택 구분 민영 / 국민 */
    @XmlElement(name = "houseDtlSecdNm")
    private String houseType;

    /* 주택관리번호 */
    @XmlElement(name = "houseManageNo")
    private String houseManagementId;

    public Long getHouseManagementId() {
        return Long.parseLong(houseManagementId);
    }

    /* 주택명 */
    @XmlElement(name = "houseNm")
    private String houseName;

    /* 공고번호 */
    @XmlElement(name = "pblancNo")
    private String noticeId;

    public Long getNoticeId() {
        return Long.parseLong(noticeId);
    }

    /* 당첨자 발표 일*/
    @XmlElement(name = "przwnerPresnatnDe")
    private String announcementDay;

    public LocalDate getAnnouncementDay() {
        return LocalDate.parse(announcementDay,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /* 청약 접수 시작일 */
    @XmlElement(name = "rceptBgnde")
    private String subscriptionStartDay;

    public LocalDate getSubscriptionStartDay() {
        return LocalDate.parse(subscriptionStartDay,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /* 청약 접수 종료일 */
    @XmlElement(name = "rceptEndde")
    private String subscriptionEndDay;

    public LocalDate getSubscriptionEndDay() {
        return LocalDate.parse(subscriptionEndDay,
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /* 모집 공고일 */
    @XmlElement(name = "rcritPblancDe")
    private String recruitmentDay;

    public LocalDate getRecruitmentDay() {
        return LocalDate.parse(recruitmentDay,
                DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    /* 분양 / 임대 */
    @XmlElement(name = "rentSecdNm")
    private String subscriptionType;

    /* 공급 지역 */
    @XmlElement(name = "sido")
    private String region;

    public HomeRequest toRequest() {
        HomeRequest request = new HomeRequest();
        request.setNoticeId(this.getNoticeId());
        request.setHouseManagementId(this.getHouseManagementId());
        request.setHouseName(this.getHouseName());
        request.setConstructionCompany(this.getConstructionCompany());
        request.setRegion(this.getRegion());

        if (this.getSubscriptionType() == "분양") {
            request.setSubscriptionType(PARCEL);}
        else
            request.setSubscriptionType(RENT);

        if (this.getHouseType() == "국민") {
            request.setHouseType(NATIONAL_HOUSE);}
        else {
            request.setHouseType(PRIVATE_HOUSE);}

        request.setRecruitmentDay(this.getRecruitmentDay());
        request.setSubscriptionStartDay(this.getSubscriptionStartDay());
        request.setSubscriptionEndDay(this.getSubscriptionEndDay());
        request.setAnnouncementDay(this.getAnnouncementDay());

        return request;
    }
}
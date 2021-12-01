package com.pipiolo.home.dto;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
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

    public HouseType getHouseType() {
        if (this.houseType == "국민") {
            return NATIONAL_HOUSE;
        }
        else {
            return PRIVATE_HOUSE;
        }
    }

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

    public SubscriptionType getSubscriptionType() {
        if (this.subscriptionType == "분양") {
            return PARCEL;
        }
        else {
            return RENT;
        }
    }

    /* 공급 지역 */
    @XmlElement(name = "sido")
    private String region;

    public HomeRequest toRequest() {
        return HomeRequest.of(
                this.getNoticeId(),
                this.getHouseManagementId(),
                this.getHouseName(),
                this.getConstructionCompany(),
                this.getRegion(),
                this.getSubscriptionType(),
                this.getHouseType(),
                this.getRecruitmentDay(),
                this.getSubscriptionStartDay(),
                this.getSubscriptionEndDay(),
                this.getAnnouncementDay()
        );
    }
}
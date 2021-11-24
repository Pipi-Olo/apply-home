package com.pipiolo.home.domain;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.dto.HomeRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long noticeId;                      /* 공고번호 */

    @Column(nullable = false)
    private Long houseManagementId;             /* 주택관리번호 */

    @Column(nullable = false)
    private String houseName;                   /* 주택명 */

    @Column(nullable = false)
    private String constructionCompany;         /* 건설 업체 */

    @Column(nullable = false)
    private String region;                      /* 공급 지역 */

    @Column(nullable = false)
    private SubscriptionType subscriptionType;  /* 분양 / 임대 */

    @Column(nullable = false)
    private HouseType houseType;                /* 주택 구분(국민주택 / 민영주택) */

    @Column(nullable = false)
    private LocalDate recruitmentDay;           /* 모집 공고일 */

    @Column(nullable = false)
    private LocalDate subscriptionStartDay;     /* 청약 접수 시작일 */

    @Column(nullable = false)
    private LocalDate subscriptionEndDay;       /* 청약 접수 종료일 */

    @Column(nullable = false)
    private LocalDate announcementDay;          /* 당첨자 발표 일*/

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Home from(HomeRequest dto) {
        Home home = new Home();
        home.setNoticeId(dto.getNoticeId());
        home.setHouseManagementId(dto.getHouseManagementId());
        home.setHouseName(dto.getHouseName());
        home.setConstructionCompany(dto.getConstructionCompany());
        home.setRegion(dto.getRegion());
        home.setSubscriptionType(dto.getSubscriptionType());
        home.setHouseType(dto.getHouseType());
        home.setRecruitmentDay(dto.getRecruitmentDay());
        home.setSubscriptionStartDay(dto.getSubscriptionStartDay());
        home.setSubscriptionEndDay(dto.getSubscriptionEndDay());
        home.setAnnouncementDay(dto.getAnnouncementDay());

        return home;
    }

    public void update(HomeRequest dto) {
        this.houseName = dto.getHouseName();
        this.constructionCompany = dto.getConstructionCompany();
        this.region = dto.getRegion();
        this.subscriptionType = dto.getSubscriptionType();
        this.houseType = dto.getHouseType();
        this.recruitmentDay = dto.getRecruitmentDay();
        this.subscriptionStartDay =dto.getSubscriptionStartDay();
        this.subscriptionEndDay = dto.getSubscriptionEndDay();
        this.announcementDay = dto.getAnnouncementDay();
    }
}

package com.pipiolo.home.domain;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.dto.HomeRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
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

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private SubscriptionType subscriptionType;  /* 분양 / 임대 */

    @Enumerated(value = EnumType.STRING)
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

    @Builder
    public Home(
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

    public void update(HomeRequest request) {
        this.houseName = request.houseName();
        this.constructionCompany = request.constructionCompany();
        this.region = request.region();
        this.subscriptionType = request.subscriptionType();
        this.houseType = request.houseType();
        this.recruitmentDay = request.recruitmentDay();
        this.subscriptionStartDay =request.subscriptionStartDay();
        this.subscriptionEndDay = request.subscriptionEndDay();
        this.announcementDay = request.announcementDay();
    }
}

package com.pipiolo.home.domain;

import com.pipiolo.home.dto.HomeRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(EnableJpaAuditing.class)
@Entity
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long noticeId;

    @Column
    private String houseName;

    @Column
    private String constructionCompany;

    @Column
    private String region;

    @Column
    private LocalDate subscriptionStartDay;

    @Column
    private LocalDate subscriptionEndDay;

    @Column
    private LocalDate announcementDay;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static Home from(HomeRequest dto) {
        Home home = new Home();
        home.setNoticeId(dto.getNoticeId());
        home.setHouseName(dto.getHouseName());
        home.setConstructionCompany(dto.getConstructionCompany());
        home.setRegion(dto.getRegion());
        home.setSubscriptionStartDay(dto.getSubscriptionStartDay());
        home.setSubscriptionEndDay(dto.getSubscriptionEndDay());
        home.setAnnouncementDay(dto.getAnnouncementDay());

        return home;
    }

    public void update(HomeRequest dto) {
        this.houseName = dto.getHouseName();
        this.constructionCompany = dto.getConstructionCompany();
        this.region = dto.getRegion();
        this.subscriptionStartDay =dto.getSubscriptionStartDay();
        this.subscriptionEndDay = dto.getSubscriptionEndDay();
        this.announcementDay = dto.getAnnouncementDay();
    }
}

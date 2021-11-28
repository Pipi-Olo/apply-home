package com.pipiolo.home.repository;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.domain.Home;
import com.pipiolo.home.dto.HomeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

import static com.pipiolo.home.constant.HouseType.PRIVATE_HOUSE;
import static com.pipiolo.home.constant.SubscriptionType.PARCEL;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[CORE] - Repository")
@DataJpaTest
class HomeRepositoryTest {

    private final HomeRepository homeRepository;

    HomeRepositoryTest(@Autowired HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @BeforeEach
    void before() {
        homeRepository.save(Home.from(createHomeRequest()));
    }

    @DisplayName("[Querydsl] 검색 파라미터와 함께 조회하면, 조건에 맞는 데이터를 페이징 처리하여 반환한다.")
    @Test
    void givenSearchParams_whenSearching_thenReturnsHomePage() {
        // Given
        String region = "경남";
        SubscriptionType subscriptionType = PARCEL;
        HouseType houseType = PRIVATE_HOUSE;

        // When
        Page<Home> actual = homeRepository.findHomeBySearchParams(
                region,
                subscriptionType,
                houseType,
                PageRequest.of(0, 5));

        // Then
        assertThat(actual.getTotalPages()).isEqualTo(1);
        assertThat(actual.getContent().get(0))
                .hasFieldOrPropertyWithValue("region", region)
                .hasFieldOrPropertyWithValue("subscriptionType", subscriptionType)
                .hasFieldOrPropertyWithValue("houseType", houseType);
    }

    @DisplayName("[Querydsl] 검색 파라미터 중 일부가 없어도, 조건에 맞는 데이터를 페이징 처리하여 반환한다.")
    @Test
    void test() {
        // Given
        String region = "경남";
        SubscriptionType subscriptionType = PARCEL;

        // When
        Page<Home> actual = homeRepository.findHomeBySearchParams(
                region,
                subscriptionType,
                null,
                PageRequest.of(0, 5));

        // Then
        assertThat(actual.getTotalPages()).isEqualTo(1);
        assertThat(actual.getContent().get(0))
                .hasFieldOrPropertyWithValue("region", region)
                .hasFieldOrPropertyWithValue("subscriptionType", subscriptionType);
    }

    @DisplayName("[Querydsl] 검색 파라미터가 없다면, 모든 홈 데이터를 반환합니다.")
    @Test
    void givenNothing_whenSearching_thenReturnsEntireHome() {
        // Given

        // When
        Page<Home> actual = homeRepository.findHomeBySearchParams(
                null,
                null,
                null,
                PageRequest.of(0, 5));

        // Then
        assertThat(actual.getContent().size())
                .isEqualTo(homeRepository.findAll().size());
    }

    private HomeRequest createHomeRequest() {
        HomeRequest request = new HomeRequest();
        request.setNoticeId(2021000222L);
        request.setHouseManagementId(2021000222L);
        request.setHouseName("가포금호어울림 10년공공임대주택(리츠)");
        request.setConstructionCompany("한국토지주택공사 경남지역본부");
        request.setRegion("경남");
        request.setSubscriptionType(PARCEL);
        request.setHouseType(PRIVATE_HOUSE);
        request.setRecruitmentDay(LocalDate.now());
        request.setSubscriptionStartDay(LocalDate.now());
        request.setSubscriptionEndDay(LocalDate.now().plusMonths(3L));
        request.setAnnouncementDay(LocalDate.now().plusMonths(6L));

        return request;
    }
}
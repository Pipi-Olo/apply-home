package com.pipiolo.home.repository;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.dto.HomeRequest;
import com.pipiolo.home.dto.HomeResponse;
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

@DisplayName("[CORE][Repository][Home]")
@DataJpaTest
class HomeRepositoryTest {

    private final HomeRepository homeRepository;

    HomeRepositoryTest(@Autowired HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @BeforeEach
    void before() {
        homeRepository.save(createHomeRequest().toEntity());
    }

    @DisplayName("[Querydsl] 검색 파라미터와 함께 조회하면, 조건에 맞는 데이터를 페이징 처리하여 반환한다.")
    @Test
    void givenSearchParams_whenSearching_thenReturnsHomePage() {
        // Given
        String houseName = "가포금호어울림 10년공공임대주택(리츠)";
        String region = "경남";
        SubscriptionType subscriptionType = PARCEL;
        HouseType houseType = PRIVATE_HOUSE;

        // When
        Page<HomeResponse> actual = homeRepository.findHomeBySearchParams(
                houseName,
                region,
                subscriptionType,
                houseType,
                PageRequest.of(0, 5));

        // Then
        assertThat(actual.getTotalPages()).isEqualTo(1);
        assertThat(actual.getContent().get(0))
                .hasFieldOrPropertyWithValue("houseName", houseName)
                .hasFieldOrPropertyWithValue("region", region)
                .hasFieldOrPropertyWithValue("subscriptionType", subscriptionType)
                .hasFieldOrPropertyWithValue("houseType", houseType);
    }

    @DisplayName("[Querydsl] 검색 파라미터 중 일부가 없어도, 조건에 맞는 데이터를 페이징 처리하여 반환한다.")
    @Test
    void givenNullParam_whenSearching_thenReturnsHomePage() {
        // Given
        String region = "경남";
        SubscriptionType subscriptionType = PARCEL;

        // When
        Page<HomeResponse> actual = homeRepository.findHomeBySearchParams(
                null,
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
        Page<HomeResponse> actual = homeRepository.findHomeBySearchParams(
                null,
                null,
                null,
                null,
                PageRequest.of(0, 5));

        // Then
        assertThat(actual.getContent().size())
                .isEqualTo(homeRepository.findAll().size());
    }

    private HomeRequest createHomeRequest() {
        return HomeRequest.of(
                2021000222L,
                2021000222L,
                "가포금호어울림 10년공공임대주택(리츠)",
                "한국토지주택공사 경남지역본부",
                "경남",
                PARCEL,
                PRIVATE_HOUSE,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now().plusMonths(3L),
                LocalDate.now().plusMonths(6L)
        );
    }
}
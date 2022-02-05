package com.pipiolo.home.controller.api;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.dto.HomeRequest;
import com.pipiolo.home.dto.HomeResponse;
import com.pipiolo.home.dto.HomeSearchRequest;
import com.pipiolo.home.repository.HomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static com.pipiolo.home.constant.HouseType.NATIONAL_HOUSE;
import static com.pipiolo.home.constant.HouseType.PRIVATE_HOUSE;
import static com.pipiolo.home.constant.SubscriptionType.PARCEL;
import static com.pipiolo.home.constant.SubscriptionType.RENT;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[API][API Controller][Home]")
@SpringBootTest
class HomeAPIControllerTest {

    private final HomeRepository homeRepository;
    private final HomeAPIController homeAPIController;

    public HomeAPIControllerTest(
            @Autowired HomeAPIController homeAPIController,
            @Autowired HomeRepository homeRepository
    ) {
        this.homeAPIController = homeAPIController;
        this.homeRepository = homeRepository;
    }

    @BeforeEach
    void before() {
        String[] regions = {"경기", "서울"};
        for (String region : regions) {
            homeRepository.save(createHomeRequest(region, PARCEL, PRIVATE_HOUSE).toEntity());
            homeRepository.save(createHomeRequest(region, PARCEL, NATIONAL_HOUSE).toEntity());
            homeRepository.save(createHomeRequest(region, RENT, PRIVATE_HOUSE).toEntity());
            homeRepository.save(createHomeRequest(region, RENT, NATIONAL_HOUSE).toEntity());
        }
    }

    @DisplayName("NULL 조건으로 검색하면 모든 결과를 반환한다.")
    @Test
    void givenNullConditionRequest_whenSearching_thenReturnsAll() {
        // Given
        Pageable pageable = PageRequest.of(0, 20);
        HomeSearchRequest request = new HomeSearchRequest(null, null, null);

        // When
        List<HomeResponse> responses = homeAPIController.findHomesBySearchParams(request, pageable);

        // Then
        assertThat(responses).hasSize(8);
    }

    private HomeRequest createHomeRequest(String region, SubscriptionType subscriptionType, HouseType houseType) {
        return HomeRequest.of(
                2021000222L,
                2021000222L,
                "가포금호어울림 10년공공임대주택(리츠)",
                "한국토지주택공사 경남지역본부",
                region,
                subscriptionType,
                houseType,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now().plusMonths(3L),
                LocalDate.now().plusMonths(6L)
        );
    }
}
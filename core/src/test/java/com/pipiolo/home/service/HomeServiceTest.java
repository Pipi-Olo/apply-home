package com.pipiolo.home.service;

import com.pipiolo.home.dto.HomeRequest;
import com.pipiolo.home.dto.HomeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.pipiolo.home.constant.HouseType.PRIVATE_HOUSE;
import static com.pipiolo.home.constant.SubscriptionType.PARCEL;

@DisplayName("[CORE] - Service")
@SpringBootTest
class HomeServiceTest {

    private final HomeService homeService;

    public HomeServiceTest(@Autowired HomeService homeService) {
        this.homeService = homeService;
    }

    @DisplayName("새로운 집이 주어졌을 때, 저장한다.")
    @Test
    void givenHome_whenUpsertNonExistingHome_thenReturnsInsertedHome() {
        // Given
        HomeRequest request = createHomeRequest("경남");

        // When
        HomeResponse actual = homeService.upsert(request);

        // Then
        Assertions.assertEquals(request.noticeId(), actual.noticeId());
    }

    @DisplayName("기존과 중복된 집이 주어졌을 때, 기존 값으 수정한다. - NoticeId를 통해 중복 여부 확인한다.")
    @Test
    void givenHome_whenUpsertExistingHome_thenReturnsUpdatedHome() {
        // Given
        HomeRequest request = createHomeRequest("경남");

        // When
        homeService.upsert(request);
        HomeResponse actual = homeService.upsert(createHomeRequest("강남"));


        // Then
        Assertions.assertEquals(request.noticeId(), actual.noticeId());
        Assertions.assertNotEquals(request.region(), actual.region());
    }



    private HomeRequest createHomeRequest(String region) {
        return HomeRequest.of(
                2021000222L,
                2021000222L,
                "가포금호어울림 10년공공임대주택(리츠)",
                "한국토지주택공사 경남지역본부",
                region, // "경남"
                PARCEL,
                PRIVATE_HOUSE,
                LocalDate.now(),
                LocalDate.now(),
                LocalDate.now().plusMonths(3L),
                LocalDate.now().plusMonths(6L)
        );
    }
}
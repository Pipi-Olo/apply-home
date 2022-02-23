package com.pipiolo.home.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[API][Controller][Profile]")
// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileControllerTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate;

    ProfileControllerTest(@Autowired TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @DisplayName("[NGINX]")
//     @Test
    public void givenProfiles_whenChangingProfile_thenReturnsRealProfile() {
        // Given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");


        ProfileController controller = new ProfileController(env);

        // When
        String profile = controller.profile();

        // Then
        assertThat(profile)
                .isEqualTo(expectedProfile);
    }

    @DisplayName("[NGINX]")
//     @Test
    public void givenProfile_whenRealProfileNonExists_thenReturnsFirst() {
        // Given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // When
        String profile = controller.profile();

        // Then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @DisplayName("[NGINX] 실행중인 프로파일이 없으면 디폴트 프로파일이 조회된다.")
//     @Test
    public void givenNothing_whenActiveProfileNonExists_thenReturnsDefaultProfile() {
        // Given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        // When
        String profile = controller.profile();

        // Then
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @DisplayName("[NGINX] 인증 없이도 /profile 을 호출할 수 있다. ")
//     @Test
    void givenNothing_whenRequestProfileWithNoAuthenticated_thenReturnsDefault() {
        // Given
        String expected = "default";

        // When
        ResponseEntity<String> response = restTemplate.getForEntity("/profile", String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}

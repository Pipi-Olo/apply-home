package com.pipiolo.home.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[API][Controller][Profile]")
class ProfileControllerTest {

    @DisplayName("[NGINX]")
    @Test
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
    @Test
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
    @Test
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
}
package com.pipiolo.home.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileControllerTest {

    @Test
    public void givenProfilesWhenChangingProfileThenReturnsRealProfile() {
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
}
package com.pipiolo.home.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscriptionType {

    PARCEL("분양"),
    RENT("임대")
    ;

    private final String type;
}

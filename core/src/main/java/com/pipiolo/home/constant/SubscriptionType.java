package com.pipiolo.home.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubscriptionType {

    PARCEL("λΆμ"),
    RENT("μλ")
    ;

    private final String type;
}

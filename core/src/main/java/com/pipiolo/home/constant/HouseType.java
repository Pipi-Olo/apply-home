package com.pipiolo.home.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HouseType {

    NATIONAL_HOUSE("국민 주택"),
    PRIVATE_HOUSE("민영 주택")
    ;

    private final String type;
}

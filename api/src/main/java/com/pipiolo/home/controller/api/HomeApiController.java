package com.pipiolo.home.controller.api;

import com.pipiolo.home.domain.Home;
import com.pipiolo.home.dto.HomeSearchRequest;
import com.pipiolo.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeApiController {

    private final HomeService homeService;

    @GetMapping("/home")
    public Page<Home> findHomesBySearchParams(
            @Valid @RequestBody HomeSearchRequest request,
            Pageable pageable
    ) {
        return homeService.findHomeBySearchParams(
                request.getRegion(),
                request.getSubscriptionType(),
                request.getHouseType(),
                pageable
        );
    }
}

package com.pipiolo.home.controller.api;

import com.pipiolo.home.domain.Home;
import com.pipiolo.home.dto.HomeSearchRequest;
import com.pipiolo.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeApiController {

    private final HomeService homeService;

    @GetMapping("/home")
    public Page<Home> findHomesBySearchParams(
            @RequestBody HomeSearchRequest request
    ) {
        return homeService.findHomeBySearchParams(
                request.getRegion(),
                request.getSubscriptionType(),
                request.getHouseType(),
                PageRequest.of(0, 5)
        );
    }
}

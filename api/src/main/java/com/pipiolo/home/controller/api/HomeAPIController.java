package com.pipiolo.home.controller.api;

import com.pipiolo.home.dto.HomeResponse;
import com.pipiolo.home.dto.HomeSearchRequest;
import com.pipiolo.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeAPIController {

    private final HomeService homeService;

    @GetMapping("/home")
    public List<HomeResponse> findHomesBySearchParams(
            @Valid @ModelAttribute HomeSearchRequest request,
            Pageable pageable
    ) {
        return homeService.findHomeBySearchParams(
                request.region(),
                request.subscriptionType(),
                request.houseType(),
                pageable
        ).getContent();
    }
}

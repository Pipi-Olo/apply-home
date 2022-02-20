package com.pipiolo.home.controller.api;

import com.pipiolo.home.dto.HomeResponse;
import com.pipiolo.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeAPIController {

    private final HomeService homeService;

//    @GetMapping("/home")
//    @ResponseBody
//    public List<HomeResponse> getHomes() {
//        return homeService.getHomes(Pageable.ofSize(10));
//    }

    @GetMapping("/home")
    public List<HomeResponse> findHomesNoOffset(Long homeId) {
        return homeService.findHomesNoOffset(homeId, 10);
    }
}

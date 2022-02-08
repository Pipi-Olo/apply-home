package com.pipiolo.home.controller.view;

import com.pipiolo.home.dto.HomeResponse;
import com.pipiolo.home.dto.HomeSearchRequest;
import com.pipiolo.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HomeService homeService;

    @GetMapping( value = {"/", "/index"})
    public ModelAndView index(
            @Valid @ModelAttribute HomeSearchRequest request,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Map<String, Object> map = new HashMap<>();
        Page<HomeResponse> homeList = homeService.findHomeBySearchParams(
                request.houseName(),
                request.region(),
                request.subscriptionType(),
                request.houseType(),
                pageable
        );
        
        map.put("homes", homeList);
        return new ModelAndView("index", map);
    }
}

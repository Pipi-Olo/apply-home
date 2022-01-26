package com.pipiolo.home.controller.view;

import com.pipiolo.home.dto.HomeResponse;
import com.pipiolo.home.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HomeService homeService;

    @GetMapping("/")
    public ModelAndView index() {
        Map<String, Object> map = new HashMap<>();
        List<HomeResponse> homeList = homeService.getHomes().subList(0, 2);
        map.put("homes", homeList);

        return new ModelAndView("/index", map);
    }

    @GetMapping("/fragments")
    public String fragments() {
        return "/fragments";
    }
}

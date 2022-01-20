package com.pipiolo.home.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/index")
    public ModelAndView subscribe() {
        return new ModelAndView("user/index");
    }
}

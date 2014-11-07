package com.r3systems.guacamoletest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class DemoController {

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "/demo";
    }

}
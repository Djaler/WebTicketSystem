package com.moracle.webticketsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("greeting", "World");
        return "welcome";
    }

    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    public String sayHelloAgain(@PathVariable String url, ModelMap model) {
        model.addAttribute("greeting", url);
        return "welcome";
    }
}

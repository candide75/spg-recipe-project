package com.example.sfgrecipeproject.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage() {
        log.debug("Getting Index Page");
        return "index";
    }
}

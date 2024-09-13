package org.scoula.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@Configuration
public class HomeController {
    @GetMapping("/")
    public String home() {
        log.info("=====> HomeController /");
        return "index"; //view의 이름
    }

};

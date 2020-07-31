package com.hyunro.bookmark.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexApiController {

    @GetMapping("/")
    public String index() {
        return "/index.html";
    }
}

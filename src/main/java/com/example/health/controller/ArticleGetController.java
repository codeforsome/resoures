package com.example.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value ={"/article/{id}"} )
public class ArticleGetController {
    @GetMapping("")
    public String getarticlehtml(){
        return "html/information";
    }
}

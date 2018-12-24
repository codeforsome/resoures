package com.example.health.controller;

import org.springframework.stereotype.Controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = {"/user/{id}/article"})
public class ArticleUpdateController {
    @GetMapping("/get")
    public String getHtml(@PathVariable("id") int id,ModelMap modelMap){
        modelMap.addAttribute("id",id);
        return "html/articleupdate";
    }

}

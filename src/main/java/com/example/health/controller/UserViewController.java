package com.example.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
@RequestMapping(value = "/user/{id}")
public class UserViewController {

    @ResponseBody
    @GetMapping("")
    public  BigInteger get(@PathVariable BigInteger id){
        return id;
    }
}

package com.rong.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ Author ：rongyong
 * @ Date ：Created in 22:58 2019/3/15
 */
@Controller
@RequestMapping("/demo")
public class controller
{
    @GetMapping("/get")
    @ResponseBody
    public String get()
    {
        return "aaa";
    }
}

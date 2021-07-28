package com.xl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XLong
 * @create 2021-07-16 16:34
 */
@Controller
public class MainController {

    @RequestMapping(value = "main")
    public String toMain() {
        return "main";
    }

    @RequestMapping(value = "top.action")
    public String toTop() {
        return "top";
    }

    @RequestMapping(value = "left.action")
    public String toLeft() {
        return "left";
    }

    @RequestMapping(value = "right.action")
    public String toRight() {
        return "right";
    }

}

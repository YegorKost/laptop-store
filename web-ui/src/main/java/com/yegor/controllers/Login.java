package com.yegor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by YegorKost on 23.03.2017.
 */
@Controller
public class Login {
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

}

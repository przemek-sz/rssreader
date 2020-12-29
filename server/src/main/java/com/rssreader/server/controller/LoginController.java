package com.rssreader.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    @PostMapping("/login")
    @ResponseBody
    public Principal login(Principal principal){

        return principal;
    }
}

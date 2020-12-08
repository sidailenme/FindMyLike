package com.yalta.controllers;

import com.yalta.services.impl.AuthVksServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthVksServiceImpl authVksService;

    @GetMapping("/get")
    public String go() {
        return "redirect:" + authVksService.getAuthURL();
    }


    @GetMapping("/")
    public void authVk(@RequestParam(value = "code", required = false) String code) {
        System.out.println(code);
        authVksService.auth(code);
    }



}

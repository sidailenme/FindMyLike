package com.yalta.controllers;

import com.yalta.services.impl.FriendsServiceImpl;
import com.yalta.services.impl.PostService;
import com.yalta.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final FriendsServiceImpl friendsService;
    private final PostService postService;

    @GetMapping("/get")
    public String go() {
        return "redirect:" + authService.createAuthURL();
    }


    @GetMapping("/")
    public void authVk(@RequestParam(value = "code", required = false) String code) {
//        List<String> friendsList = friendsService.takeFriendsIds(authService.takeAccessToken(code));
        postService.takePostList(authService.takeAccessToken(code), "19923086");
    }
}

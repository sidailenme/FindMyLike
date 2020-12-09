package com.yalta.controllers;

import com.yalta.config.Session;
import com.yalta.model.Post;
import com.yalta.model.User;
import com.yalta.services.impl.FriendServiceImpl;
import com.yalta.services.impl.PostServiceImpl;
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
    private final Session s;
    private final FriendServiceImpl friendService;
    private final PostServiceImpl postService;

    @GetMapping("/get")
    public String go(@RequestParam(value = "id", required = false) String targetUserId) {
        s.setId(targetUserId);
        return "redirect:" + authService.createAuthURL();
    }


    @GetMapping("/")
    public String authVk(@RequestParam(value = "code", required = false) String code) {
        s.setToken(authService.takeAccessToken(code));
        friendService.takeFriendsIds(s);
        for (User user : s.getFriendsList()) {
            postService.takePostsIds(s, user);
        }
        for (User friend : s.getFriendsList()) {

            for (Post post : friend.getPostList()) {
                System.out.println(friend.getFullName() + " " + post.getItemId());
            }

        }

        return "redirect:vk.com";
    }
}

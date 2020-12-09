package com.yalta.controllers;

import com.yalta.config.Session;
import com.yalta.model.Post;
import com.yalta.model.User;
import com.yalta.services.impl.FriendServiceImpl;
import com.yalta.services.impl.LikeServiceImpl;
import com.yalta.services.impl.PostServiceImpl;
import com.yalta.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final Session s;
    private final FriendServiceImpl friendService;
    private final PostServiceImpl postService;
    private final LikeServiceImpl likeService;

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
            for (Post post : user.getPostList()) {
                likeService.findLikeOnPost(s, user, post);
            }
        }

        List<String> likedPosts = new ArrayList<>();

        List<User> friendsList = s.getFriendsList();
        for (User user : friendsList) {
            for (Post post : user.getPostList()) {
                if (post.isLiked()) {
                    likedPosts.add(user.getFullName() + " " + post.getItemId() + " is liked");
                }
            }
        }
        System.out.println("like is -------------");
        likedPosts.stream().forEach(System.out::println);



        return "redirect:vk.com";
    }
}

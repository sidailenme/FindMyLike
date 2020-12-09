package com.yalta.controllers;

import com.yalta.config.TargetUser;
import com.yalta.services.tst.FriendsServiceImpl;
import com.yalta.services.tst.LikeService;
import com.yalta.services.tst.PostService;
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
    private final LikeService likeService;
    private final TargetUser targetUser;

    @GetMapping("/get")
    public String go(@RequestParam(value = "id", required = false) String targetUserId) {
        targetUser.setId(targetUserId);
        return "redirect:" + authService.createAuthURL();
    }


    @GetMapping("/")
    public void authVk(@RequestParam(value = "code", required = false) String code) {
        String token = authService.takeAccessToken(code);           //todo
//        List<String> friendsList = friendsService.takeFriendsIds(authService.takeAccessToken(code));
        List<String> userPosts = postService.takePostList(token, "137750708");

        for (String userPost : userPosts) {
            likeService.takeLikeList(token, "137750708", userPost, targetUser.getId());
        }

    }
}

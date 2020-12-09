package com.yalta.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yalta.config.Session;
import com.yalta.model.Post;
import com.yalta.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void findLikeOnPost(Session s, User user, Post post) {
        Thread.sleep(300);
        String url = "https://api.vk.com/method/"
                + "likes.isLiked?user_id=" + s.getId()
                + "&type=post"
                + "&owner_id=" + user.getId()
                + "&item_id=" + post.getItemId()
                + "&access_token=" + s.getToken()
                + "&v=5.126";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Map map = objectMapper.readValue(forEntity.getBody(), Map.class);
        Map m1 = (Map) map.get("response");
        Integer like = (Integer) m1.get("liked");
        if (like == 1) {
            post.setLiked(true);
            System.out.println(user.getFullName() + ": post #" + post.getItemId() + " is liked");
        }
    }


}

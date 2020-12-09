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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void takePostsIds(Session s, User user) {
        Thread.sleep(350);
        List<Post> postList = user.getPostList();
        String url = "https://api.vk.com/method/"
                + "wall.get?owner_id=" + user.getId()
                + "&count=20"
                + "&access_token=" + s.getToken()
                + "&v=5.126";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Map map = objectMapper.readValue(forEntity.getBody(), Map.class);
        Map mapResponse = (Map) map.get("response");
//        List<LinkedHashMap> itemList = (List) mapResponse.get("items");

        List<LinkedHashMap> itemList = null;
        try {
            itemList = (List) mapResponse.get("items");
        } catch (NullPointerException e) {
            //trough
        }
        if (itemList != null) {
            for (LinkedHashMap lhm : itemList) {
                Post post = new Post();
                post.setItemId(String.valueOf(lhm.get("id")));
                postList.add(post);
            }
        }


    }

}

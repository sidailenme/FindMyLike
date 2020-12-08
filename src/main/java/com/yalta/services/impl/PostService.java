package com.yalta.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final ObjectMapper objectMapper;


    @SneakyThrows
    public List<String> takePostList(String accessToken, String userId) {
        List<String> list = new ArrayList<>();
        String url = "https://api.vk.com/method/"
                + "wall.get?owner_id=" + userId
                + "&count=100"
                + "&access_token=" + accessToken
                + "&v=5.126";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Map map = objectMapper.readValue(forEntity.getBody(), Map.class);
        Map m1 = (Map) map.get("response");
        List m2 = (List) m1.get("items");

        for (int i = 0; i < m2.size(); i++) {
            Map m3 = (Map) m2.get(i);
            int postId = (int) m3.get("id");
            list.add(String.valueOf(postId));
        }

        list.stream().forEach(System.out::println);
        return null;
    }


}

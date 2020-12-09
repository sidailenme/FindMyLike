package com.yalta.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yalta.config.Session;
import com.yalta.model.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void takeFriendsIds(Session s) {
        List<User> friendsList = s.getFriendsList();
        String url = "https://api.vk.com/method/"
                + "friends.get?user_id=166441826"
                + "&fields=nickname"
                + "&count=20"
                + "&access_token=" + s.getToken()
                + "&v=5.126";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.getForEntity(url, String.class);
        //----------
        Map map = objectMapper.readValue(entity.getBody(), Map.class);
        Map mapResponse = (Map) map.get("response");
        List<LinkedHashMap> listItems = (List) mapResponse.get("items");
        for (LinkedHashMap lhm : listItems) {
            User user = new User();
            user.setId(String.valueOf(lhm.get("id")));
            user.setFullName(lhm.get("first_name") + " " + lhm.get("last_name"));
            friendsList.add(user);
        }
    }
}

package com.yalta.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@Setter
public class TestService {

    @SneakyThrows
    public void go(String response) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> template = restTemplate.getForEntity(response, String.class);
//        System.out.println(template.getBody());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(template.getBody(), Map.class);
        String url = "https://api.vk.com/method/"
                + "friends.get?user_id=166441826"
                + "&access_token=" + map.get("access_token")
                + "&v=5.126";
        RestTemplate restTemplate1 = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate1.getForEntity(url, String.class);
//        System.out.println(forEntity.getBody());
    }

}

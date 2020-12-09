//package com.yalta.services.tst;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.*;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class FriendsServiceImpl {
//
//    private final ObjectMapper objectMapper;
//
//    @SneakyThrows
//    public List<String> takeFriendsIds(String accessToken) {
//        String url = "https://api.vk.com/method/"
//                + "friends.get?user_id=166441826"
//                + "&access_token=" + accessToken
//                + "&v=5.126";
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
//        Map map = objectMapper.readValue(forEntity.getBody(), Map.class);
//        Map m1 = (Map) map.get("response");
//        List<Integer> list = (List<Integer>) m1.get("items");
//        List<String> stringList = list.stream().map(Object::toString).collect(Collectors.toList());
//        stringList.forEach(System.out::println);
//        return stringList;
//    }
//}

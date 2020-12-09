package com.yalta.services.tst;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final ObjectMapper objectMapper;

    @SneakyThrows
    public List<String> takeLikeList(String accessToken, String targetUser, String targetItem, String mainUser) {
        String url = "https://api.vk.com/method/"
                + "likes.isLiked?user_id=" + mainUser
                + "&type=post"
                + "&owner_id=" + targetUser
                + "&item_id=" + targetItem
                + "&access_token=" + accessToken
                + "&v=5.126";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        Map map = objectMapper.readValue(forEntity.getBody(), Map.class);
        Map m1 = (Map) map.get("response");
        Integer x = (Integer) m1.get("liked");
        if (x.equals(1)) {
            System.out.println("лайк на запись " + targetItem);
        }

        return null;
    }
}

package com.yalta.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yalta.services.interfaces.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Data
@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix="spring.security.vk-app")
public class AuthServiceImpl implements AuthService {

    private String clientId;
    private String clientSecret;
    private String responseType;
    private String redirectURL;
    private String authURL;
    private String tokenURL;
    private String display;
    private String version;

    private final ObjectMapper objectMapper;

    @Override
    public String createAuthURL() {
        return authURL + "?"
                + "client_id=" + clientId
                + "&redirect_uri=" + redirectURL
                + "&display=" + display
                + "&response_type=" + responseType
                + "&v=" + version;
    }

    public String createAccessTokenURL(String code) {
        return tokenURL + "?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectURL
                + "&code=" + code;
    }

    @SneakyThrows
    @Override
    public String takeAccessToken(String code) {
        String accessTokenURL = createAccessTokenURL(code);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(accessTokenURL, String.class);
        Map<String, String> map = objectMapper.readValue(responseEntity.getBody(), Map.class);
        return map.get("access_token");
    }
}

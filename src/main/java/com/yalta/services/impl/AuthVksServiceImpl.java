package com.yalta.services.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Data
@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix="spring.security.vk-app")
public class AuthVksServiceImpl {

    private String clientId;
    private String clientSecret;
    private String responseType;
    private String redirectURL;
    private String authURL;
    private String tokenURL;
    private String display;
    private String version;

    private final TestService testService; // todo delete

    public String getAuthURL() {
        return authURL + "?"
                + "client_id=" + clientId
                + "&redirect_uri=" + redirectURL
                + "&display=" + display
                + "&response_type=" + responseType
                + "&v=" + version;
    }

    public String getAccessToken(String code) {
        return tokenURL + "?"
                + "client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectURL
                + "&code=" + code;
    }

    public void auth(String code) {
        String response = getAccessToken(code);
        testService.go(response);
    }

}

package com.yalta.services.interfaces;

public interface AuthService {

    String createAuthURL();

    String takeAccessToken(String code);
}

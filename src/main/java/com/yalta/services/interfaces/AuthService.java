package com.yalta.services.interfaces;

public interface AuthService {

    String getAuthURL();

    String getAccessToken(String code);
}

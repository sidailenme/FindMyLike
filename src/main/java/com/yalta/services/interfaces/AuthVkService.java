package com.yalta.services.interfaces;

public interface AuthVkService {
    String getAuthURL();
    void auth(String code);
}

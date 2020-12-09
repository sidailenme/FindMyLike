package com.yalta.config;

import com.yalta.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Session {

    private String id;

    private String token;

    private List<User> friendsList = new ArrayList<>();

}

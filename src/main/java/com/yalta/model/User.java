package com.yalta.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {

    private String id;

    private String fullName;

    private List<Post> postList = new ArrayList<>();
}

package com.example.blinddate.entity;

import lombok.Data;

@Data
public class FriendRequest {
    private Integer id;
    private String request_people_id;
    private String receive_people_id;
    private String status;
}

package org.com.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String email;
}

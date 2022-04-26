package org.com.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String id;
    private String username;
    private String sex;
    private String phone;
    private String address;
    private String subject;
}

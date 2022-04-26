package org.com.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private String studentName;
    private String address;
    private String phone;
    private String sex;
    private Integer age;
    private String studentNo;
}

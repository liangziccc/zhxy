package com.etoak.zhxy.jcxx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data@NoArgsConstructor@AllArgsConstructor
public class Student {
    private int id;
    private  String name;
    private String stuno;
    private int age;
    private String phone;
    private  String classes;
    private int collegeid;
    private int xiid;
    private int majorid;
    private Date startdate;
    private int flag1;
}

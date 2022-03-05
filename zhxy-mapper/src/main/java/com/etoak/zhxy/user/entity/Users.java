package com.etoak.zhxy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor
public class Users {
    private int  id;
    private String uname;
    private String pwd;
    private String phone;
    private List<Roles> roles;
    private String token;
}

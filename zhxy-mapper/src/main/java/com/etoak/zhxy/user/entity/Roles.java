package com.etoak.zhxy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Roles {
    private int id;
    private String name;
    private List<Menus> menus;
}

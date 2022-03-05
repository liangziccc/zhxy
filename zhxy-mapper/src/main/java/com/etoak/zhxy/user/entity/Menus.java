package com.etoak.zhxy.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Menus {
    private int id;
    private String text;
    private String href;
    private int pid;
    private int sort;
    private int hide;

    private List<Menus> nodes;

}

package com.etoak.zhxy.jcxx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Dept {
    private int id;
    private String name;
    private String code;
    private String dtype;
    private int pid;
    private int schid;

    private List<Dept> children;
}

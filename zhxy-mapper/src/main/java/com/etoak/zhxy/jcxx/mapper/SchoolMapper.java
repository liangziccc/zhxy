package com.etoak.zhxy.jcxx.mapper;

import com.etoak.zhxy.jcxx.entity.School;

import java.util.List;

public interface SchoolMapper {
    public List<School> queryAll();

    School querySchById(int id);
}

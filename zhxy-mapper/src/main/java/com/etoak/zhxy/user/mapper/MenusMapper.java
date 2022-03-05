package com.etoak.zhxy.user.mapper;

import com.etoak.zhxy.user.entity.Menus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenusMapper {
    public List<Menus> queryMenus();

    List<Menus> queryMenusById(@Param("userid") int id);
}

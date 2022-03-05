package com.etoak.zhxy.user.mapper;

import com.etoak.zhxy.user.entity.Roles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface RolesMapper {

    public List<Roles> queryRolesWithMenus();

    void deleteRoleMenuByRoleId(Integer roleid);

    void addRoleMenu(Integer roleid, List<Integer> menuids);
}

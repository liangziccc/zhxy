package com.etoak.zhxy.user.service;

import com.etoak.zhxy.user.entity.Menus;
import com.etoak.zhxy.user.entity.Users;
import com.etoak.zhxy.user.vo.RoleMenuVo;
import com.etoak.zhxy.user.vo.UserRoleVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, Object> queryAll();
    public void updateRole(UserRoleVo vos);
    public void updateMenus(RoleMenuVo vos);
    public List<Map<String, Object>> query1();
    public Users login(String uname, String password);
    public List<Menus> queryMenuByUserId(int id);
    public void setChildren(Menus t, List<Menus> menus);




}

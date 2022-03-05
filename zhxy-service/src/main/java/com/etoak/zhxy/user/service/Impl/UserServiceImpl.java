package com.etoak.zhxy.user.service.Impl;

import com.etoak.zhxy.user.entity.Menus;
import com.etoak.zhxy.user.entity.Users;
import com.etoak.zhxy.user.mapper.MenusMapper;
import com.etoak.zhxy.user.mapper.RolesMapper;
import com.etoak.zhxy.user.mapper.UsersMapper;
import com.etoak.zhxy.user.service.UserService;
import com.etoak.zhxy.user.vo.RoleMenuVo;
import com.etoak.zhxy.user.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private MenusMapper menusMapper;



    @Override
    public Map<String, Object> queryAll() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("us",usersMapper.queryUsersWithRoles());
        data.put("rs",rolesMapper.queryRolesWithMenus());
        data.put("ms",menusMapper.queryMenus());
        return data;

    }


//    @Transactional
    @Override
    public void updateRole(UserRoleVo vos) {
        //先删除之前的，再添加新的
        usersMapper.deleteUserRoleByUserId(vos.getUserid());

        usersMapper.addUserRole(vos.getUserid(),vos.getRoleids());

    }


//    @Transactional
@Override
    public void updateMenus(RoleMenuVo vos) {
        rolesMapper.deleteRoleMenuByRoleId(vos.getRoleid());
        rolesMapper.addRoleMenu(vos.getRoleid(),vos.getMenuids());
    }

    @Override
    public List<Map<String, Object>> query1() {
       return usersMapper.query1 ();

    }

    @Override
    public Users login(String uname, String password) {
       return usersMapper.queryUserByNameAndPwd(uname,password);
    }

    @Override
    public List<Menus> queryMenuByUserId(int id) {
        return menusMapper.queryMenusById(id);
    }

    @Override
    public void setChildren(Menus t, List<Menus> menus) {
        //存放子菜单
        List<Menus> childs = new ArrayList<>();
        for (Menus m:menus){
            if (m.getPid()==t.getId()){
                childs.add(m);
            }

        }
        //给顶级菜单加一级菜单
        t.setNodes(childs);
        //如果子菜单不为空，为子菜单寻找自菜单，循环递归
       if (childs.size()>0) {
           for (Menus c : childs) {
               this.setChildren(c, menus);
           }
       }
    }
}

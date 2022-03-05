package com.etoak.zhxy.user.mapper;

import com.etoak.zhxy.user.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UsersMapper {
    //查询用户，携带角色
    public List<Users> queryUsersWithRoles();


    void deleteUserRoleByUserId(Integer userid);

    void addUserRole(Integer userid, List<Integer> roleids);


    List<Map<String, Object>> query1();

    Users queryUserByNameAndPwd(@Param("uname") String uname, @Param("pwd") String password);

}

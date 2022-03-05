package com.etoak.zhxy.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor
public class UserRoleVo {
    private Integer userid;
    private List<Integer>  roleids;


}

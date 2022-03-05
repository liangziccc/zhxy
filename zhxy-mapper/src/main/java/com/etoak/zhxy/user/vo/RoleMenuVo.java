package com.etoak.zhxy.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data@NoArgsConstructor@AllArgsConstructor
public class RoleMenuVo {
    private Integer roleid;
    private List<Integer> menuids;

}

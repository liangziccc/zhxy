package com.etoak.zhxy.jcxx.mapper;

import com.etoak.zhxy.jcxx.entity.Classes;
import com.etoak.zhxy.jcxx.entity.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    //1 根据学校查学院
    public List<Dept> queryCollegeBySch(@Param("schid") int schid);

    //2 根据学院查 系 专业

    public List<Dept> queryByPid(@Param("pid") int pid);

    //3 根据专业查询班
    public List<Classes> queryClasses(@Param("deptno") int pid);

}

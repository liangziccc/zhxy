package com.etoak.zhxy.jcxx.service;

import com.etoak.zhxy.jcxx.entity.Classes;
import com.etoak.zhxy.jcxx.entity.Dept;
import com.etoak.zhxy.jcxx.entity.School;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {

    public List<School> queryAll();


    List<Dept> queryCollegeBySch(int schid);

    List<Dept> queryByPid(int pid);

    List<Classes> queryClasses(int pid);

    School querySchoolById(int schid);
}

package com.etoak.zhxy.jcxx.service.Impl;

import com.etoak.zhxy.jcxx.entity.Classes;
import com.etoak.zhxy.jcxx.entity.Dept;
import com.etoak.zhxy.jcxx.entity.School;
import com.etoak.zhxy.jcxx.mapper.DeptMapper;
import com.etoak.zhxy.jcxx.mapper.SchoolMapper;
import com.etoak.zhxy.jcxx.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private DeptMapper deptMapper;


    @Override
    public List<School> queryAll() {
        List<School> schools = schoolMapper.queryAll();
        return schools;
    }

    @Override
    public List<Dept> queryCollegeBySch(int schid) {
        List<Dept> data = deptMapper.queryCollegeBySch(schid);
        return data;
    }

    @Override
    public List<Dept> queryByPid(int pid) {
       return deptMapper.queryByPid(pid);
    }

    @Override
    public List<Classes> queryClasses(int pid) {
        return deptMapper.queryClasses(pid);
    }

    @Override
    public School querySchoolById(int schid) {
        return  schoolMapper.querySchById(schid);
    }


}

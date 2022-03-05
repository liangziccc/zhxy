package com.etoak.zhxy.jbxc.controller;

import com.etoak.zhxy.jcxx.entity.Classes;
import com.etoak.zhxy.jcxx.entity.Dept;
import com.etoak.zhxy.jcxx.entity.School;
import com.etoak.zhxy.jcxx.service.DeptService;
import com.etoak.zhxy.jcxx.service.Impl.DeptServiceImpl;
import com.etoak.zhxy.utils.JsonResponse;
import com.etoak.zhxy.utils.ResultStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptServiceImpl deptService;


    @PostMapping("/queryAll")
    public JsonResponse queryAll() {
        return ResultStatus.suc(deptService.queryAll());
    }

    @PostMapping("/queryCollegeBySch")
    public JsonResponse queryCollegeBySch(int schid) {
        List<Dept> data = deptService.queryCollegeBySch(schid);
        return ResultStatus.suc(data);

    }

    @PostMapping("/queryByPid")
    public JsonResponse queryByPid(int pid) {
        return ResultStatus.suc(deptService.queryByPid(pid));
    }

    @PostMapping("/queryClasses")
    public JsonResponse queryClasses(int pid) {
        return ResultStatus.suc(deptService.queryClasses(pid));
    }

    @PostMapping("/queryBytree")
    public JsonResponse queryBytree(int schid) {
        School sch = deptService.querySchoolById(schid);
        //根节点
        List<Dept> result = new ArrayList<>();

        Dept dept = new Dept();
        dept.setName(sch.getName());
        //给学校设置学院
        List<Dept> colleges = deptService.queryByPid(schid);
        //给学院添加系
        for (Dept college : colleges) {
            //获得每一个学院的系
            List<Dept> xis = deptService.queryByPid(college.getId());
            if (!ObjectUtils.isEmpty(xis)) {
                for (Dept xi : xis) {
                    List<Dept> majors = deptService.queryByPid(xi.getId());
                    if (!ObjectUtils.isEmpty(majors)) {
                        for (Dept major : majors) {
                            List<Classes> classes = deptService.queryClasses(major.getId());
                            if (!ObjectUtils.isEmpty(classes)) {
                                //查询到班
                                List<Dept> bans = new ArrayList<>();
                                for (Classes c : classes) {
                                    Dept deptClass = new Dept();
                                    BeanUtils.copyProperties(c, deptClass);
                                    bans.add(deptClass);
                                }
                                //给专业添加班
                                major.setChildren(bans);
                            }
                        }
                        xi.setChildren(majors);
                    }
                }
            }
            college.setChildren(xis);
        }
        dept.setChildren(colleges);
        result.add(dept);
        return ResultStatus.suc(result);


    }
}

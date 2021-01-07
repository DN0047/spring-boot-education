package com.ws.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.EduCourse;
import com.ws.serviceedu.entity.EduTeacher;
import com.ws.serviceedu.service.EduCourseCollectService;
import com.ws.serviceedu.service.EduCourseService;
import com.ws.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("teacherPage")
@Api(description = "首页讲师")
public class TeacherPageController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @RequestMapping("pageTeacher/{page}/{limit}")
    @ApiOperation("首页讲师分页")
    public R1 pageTeacher(@PathVariable Long page,
                          @PathVariable Long limit){


        Page<EduTeacher> objectPage = new Page<>(page,limit);
        HashMap<String,Object> teacher= eduTeacherService.pageTeacher(objectPage);

        return R1.ok().data("teacherMap",teacher);

    }

    @RequestMapping("slecteTeacher/{teacherId}")
    @ApiOperation("首页讲师详情查询")
    public R1 slecteTeacher(@PathVariable Long teacherId){


        EduTeacher byId = eduTeacherService.getById(teacherId);
        //查询讲师课程
       /* QueryWrapper<EduCourse> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("teacher",teacherId);
        EduCourse one = eduCourseService.getOne(objectQueryWrapper)*/;


        return R1.ok().data("teacher",byId);

    }

}

package com.ws.serviceedu.controller.info;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.EduCourse;
import com.ws.serviceedu.entity.chapter.CoursePage;
import com.ws.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("courseController")
@CrossOrigin
@Api(description = "条件查询全部课程")
public class CourseController {

    @Autowired
    private EduCourseService eduCourseService;
@RequestMapping("getCoursePage/{page}/{limit}")
@ApiOperation("前台课程条件查询")
    public R1 getCoursePage(@PathVariable long page,
                            @PathVariable long limit,
                            CoursePage coursePage){
    Page<EduCourse> objectPage = new Page<>(page, limit);
    eduCourseService.getCoursePage(objectPage,coursePage);
    return R1.ok().data("getCoursePage",objectPage);

    }
}

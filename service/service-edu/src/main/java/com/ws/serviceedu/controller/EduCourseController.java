package com.ws.serviceedu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.EduCourse;
import com.ws.serviceedu.entity.excel.CourseInfo;
import com.ws.serviceedu.exception.MyException;
import com.ws.serviceedu.service.EduCourseCollectService;
import com.ws.serviceedu.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.POST;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ws
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/eduservice/course")
@Api(description = "添加课程基础信息")
@CrossOrigin
public class EduCourseController {
@Autowired
    private EduCourseService eduCourseService;
@PostMapping("addCourseInfo")
public R1 addCourseInfo(@RequestBody CourseInfo courseInfo){
    String id= null;

    try {
          id=    eduCourseService.addCourseInfo(courseInfo);
    } catch (MyException e) {
        e.printStackTrace();
        return R1.error().setMessage("添加失败");
    }
    return R1.ok().data("couresId", id);
}



    @GetMapping("getAll/{courseId1}")
    public R1 getCourse(@PathVariable String courseId1){
        EduCourse byId = eduCourseService.getById(courseId1);
        return R1.ok().data("getCourseInfo", byId);
    }

    @ApiOperation(value = "最终发布课程")
    @PostMapping ("updateStatus/{courseId1}")
    public R1 updateStatus(@PathVariable String courseId1){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId1);
        eduCourse.setStatus("Normal");
        boolean b = eduCourseService.updateById(eduCourse);
        if (b){
            return R1.ok();
        }else {
            return R1.error();
        }

    }
    @ApiOperation("分页查询所有课程")
    @GetMapping("getAllCourse/{page}/{limit}")
    public R1 getAllCourse(@PathVariable Long page,
                           @PathVariable Long limit,
                           @RequestBody EduCourse eduCourse){
        Page<EduCourse> objectPage = new Page<>(page,limit);
        eduCourseService.getAllCourse(objectPage,eduCourse);
        List<EduCourse> records = objectPage.getRecords();
        long total = objectPage.getTotal();

        return R1.ok().data("records",records).data("total",total);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("delectCoures/{courseId}")
    public R1 delectCoures(@PathVariable String courseId){
        boolean c= false;
        try {
            c = eduCourseService.delectCourse(courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return R1.error();

        }
        return R1.ok();

    }



    @ApiOperation("查询最热门的课程")
    @GetMapping ("selecthotCoures")
    public R1 selecthotCoures(){
    List<EduCourse> list = eduCourseService.selecthotCoures();
        return R1.ok().data("selecthotCoures",list);

    }

}


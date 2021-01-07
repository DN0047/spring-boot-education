package com.ws.serviceedu.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.EduTeacher;
import com.ws.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ws
 * @since 2020-12-17
 */
@CrossOrigin //跨域
@RestController

@Api(description = "教师增删改查接口")
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {
@Autowired
 private    EduTeacherService teacherService;
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }
    @ApiOperation(value = "查询所有讲师")
    @GetMapping
    public R1 list() {



        List<EduTeacher> list = teacherService.list(null);
        if (StringUtils.isEmpty(list)){

            return R1.error();
        }else {

            return R1.ok().data("items", list);
        }




    }

   /* @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R1 pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        teacherService.page(pageParam, null);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R1.ok().data("total", total).data("rows", records);
    }*/

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("{page}/{limit}")
    public R1 pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                    EduTeacher teacherQuery) {

        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R1.ok().data("total", total).data("rows", records);

    }

    @PostMapping("add")
    @ApiOperation(value = "添加教师")
    public R1 getById(@ApiParam(name="eduTeacher",value = "讲师",required = true)

            @RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        if (save){
            return R1.ok();

        }else
        return R1.error();
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R1 updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        teacher.setId(id);
        boolean b = teacherService.updateById(teacher);
        if (b){

            return R1.ok();

        }else
            return R1.error();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R1 getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        EduTeacher teacher = teacherService.getById(id);
        return R1.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("delect/{id}")
    public R1 deleteTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){

        boolean teacher = teacherService.removeById(id);
        return R1.ok();
    }

    @ApiOperation("查询最热门的老师")
    @GetMapping("selectTeacher")
    public R1 selectTeacher(){


      List<EduTeacher> list= teacherService.selectHotTeacher();

      return R1.ok().data("selectHotTeacher",list);
    }

}


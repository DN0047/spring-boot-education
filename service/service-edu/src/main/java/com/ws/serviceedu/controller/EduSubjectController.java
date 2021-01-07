package com.ws.serviceedu.controller;


import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.excel.OneSubject;
import com.ws.serviceedu.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author ws
 * @since 2020-12-21
 */
@RestController
@RequestMapping("/serviceedu/edu-subject")
@CrossOrigin
@Api(description = "二级分类表")
public class EduSubjectController {


    @Autowired
  private   EduSubjectService eduSubjectService;
@ApiOperation(value = "上传二级分类表")
    @PostMapping("addSubject")
    public R1 addSubject(MultipartFile file){
        //上传excel文件




    eduSubjectService.saveSubject(file,eduSubjectService);
        return R1.ok();
    }

    @ApiOperation(value = "查询树")
    @GetMapping("getAllSubject")
    public R1 getAllSubject(){



        List<OneSubject> list =eduSubjectService.getAllSubject();
        return R1.ok().data("list",list);
    }
}


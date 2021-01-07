package com.ws.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.EduChapter;
import com.ws.serviceedu.entity.EduVideo;
import com.ws.serviceedu.entity.chapter.Chapter;
import com.ws.serviceedu.service.EduChapterService;
import com.ws.serviceedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author ws
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/serviceedu/edu-chapter")
@Api(description = "章节接口")
@CrossOrigin
public class EduChapterController {
@Autowired
    private EduChapterService eduChapterService;

@Autowired
private EduVideoService eduVideoService;

@ApiOperation(value = "查询章节")
@GetMapping("getChapterVideo/{couresId}")
public R1 getChapterVideo(

        @PathVariable String couresId){
    System.out.println(couresId);
  List<Chapter> list= eduChapterService.getChapterVideoBy(couresId);

  return R1.ok().data("allChapterVideo",list);
}

    @ApiOperation(value = "增加章节")
    @GetMapping("addChapterVideo")
    public R1 addChapterVideo(@RequestBody EduChapter eduChapter){
        boolean save = eduChapterService.save(eduChapter);
        if (save){
            return R1.ok();

        }else {
            return R1.error();
        }

    }


    @ApiOperation(value = "根据章节id删除章节")
    @GetMapping("delectChapterVideo/{couresId}")
    public R1 delectChapterVideo(@PathVariable String couresId){
    //查询子章节
        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        qw.eq("coures_Id",couresId);
        List<EduVideo> list = eduVideoService.list(qw);
        boolean b=false;
        if (list!=null){//如果子章节有数据先删除子章节
            eduVideoService.removeByIds(list);

        }else {
             b = eduChapterService.removeById(couresId);

        }

        if (b){
            return R1.ok();

        }else {
            return R1.error();
        }

    }


    @ApiOperation(value = "根据章节id查询章节")
    @GetMapping("selectChapterVideo/{couresId}")
    public R1 selectChapterVideo(@PathVariable String couresId) {

        EduChapter byId = eduChapterService.getById(couresId);
        if (byId!=null){
            return R1.ok();

        }else {
            return R1.error();
        }

    }
}


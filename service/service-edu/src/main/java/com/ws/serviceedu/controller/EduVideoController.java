package com.ws.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.EduVideo;
import com.ws.serviceedu.fclin.OpenFeign;
import com.ws.serviceedu.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author ws
 * @since 2020-12-22
 */
@RestController
@RequestMapping("/serviceedu/edu-video")
@CrossOrigin
@Api(description = "章节小结CURD")
public class EduVideoController {

@Autowired
private OpenFeign openFeign;
    @Autowired
    private EduVideoService eduVideoService;
    @DeleteMapping("{id}")
    @ApiOperation(value = "根据id删除小结")
    public R1 delectVideo(@PathVariable String id){

        //根据小结ID删除阿里云端视频
        QueryWrapper<EduVideo> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("id",id);
        objectQueryWrapper.select("video_original_name");

        EduVideo one = eduVideoService.getOne(objectQueryWrapper);

        String videoSourceId = one.getVideoSourceId();
        ArrayList<String> objects = new ArrayList<>();
        objects.add(videoSourceId);
        openFeign.delectPlay(objects);

        boolean b = eduVideoService.removeById(id);
        return R1.ok();
    }


    @DeleteMapping("addVideo")
    @ApiOperation(value = "增加小结")
    public R1 addVideo(@RequestBody EduVideo eduVideo){



        boolean b = eduVideoService.save(eduVideo);

        if (b){
            return R1.ok();
        }else {

            return R1.ok();
        }

    }

}


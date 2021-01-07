package com.ws.vod.controller;

import com.ws.serviceedu.R1;
import com.ws.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
@Api(description = "上传视频")
public class VodController {



@Autowired
private VodService vodService;
    @PostMapping("uploadAlyiVideo")
    @ApiOperation(value = "上传视频")
    public R1 uploadAlyiVideo(MultipartFile file){
String vid=vodService.addVod(file);
        return R1.ok().data("vid",vid);
    }
}

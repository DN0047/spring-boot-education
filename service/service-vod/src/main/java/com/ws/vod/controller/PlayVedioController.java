package com.ws.vod.controller;

import com.ws.serviceedu.R1;
import com.ws.vod.service.VodPlayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playVedio/vdo")
@CrossOrigin
@Api(description = "视频点播")
public class PlayVedioController {

    @Autowired
    private VodPlayService vodPlayService;
    @GetMapping("play/{VodId}")
    public R1 playInfoVod(@PathVariable String vodId){
     String  id=   vodPlayService.selectPlay(vodId);

     return  R1.ok();
    }


   @ApiOperation(value = "批量删除视频")
    @PostMapping("delectPlay")
    public R1 delectPlay(@RequestBody List<String> VodId){
     R1 r=    vodPlayService.delectPlay(VodId);

        return  r;
    }


    @ApiOperation(value = "删除视频")
    @PostMapping("delectPlay/{VodId}")
    public R1 dePlay(@PathVariable String VodId){
        R1 r=    vodPlayService.dePlay(VodId);

        return  r;
    }

}

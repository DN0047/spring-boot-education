package com.ws.servicenote.controller;

import com.ws.serviceedu.R1;
import com.ws.servicenote.service.NoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@Api(description = "短信验证")
@RequestMapping("/note-user")
public class NoteSmscontroller {

    @Autowired
    private NoteService noteService;




    @ApiOperation(value = "短信注册接口")
    @GetMapping ("noteSms/{pp}")
    public R1 noteSms(@PathVariable String pp){


      boolean a=  noteService.noteSms(pp);

      if (a){
          return R1.ok();
      }else {
          return R1.error();
      }


    }


}

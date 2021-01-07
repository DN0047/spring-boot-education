package com.ws.serviceedu.controller;

import com.ws.serviceedu.R1;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class LogeEduTeacgerController {

@PostMapping("login")
    public R1 login(){

       return R1.ok().data("token","欢迎大老板");
    }

    @GetMapping("info")
    public R1 info(){

        return R1.ok().data("roles","欢迎大老板").data("name","我是你大哥").data("avatar","cccc");
    }
}

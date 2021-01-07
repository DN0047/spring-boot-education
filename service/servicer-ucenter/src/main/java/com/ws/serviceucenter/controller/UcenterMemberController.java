package com.ws.serviceucenter.controller;


import com.ws.UcenterAppliction;
import com.ws.serviceedu.R1;
import com.ws.serviceucenter.entity.UcenterMember;
import com.ws.serviceucenter.service.UcenterMemberService;
import com.ws.serviceucenter.vo.RegisterUserBean;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author ws
 * @since 2020-12-27
 */
@RestController
@RequestMapping("/serviceucenter/ucenter-member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    UcenterMemberService ucenterMemberService;


    @ApiOperation("用户注册")
    @PostMapping("registerUser")
    public R1 registerUser(@RequestBody RegisterUserBean registerUserBean){
        if (StringUtils.isBlank(registerUserBean.getMobile())&&
                StringUtils.isBlank(registerUserBean.getNickname())&&
                StringUtils.isBlank(registerUserBean.getPassword())
        ){

            return R1.error().message("注册失败");

        }


      boolean r = ucenterMemberService.registerUser(registerUserBean);

        if (r){
            return R1.ok().message("注册成功");
        }
        return R1.ok().message("注册失败");

    }

    /**
     * <p>
     *     登录接口
     * </p>
     * @param registerUserBean
     * @return
     */
    @PostMapping("loginUser")
    @ApiOperation("登录")
    public R1 loginUser(@RequestBody RegisterUserBean registerUserBean){
        if (StringUtils.isBlank(registerUserBean.getMobile())&&
                StringUtils.isBlank(registerUserBean.getNickname())&&
                StringUtils.isBlank(registerUserBean.getPassword())
        ){

            return R1.error().message("登录失败，请填写账号或密码");

        }

     String token=   ucenterMemberService.loginUser(registerUserBean);

        if (!StringUtils.isBlank(token)){

            return R1.ok().message("登录成功");
        }else {
            return R1.error().message("登陆失败");
        }
    }


}


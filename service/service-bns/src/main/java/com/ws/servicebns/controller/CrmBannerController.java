package com.ws.servicebns.controller;


import com.ws.servicebns.entity.CrmBanner;
import com.ws.servicebns.service.CrmBannerService;
import com.ws.serviceedu.R1;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author ws
 * @since 2020-12-26
 */
@RestController
@RequestMapping("/servicebns/crm-banner")
@Api(description = "图片流动")
public class CrmBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation(value = "查询流动图")
    @GetMapping("selectBanner")
    public R1 selectBanner(){
        List<CrmBanner> list = crmBannerService.listBanner();
        if (list!=null){
            return R1.ok().data("banner",list);
        }else {
            return R1.error().message("查询失败");
        }

    }
}


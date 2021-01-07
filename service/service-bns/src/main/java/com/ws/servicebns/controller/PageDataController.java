package com.ws.servicebns.controller;

import com.ws.servicebns.open.OpenFine;
import com.ws.serviceedu.R1;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@CrossOrigin
@Api(description = "页面返回数据结合集")
public class PageDataController {

    @Autowired
    private OpenFine openFine;

    /**
     *
     * @return
     */
    public R1 pageData(){
        R1 r1 = openFine.selectTeacher();
        R1 r11 = openFine.selecthotCoures();

        return R1.ok().data("selectTeacher",r1.getData().get("selectTeacher")).data("selecthotCoures",r11.getData().get("selecthotCoures"));

    }


}

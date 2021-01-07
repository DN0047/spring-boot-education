package com.ws.servicebns.open;

import com.ws.serviceedu.R1;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@FeignClient(name = "service-bns")
public interface OpenFine {

    @ApiOperation("查询最热门的老师")
    @GetMapping("/serviceedu/edu-teacher/selectTeacher")
    public R1 selectTeacher();


    @ApiOperation("查询最热门的课程")
    @GetMapping ("/eduservice/course/electhotCoures")
    public R1 selecthotCoures();

}

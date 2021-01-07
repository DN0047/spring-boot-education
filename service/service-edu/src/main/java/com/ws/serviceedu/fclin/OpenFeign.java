package com.ws.serviceedu.fclin;

import com.ws.serviceedu.R1;
import com.ws.serviceedu.fclin.imlp.OpenFeignImlp;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "service-vod")
@Component
public interface OpenFeign {


    @ApiOperation(value = "删除视频")
    @PostMapping("/playVedio/vdo/delectPlay")
    public R1 delectPlay(@RequestBody List<String> VodId);

}

package com.ws.serviceucenter.openfein;

import com.ws.serviceedu.R1;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient
@Component
public interface Open {


    @ApiOperation(value = "短信注册接口")
    @GetMapping("/note-user/noteSms/{pp}")
    public R1 noteSms(@PathVariable("pp") String pp);
}

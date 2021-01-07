package com.ws.serviceedu.fclin.imlp;

import com.ws.serviceedu.R1;
import com.ws.serviceedu.fclin.OpenFeign;

import java.util.List;

public class OpenFeignImlp implements OpenFeign {
    @Override
    public R1 delectPlay(List<String> listID) {
        return R1.error().message("调用失败");
    }
}

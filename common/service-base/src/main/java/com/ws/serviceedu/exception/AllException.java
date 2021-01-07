package com.ws.serviceedu.exception;

import com.ws.serviceedu.R1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AllException {
    @ExceptionHandler(value = MyException.class)
    public R1 error(MyException e) {
        log.error(e.toString());
        e.printStackTrace();
        return R1.error().message("w");
    }


}
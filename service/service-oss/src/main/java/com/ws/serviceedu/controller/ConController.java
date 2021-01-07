package com.ws.serviceedu.controller;

import com.ws.serviceedu.R1;
import com.ws.serviceedu.service.OssService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Api(description = "上传文件到oss")
public class ConController {
@Autowired
private OssService ossService;

@PostMapping
    public R1 uploadOssFile(MultipartFile file){
    String s = ossService.uploadfILEaVATAR(file);
    return R1.ok().data("url",s);
}

}

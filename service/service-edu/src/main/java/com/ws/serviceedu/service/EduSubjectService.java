package com.ws.serviceedu.service;

import com.ws.serviceedu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.serviceedu.entity.excel.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ws
 * @since 2020-12-21
 */
public interface EduSubjectService extends IService<EduSubject> {
    //  添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();
}

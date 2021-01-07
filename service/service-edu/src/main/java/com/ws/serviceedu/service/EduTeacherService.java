package com.ws.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ws
 * @since 2020-12-17
 */

public interface EduTeacherService extends IService<EduTeacher> {
   void pageQuery(Page page,EduTeacher eduTeacher);

    List<EduTeacher> selectHotTeacher();

    HashMap<String,Object> pageTeacher(Page<EduTeacher> objectPage);


}

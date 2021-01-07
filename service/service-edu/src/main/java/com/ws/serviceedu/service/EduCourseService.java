package com.ws.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.serviceedu.entity.chapter.CoursePage;
import com.ws.serviceedu.entity.excel.CourseInfo;
import com.ws.serviceedu.exception.MyException;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ws
 * @since 2020-12-22
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfo courseInfo) throws MyException;

    void getAllCourse(Page<EduCourse> objectPage, EduCourse eduCourse);

    boolean delectCourse(String courseId) throws Exception;

    List<EduCourse> selecthotCoures();

    void getCoursePage(Page<EduCourse> objectPage, CoursePage coursePage);
}

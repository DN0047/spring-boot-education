package com.ws.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.R1;
import com.ws.serviceedu.entity.EduChapter;
import com.ws.serviceedu.entity.EduCourse;
import com.ws.serviceedu.entity.EduCourseDescription;
import com.ws.serviceedu.entity.EduVideo;
import com.ws.serviceedu.entity.chapter.CoursePage;
import com.ws.serviceedu.entity.excel.CourseInfo;
import com.ws.serviceedu.exception.MyException;
import com.ws.serviceedu.fclin.OpenFeign;
import com.ws.serviceedu.mapper.EduCourseMapper;
import com.ws.serviceedu.service.EduChapterService;
import com.ws.serviceedu.service.EduCourseDescriptionService;
import com.ws.serviceedu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.serviceedu.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author ws
 * @since 2020-12-22
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private OpenFeign openFeign;

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Override
    public String addCourseInfo(CourseInfo courseInfo) throws MyException {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
if (insert<=0){
    throw new MyException();
}

        String id = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(id);
        eduCourseDescription.setDescription(courseInfo.getDescription());
        boolean save = eduCourseDescriptionService.save(eduCourseDescription);
        return id;

}

    @Override
    public void getAllCourse(Page<EduCourse> objectPage, EduCourse eduCourse) {
        QueryWrapper<EduCourse> objectQueryWrapper = new QueryWrapper<>();
        if (eduCourse==null){
            baseMapper.selectPage(objectPage,null);

        }else {
            if (eduCourse.getTitle()!=null){
                objectQueryWrapper.like("title",eduCourse.getTitle());
            }
            if (eduCourse.getPrice()!=null){
                objectQueryWrapper.eq("price",eduCourse.getPrice()!=null);
            }
            baseMapper.selectPage(objectPage,objectQueryWrapper);
        }


    }

    @Override
   /* @Transactional(rollbackFor = Exception.class)*/
    public boolean delectCourse(String courseId) throws Exception {

        //删除视频
     /*   QueryWrapper<EduVideo> vod = new QueryWrapper<>();
        vod.eq("course_id", courseId);
        vod.select("video_source_id");
        List<EduVideo> list1 = eduVideoService.list(vod);
        ArrayList<String> objects = new ArrayList<>();
        for (EduVideo ed : list1) {
            String videoSourceId = ed.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)) {
                objects.add(videoSourceId);
            }

        }
        R1 r1 = openFeign.delectPlay(objects);

        if (r1.getCode() == 20001) {

            throw new Exception();
        }*/
        //删除小结
        QueryWrapper<EduVideo> objectQueryWrapper1 = new QueryWrapper<>();
        objectQueryWrapper1.eq("course_id", courseId);

        boolean remove1 = eduVideoService.remove(objectQueryWrapper1);

        //删除简介
        boolean k = eduCourseDescriptionService.removeById(courseId);
        //删除课程
        int i = baseMapper.deleteById(courseId);

        //删除章节
        QueryWrapper<EduChapter> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("course_id", courseId);
        boolean remove = eduChapterService.remove(objectQueryWrapper);

        return true;







    }

    @Override
    @Cacheable(value = "course",key = "'selectIndexList'")
    public List<EduCourse> selecthotCoures() {

        QueryWrapper<EduCourse> eduCourseWrapper = new QueryWrapper<>();
        eduCourseWrapper.orderByDesc("view_count");
        eduCourseWrapper.last("limit 8");
        List<EduCourse> list = baseMapper.selectList(eduCourseWrapper);

        return list;
    }

    /**
     *<p>
     *     前端页面条件查询课程信息
     *</p>
     * @param objectPage
     * @param coursePage
     * @return
     */
    @Override
    public void getCoursePage(Page<EduCourse> objectPage, CoursePage coursePage) {
        QueryWrapper<EduCourse> qw = new QueryWrapper<>();
        if (coursePage==null){
            baseMapper.selectPage(objectPage,null);

        }
        if (StringUtils.isNotBlank(coursePage.getId())){
            qw.eq("subject_parent_id",coursePage.getId());
        }
        if (StringUtils.isNotBlank(coursePage.getCourseId())){
            qw.eq("subject_id",coursePage.getCourseId());
        }
        if (coursePage.getBuyCountDesc()==0){
            qw.orderByDesc("buy_count");
        }
        if (coursePage.getViewCountDsc()==0){
            qw.orderByDesc("view_count");
        }

        baseMapper.selectPage(objectPage,qw);




    }
}

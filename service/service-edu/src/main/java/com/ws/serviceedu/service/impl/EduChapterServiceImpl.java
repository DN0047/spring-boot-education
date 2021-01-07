package com.ws.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.serviceedu.entity.EduChapter;
import com.ws.serviceedu.entity.EduVideo;
import com.ws.serviceedu.entity.chapter.Chapter;
import com.ws.serviceedu.entity.chapter.ChapterVo;
import com.ws.serviceedu.mapper.EduChapterMapper;
import com.ws.serviceedu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<Chapter> getChapterVideoBy(String courseId) {
        //查询章节
        QueryWrapper<EduChapter> chapterOne = new QueryWrapper<>();
        chapterOne.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(chapterOne);
        //查询课时


        List<EduVideo> list = eduVideoService.list(null);

        ArrayList<Chapter> objects = new ArrayList<>();

        for (EduChapter eduChapter:eduChapters){
            Chapter chapter = new Chapter();
            BeanUtils.copyProperties(eduChapter,chapter);

            String id = chapter.getId();
            ArrayList<ChapterVo> chapterVo = new ArrayList<>();

            for (EduVideo eduVideo:list) {
                ChapterVo chapterVo1 = new ChapterVo();
                if (eduVideo.getChapterId().equals(id)){
                    chapterVo1.setId(eduVideo.getId());
                    chapterVo1.setTitle(eduVideo.getTitle());
                    chapterVo.add(chapterVo1);
                }

            }
            chapter.setChildren(chapterVo);
            objects.add(chapter);



      }



            return objects;
    }
}

package com.ws.serviceedu.service;

import com.ws.serviceedu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ws.serviceedu.entity.chapter.Chapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author ws
 * @since 2020-12-22
 */
public interface EduChapterService extends IService<EduChapter> {

    List<Chapter> getChapterVideoBy(String courseId);
}

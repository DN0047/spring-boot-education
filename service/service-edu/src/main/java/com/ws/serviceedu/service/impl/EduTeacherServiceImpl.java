package com.ws.serviceedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.serviceedu.entity.EduTeacher;
import com.ws.serviceedu.mapper.EduTeacherMapper;
import com.ws.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ws
 * @since 2020-12-17
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page page, EduTeacher eduTeacher) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (eduTeacher == null){
            baseMapper.selectPage(page,queryWrapper);

            return;
        }

        String name = eduTeacher.getName();
        Integer level = eduTeacher.getLevel();


        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            queryWrapper.eq("level", level);
        }



        baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Cacheable(value = "teacher",key = "'selectIndexList'")
    public List<EduTeacher> selectHotTeacher() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        wrapper.last("limit 4");
        List<EduTeacher> eduTeachers = baseMapper.selectList(wrapper);

        return eduTeachers;
    }

    /**
     * <p>
     *     首页名师分页
     * </p>
     *
     * @return
     */
    @Override
    public HashMap<String,Object> pageTeacher(Page<EduTeacher> objectPage) {



        baseMapper.selectPage(objectPage, null);
        long current = objectPage.getCurrent();
        List<EduTeacher> records = objectPage.getRecords();
        long size = objectPage.getSize();
        long total = objectPage.getTotal();
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("current",current);
        objectObjectHashMap.put("records",records);
        objectObjectHashMap.put("size",size);
        objectObjectHashMap.put("total",total);

        return objectObjectHashMap;
    }
}


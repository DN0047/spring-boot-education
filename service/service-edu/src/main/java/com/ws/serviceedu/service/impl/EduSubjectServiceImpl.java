package com.ws.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.serviceedu.entity.EduSubject;
import com.ws.serviceedu.entity.excel.OneSubject;
import com.ws.serviceedu.entity.excel.SubjectData;
import com.ws.serviceedu.entity.excel.TwoSubject;
import com.ws.serviceedu.listener.SubjectExcelListener;
import com.ws.serviceedu.mapper.EduSubjectMapper;
import com.ws.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ws
 * @since 2020-12-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        //上传excel文件
        try {
            InputStream inputStream =file.getInputStream();


            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<OneSubject> getAllSubject() {

        QueryWrapper<EduSubject> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("parent_id",0);
        List<EduSubject> oneEduSubjects = baseMapper.selectList(objectQueryWrapper);
        QueryWrapper<EduSubject> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.ne("parent_id",0);
        List<EduSubject> twoEduSubjects = baseMapper.selectList(QueryWrapper);

        ArrayList<OneSubject> objects = new ArrayList<>();
        for (EduSubject oneEdu:oneEduSubjects) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(oneEdu,oneSubject);

            for (EduSubject towEdu:twoEduSubjects) {

                if (towEdu.getParentId().equals(oneSubject.getId())){

                 TwoSubject twoSubject1 = new TwoSubject();
                 twoSubject1.setId(towEdu.getId());
                 twoSubject1.setTitle(towEdu.getTitle());

                    List<TwoSubject> children = oneSubject.getChildren();
                    children.add(twoSubject1);

                }
            }
            objects.add(oneSubject);
        }




        return objects;
    }
}

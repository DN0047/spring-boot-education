package com.ws.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ws.serviceedu.entity.EduSubject;
import com.ws.serviceedu.entity.excel.SubjectData;
import com.ws.serviceedu.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }
    public SubjectExcelListener() { }






    //判断有无重复一级分类

    public EduSubject existOneSubject(EduSubjectService eduSubjectService,String title){
        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("title",title);
        qw.eq("parent_id",0);
        EduSubject one = eduSubjectService.getOne(qw);
        return  one;

    }

    //判断有无重复二级分类

    public EduSubject existTwoSubject(EduSubjectService eduSubjectService,String title,String parentId){
        QueryWrapper<EduSubject> qw = new QueryWrapper<>();
        qw.eq("title",title);
        qw.eq("parent_id",parentId);
        EduSubject two = eduSubjectService.getOne(qw);
        return  two;

    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        EduSubject OneEduSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (OneEduSubject==null){//没有重复，添加一级分类
            OneEduSubject = new EduSubject();

            OneEduSubject.setParentId("0");
            OneEduSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(OneEduSubject);
        }
        String id = OneEduSubject.getId();

        EduSubject twoEduSubject1 = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), id);
        if (twoEduSubject1==null){//添加二级分类
            twoEduSubject1=  new EduSubject();
            twoEduSubject1.setTitle(subjectData.getTwoSubjectName());
            twoEduSubject1.setParentId(id);
            eduSubjectService.save(twoEduSubject1);

        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

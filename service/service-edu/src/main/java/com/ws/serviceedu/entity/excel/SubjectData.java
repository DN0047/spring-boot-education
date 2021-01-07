package com.ws.serviceedu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import org.springframework.stereotype.Component;

@Component
public class SubjectData {
    @ExcelProperty(index=0)
    private String oneSubjectName;


    @ExcelProperty(index=1)
    private String twoSubjectName;

    public String getOneSubjectName() {
        return oneSubjectName;
    }

    public void setOneSubjectName(String oneSubjectName) {
        this.oneSubjectName = oneSubjectName;
    }

    public String getTwoSubjectName() {
        return twoSubjectName;
    }

    public void setTwoSubjectName(String twoSubjectName) {
        this.twoSubjectName = twoSubjectName;
    }
}

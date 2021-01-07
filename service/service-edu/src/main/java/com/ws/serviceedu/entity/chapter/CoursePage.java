package com.ws.serviceedu.entity.chapter;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CoursePage {

    @ApiModelProperty(value = "章节ID")

    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;


    @ApiModelProperty(value = "销售数量降序升序")
    private Long buyCountDesc;

    @ApiModelProperty(value = "浏览数量降序升序")
    private Long viewCountDsc;
}

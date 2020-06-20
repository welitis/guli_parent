package com.welisit.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author welisit
 * @create 2020-06-20 15:22
 */
@ApiModel(value = "课程基本信息", description = "编辑课程基本信息的表单对象")
@Data
public class CourseInfoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    @NotEmpty
    private String teacherId;

    @ApiModelProperty(value = "课程专业ID")
    @NotEmpty
    private String subjectId;

    @ApiModelProperty(value = "课程标题")
    @NotEmpty
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    @NotEmpty
    private String cover;

    @ApiModelProperty(value = "课程简介")
    @NotEmpty
    private String description;

}

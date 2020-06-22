package com.welisit.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author welisit
 * @Description 课程发布时的视图对象
 * @create 2020-06-22 20:38
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVO  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;
    /**
     * 封面
     */
    private String cover;
    /**
     * 小节数
     */
    private Integer lessonNum;
    /**
     * 一级分类
     */
    private String subjectLevel;
    /**
     * 教师名
     */
    private String teacherName;
    /**
     * 价格   只用于显示
     */
    private String price;
}

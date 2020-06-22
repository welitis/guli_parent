package com.welisit.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author welisit
 * @Description 课程小节视图
 * @create 2020-06-21 22:31
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
}

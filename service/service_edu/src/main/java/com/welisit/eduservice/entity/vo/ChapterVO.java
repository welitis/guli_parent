package com.welisit.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author welisit
 * @Description 课程章节视图
 * @create 2020-06-21 22:31
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private List<VideoVO> children = new ArrayList<>();
}

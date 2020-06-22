package com.welisit.eduservice.entity.vo;

import lombok.Data;

/**
 * @author welisit
 * @Description 二级分类
 * @create 2020-06-20 21:30
 */
@Data
public class SubjectVO implements Comparable<SubjectVO> {

    private String id;

    private String title;

    private String parentId;

    private Integer sort;

    @Override
    public int compareTo(SubjectVO o) {
        return sort - o.sort;
    }
}

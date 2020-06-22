package com.welisit.eduservice.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 一级分类嵌套视图
 * @author welisit
 * @create 2020-06-19 15:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SubjectNestedVO extends SubjectVO implements Comparable<SubjectVO> {

    private List<SubjectVO> children = new ArrayList<>();

}

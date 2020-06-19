package com.welisit.eduservice.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类嵌套视图
 * @author welisit
 * @create 2020-06-19 15:48
 */
@Data
public class SubjectNestedVO implements Comparable<SubjectNestedVO> {

    private String id;

    private String title;

    private String parentId;

    private Integer sort;

    private List<SubjectNestedVO> children = new ArrayList<>();

    @Override
    public int compareTo(SubjectNestedVO subjectNestedVO) {
        return sort - subjectNestedVO.sort;
    }
}

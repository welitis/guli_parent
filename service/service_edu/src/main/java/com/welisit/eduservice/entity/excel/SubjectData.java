package com.welisit.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 分类Excel表数据对象
 * @author welisit
 * @create 2020-06-19 11:32
 */
@Data
public class SubjectData {

    /**
     * 一级分类
     */
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    /**
     * 二级分类
     */
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}

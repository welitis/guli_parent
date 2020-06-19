package com.welisit.eduservice.controller;


import com.welisit.commonutils.R;
import com.welisit.eduservice.entity.vo.SubjectNestedVO;
import com.welisit.eduservice.service.EduSubjectService;
import com.welisit.servicebase.exception.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Welisit
 * @since 2020-06-19
 */
@Api(value = "课程类目", description = "课程类目")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation("Excel批量导入")
    @PostMapping("by/excel")
    public R addSubjectByExcel(@RequestParam("file") MultipartFile multipartFile) {
        try {
            eduSubjectService.saveByExcel(multipartFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(20003, "上传数据异常");
        }
        return R.ok();
    }

    @ApiOperation("获取所有学科分类嵌套列表")
    @GetMapping("list")
    public R getSubjectNestedList() {
        List<SubjectNestedVO> subjectNestedVOList = eduSubjectService.nestedList();
        return R.ok().data("items", subjectNestedVOList);
    }

}


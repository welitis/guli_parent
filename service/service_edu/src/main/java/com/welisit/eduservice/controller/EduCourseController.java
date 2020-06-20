package com.welisit.eduservice.controller;


import com.welisit.commonutils.R;
import com.welisit.eduservice.entity.dto.CourseInfoForm;
import com.welisit.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
@Api(value = "课程信息", description = "课程信息")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
@Slf4j
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "新增课程信息")
    @PostMapping("info")
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @Valid @RequestBody CourseInfoForm courseInfoForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.info("====类型转换错误====");
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(s -> log.info("错误字段：{}, 错误信息：{}", s.getField(), s.getDefaultMessage()));
            return R.error().message("课程信息错误");
        }
        String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
        if(!StringUtils.isEmpty(courseId)){
            return R.ok().data("courseId", courseId);
        }else{
            return R.error().message("保存失败");
        }
    }

}


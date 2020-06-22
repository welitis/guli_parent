package com.welisit.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welisit.commonutils.AliyunUtils;
import com.welisit.commonutils.R;
import com.welisit.eduservice.entity.EduCourse;
import com.welisit.eduservice.entity.dto.CourseInfoForm;
import com.welisit.eduservice.entity.dto.CourseQueryParam;
import com.welisit.eduservice.entity.vo.CoursePublishVO;
import com.welisit.eduservice.service.EduCourseService;
import com.welisit.servicebase.config.OSSProperties;
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
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation(value = "获取课程信息")
    @GetMapping("info/{id}")
    public R getInfo(
            @PathVariable("id") String courseId
    ) {
        CourseInfoForm courseInfo = eduCourseService.getCourseInfo(courseId);
        log.info(courseInfo.toString());
        return R.ok().data("item", courseInfo);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("info")
    public R updateCourseInfoById(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        eduCourseService.updateCourseInfoById(courseInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("publish/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        CoursePublishVO courseInfoForm = eduCourseService.getCoursePublishVOById(id);
        return R.ok().data("item", courseInfoForm);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publish/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable("id") String courseId){

        eduCourseService.publishCourseById(courseId);
        return R.ok();
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQueryParam courseQueryParam){

        Page<EduCourse> pageParam = new Page<>(page, limit);

        eduCourseService.pageQuery(pageParam, courseQueryParam);
        List<EduCourse> records = pageParam.getRecords();

        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = eduCourseService.removeCourseById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}


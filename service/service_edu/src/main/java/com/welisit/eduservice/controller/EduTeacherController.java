package com.welisit.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welisit.commonutils.AliyunUtils;
import com.welisit.commonutils.R;
import com.welisit.commonutils.ResultCode;
import com.welisit.eduservice.entity.EduTeacher;
import com.welisit.eduservice.entity.dto.TeacherQueryParam;
import com.welisit.eduservice.service.EduTeacherService;
import com.welisit.servicebase.config.OSSProperties;
import com.welisit.servicebase.exception.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Welisit
 * @since 2020-06-12
 */
@Slf4j
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private OSSProperties ossProperties;

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable("id") String id) {
        boolean result = eduTeacherService.removeById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "多条件分页查询讲师列表")
    @GetMapping("list")
    public R pageQueryList(
            @ApiParam(name = "pageNo", value = "当前页码", required = true)
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @RequestParam(value = "limit", defaultValue = "5") Integer limit,

            @ApiParam(name = "teacherQueryParam", value = "查询条件参数")
                    TeacherQueryParam teacherQueryParam
    ) {
        Page<EduTeacher> page = new Page<>(pageNo, limit);

        eduTeacherService.pageQuery(page, teacherQueryParam);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();

        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R addTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        eduTeacherService.saveAndSort(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {

        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping()
    public R updateById(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        eduTeacherService.updateById(teacher);
        return R.ok();
    }

    @ApiOperation(value = "上传头像")
    @PostMapping("avatar/upload")
    public R uploadAvatar(
            @ApiParam(name = "file", value = "文件对象", required = true)
            @RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            R result = AliyunUtils.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    inputStream,
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    multipartFile.getOriginalFilename()
            );
            if (ResultCode.SUCCESS.equals(result.getCode())) {
                return R.ok().data(result.getData());
            }
            log.info("上传文件错误, {}", result.getMessage());
            return R.error();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(20002, "文件数据异常");
        }
    }
}


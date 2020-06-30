package com.welisit.aliyun.controller;

import com.welisit.aliyun.service.FileService;
import com.welisit.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author welisit
 * @Description 关于阿里云oss的文件控制器
 * @create 2020-06-25 18:58
 */
@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/oss/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        String uploadUrl = fileService.upload(file);
        //返回r对象
        if (uploadUrl != null) {
            return R.ok().message("文件上传成功").data("url", uploadUrl);
        } else {
            return R.error().message("文件上传失败");
        }

    }
}

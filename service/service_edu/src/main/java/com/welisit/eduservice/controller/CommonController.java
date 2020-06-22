package com.welisit.eduservice.controller;

import com.welisit.commonutils.AliyunUtils;
import com.welisit.commonutils.R;
import com.welisit.commonutils.ResultCode;
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

/**
 * @author welisit
 * @Description 通用的请求控制器
 * @create 2020-06-21 9:44
 */
@Slf4j
@Api(description = "通用请求")
@RestController
@RequestMapping("eduservice/common")
@CrossOrigin
public class CommonController {

    @Autowired
    private OSSProperties ossProperties;

    @ApiOperation(value = "上传单个文件")
    @PostMapping("upload")
    public R uploadFile(
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

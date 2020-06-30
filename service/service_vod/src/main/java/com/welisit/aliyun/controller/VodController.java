package com.welisit.aliyun.controller;

import com.welisit.aliyun.service.VodService;
import com.welisit.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author welisit
 * @Description 视频服务控制器
 * @create 2020-06-25 22:36
 */
@Api(description="阿里云视频点播微服务")
@CrossOrigin
@RestController
@RequestMapping("/vod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("视频文件上传")
    @PostMapping("upload")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        String videoId = vodService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }

    @ApiOperation("视频文件删除")
    @DeleteMapping("{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId){

        vodService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @ApiOperation("批量视频文件删除")
    @DeleteMapping("list")
    public R removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList){

        vodService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }
}

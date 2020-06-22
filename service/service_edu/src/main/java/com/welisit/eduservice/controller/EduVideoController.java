package com.welisit.eduservice.controller;


import com.welisit.commonutils.R;
import com.welisit.eduservice.entity.dto.VideoInfoForm;
import com.welisit.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
@Api(description="小节管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    @ApiOperation(value = "新增小节")
    @PostMapping()
    public R save(
            @ApiParam(name = "videoForm", value = "小节对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm){

        videoService.saveVideoInfo(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询小节")
    @GetMapping("{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "小节ID", required = true)
            @PathVariable String id){

        VideoInfoForm videoInfoForm = videoService.getVideoInfoFormById(id);
        return R.ok().data("item", videoInfoForm);
    }

    @ApiOperation(value = "更新小节")
    @PutMapping("{id}")
    public R updateCourseInfoById(
            @ApiParam(name = "VideoInfoForm", value = "小节基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm){

        videoService.updateVideoInfoByForm(videoInfoForm);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id){
        boolean result = videoService.removeVideoById(id);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }
}


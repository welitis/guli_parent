package com.welisit.eduservice.client;

import com.welisit.commonutils.R;
import com.welisit.eduservice.client.hystrix.VodFileDegradeFeignClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author welisit
 * @Description 视频服务远程调用接口
 * @create 2020-06-26 16:49
 */
@FeignClient(value = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     * 删除阿里云视频
     * @param videoId
     * @return
     */
    @DeleteMapping("/vod/video/{videoId}")
    R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                  @PathVariable("videoId") String videoId);

    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/vod/video/list")
    public R removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList);
}

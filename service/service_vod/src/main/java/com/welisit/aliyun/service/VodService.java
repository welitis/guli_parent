package com.welisit.aliyun.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author welisit
 * @Description 视频点播服务接口
 * @create 2020-06-25 22:21
 */
public interface VodService {

    /**
     * 阿里云视频上传
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 根据videoID删除阿里云上的视频
     * @param videoId
     */
    void removeVideo(String videoId);

    /**
     * 批量删除阿里云视频
     * @param videoIdList
     */
    void removeVideoList(List<String> videoIdList);
}

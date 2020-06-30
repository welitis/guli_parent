package com.welisit.aliyun.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author welisit
 * @Description 关于文件的服务接口
 * @create 2020-06-25 19:00
 */
public interface FileService {

    /**
     * 文件上传至阿里云
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}

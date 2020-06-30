package com.welisit.aliyun.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.comm.ResponseMessage;
import com.aliyun.oss.model.PutObjectResult;
import com.welisit.aliyun.config.OSSProperties;
import com.welisit.aliyun.service.FileService;
import com.welisit.commonutils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author welisit
 * @Description 文件服务实现
 * @create 2020-06-25 19:01
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSProperties ossProperties;

    @Override
    public String upload(MultipartFile file) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret());

        // 生成上传文件的目录
        String folderName = new SimpleDateFormat("yyyy/MM/dd").format(new Date());

        // 生成上传文件在OSS服务器上保存时的文件名
        // 原始文件名：beautfulgirl.jpg
        // 生成文件名：wer234234efwer235346457dfswet346235.jpg
        // 使用UUID生成文件主体名称
        String fileMainName = UUID.randomUUID().toString().replace("-", "");

        // 从原始文件名中获取文件扩展名
        String originalName = file.getOriginalFilename();
        String extensionName = originalName.substring(originalName.lastIndexOf("."));

        // 使用目录、文件主体名称、文件扩展名称拼接得到对象名称
        String objectName = folderName + "/" + fileMainName + extensionName;

        try {
            // 调用OSS客户端对象的方法上传文件并获取响应结果数据
            PutObjectResult putObjectResult = ossClient.putObject(ossProperties.getBucketName(), objectName, file.getInputStream());

            // 从响应结果中获取具体响应消息
            ResponseMessage responseMessage = putObjectResult.getResponse();

            // 根据响应状态码判断请求是否成功
            if(responseMessage == null) {

                // 拼接访问刚刚上传的文件的路径
                // 当前方法返回成功
                return ossProperties.getBucketDomain() + "/" + objectName;
            } else {
                // 获取响应状态码
                int statusCode = responseMessage.getStatusCode();

                // 如果请求没有成功，获取错误消息
                String errorMessage = responseMessage.getErrorResponseAsString();

                // 当前方法返回失败
                log.info("oss文件上传失败, 错误码：{}, 错误信息：{}", statusCode, errorMessage);
            }
        } catch (Exception e) {
            // 当前方法返回失败
            log.error("oss文件上传失败");
            log.error(ExceptionUtil.getMessage(e));
        } finally {

            if(ossClient != null) {

                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
        return null;
    }
}

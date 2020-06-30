package com.welisit.aliyun.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author welisit
 * @create 2020-06-18 18:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSProperties {

    /**
     * 节点域名
     */
    private String endPoint;
    /**
     * 桶名称
     */
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
    /**
     * 桶域名
     */
    private String bucketDomain;

}

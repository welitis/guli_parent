package com.welisit.commonutils.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author welisit
 * @Description TODO
 * @create 2020-06-17 23:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OSSProperties {

    private String endPoint;
    private String bucketName;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketDomain;

}

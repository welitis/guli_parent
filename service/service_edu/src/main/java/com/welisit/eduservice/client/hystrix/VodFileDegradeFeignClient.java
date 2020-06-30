package com.welisit.eduservice.client.hystrix;

import com.welisit.commonutils.R;
import com.welisit.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author welisit
 * @Description vodClient的降级实现类
 * @create 2020-06-26 18:40
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R removeVideoList(List<String> videoIdList) {
        return R.error().message("time out");
    }
}

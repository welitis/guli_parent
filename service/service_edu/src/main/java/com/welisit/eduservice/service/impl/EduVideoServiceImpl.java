package com.welisit.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.welisit.eduservice.entity.EduCourse;
import com.welisit.eduservice.entity.EduVideo;
import com.welisit.eduservice.entity.dto.VideoInfoForm;
import com.welisit.eduservice.mapper.EduCourseMapper;
import com.welisit.eduservice.mapper.EduVideoMapper;
import com.welisit.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welisit.servicebase.exception.ApiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Override
    public void saveVideoInfo(VideoInfoForm videoInfoForm) {

        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.save(video);

        if(!result){
            throw new ApiException(20001, "课时信息保存失败");
        }
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        //从video表中取数据
        EduVideo video = this.getById(id);
        if(video == null){
            throw new ApiException(20001, "数据不存在");
        }

        //创建videoInfoForm对象
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(video, videoInfoForm);

        return videoInfoForm;
    }

    @Override
    public void updateVideoInfoByForm(VideoInfoForm videoInfoForm) {
        //保存课时基本信息
        EduVideo video = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm, video);
        boolean result = this.updateById(video);
        if(!result){
            throw new ApiException(20001, "课时信息保存失败");
        }
    }

    @Override
    public boolean removeVideoById(String id) {
        //删除视频资源 TODO

        int result = baseMapper.deleteById(id);
        return result > 0;
    }
}

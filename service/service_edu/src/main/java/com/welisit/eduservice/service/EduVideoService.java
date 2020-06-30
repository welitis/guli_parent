package com.welisit.eduservice.service;

import com.welisit.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.welisit.eduservice.entity.dto.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 保存小节信息，将小节form保存进数据库
     * @param videoInfoForm
     */
    void saveVideoInfo(VideoInfoForm videoInfoForm);

    /**
     * 获取小节信息表单通过小节id
     * @param id
     * @return
     */
    VideoInfoForm getVideoInfoFormById(String id);

    /**
     * 通过表单信息更新小节
     * @param videoInfoForm
     */
    void updateVideoInfoByForm(VideoInfoForm videoInfoForm);

    /**
     * 删除小节通过小节id
     * @param id
     * @return
     */
    boolean removeVideoById(String id);

    /**
     * 根据课程id删除小节和视频
     * @param courseId
     * @return
     */
    boolean removeByCourseId(String courseId);
}

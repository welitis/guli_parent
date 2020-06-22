package com.welisit.eduservice.mapper;

import com.welisit.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.welisit.eduservice.entity.vo.CoursePublishVO;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVO selectCoursePublishVOById(String id);

    /**
     * 课程小节数自增
     * @param courseId
     */
    void updateLessonNumPlusById(String courseId);

    /**
     * 课程小节数自减
     * @param courseId
     */
    void updateLessonNumSubById(String courseId);
}

package com.welisit.eduservice.service;

import com.welisit.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.welisit.eduservice.entity.dto.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 保存课程信息和课程描述信息
     * @param courseInfoForm
     * @return 课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    /**
     * 回显课程信息
     * @return
     * @param courseId
     */
    CourseInfoForm getCourseInfo(String courseId);

    /**
     * 更新课程表单信息
     * @param courseInfoForm
     */
    void updateCourseInfoById(CourseInfoForm courseInfoForm);
}

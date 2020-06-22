package com.welisit.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welisit.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.welisit.eduservice.entity.dto.CourseInfoForm;
import com.welisit.eduservice.entity.dto.CourseQueryParam;
import com.welisit.eduservice.entity.vo.CoursePublishVO;

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

    /**
     * 获取课程发布时的信息对象
     * @param courseId 课程id
     * @return
     */
    CoursePublishVO getCoursePublishVOById(String courseId);

    /**
     * 发布课程
     * @param courseId
     */
    void publishCourseById(String courseId);

    /**
     * 分页多条件查询课程
     * @param pageParam
     * @param courseQueryParam
     */
    void pageQuery(Page<EduCourse> pageParam, CourseQueryParam courseQueryParam);

    /**
     * 删除课程
     * @param id
     * @return
     */
    boolean removeCourseById(String id);
}

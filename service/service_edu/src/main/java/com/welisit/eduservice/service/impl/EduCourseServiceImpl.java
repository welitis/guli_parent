package com.welisit.eduservice.service.impl;

import com.welisit.eduservice.entity.EduCourse;
import com.welisit.eduservice.entity.EduCourseDescription;
import com.welisit.eduservice.entity.dto.CourseInfoForm;
import com.welisit.eduservice.mapper.EduCourseDescriptionMapper;
import com.welisit.eduservice.mapper.EduCourseMapper;
import com.welisit.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduCourseDescriptionMapper eduCourseDescriptionMapper;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        // 保存课程信息到course表中
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        eduCourseMapper.insert(eduCourse);

        String courseId = eduCourse.getId();

        // 保存课程详情到课程详情表中
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseId);
        eduCourseDescriptionMapper.insert(eduCourseDescription);

        return courseId;
    }
}
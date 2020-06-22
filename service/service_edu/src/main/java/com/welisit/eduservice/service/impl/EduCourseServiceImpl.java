package com.welisit.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welisit.eduservice.entity.EduChapter;
import com.welisit.eduservice.entity.EduCourse;
import com.welisit.eduservice.entity.EduCourseDescription;
import com.welisit.eduservice.entity.EduVideo;
import com.welisit.eduservice.entity.dto.CourseInfoForm;
import com.welisit.eduservice.entity.dto.CourseQueryParam;
import com.welisit.eduservice.entity.vo.CoursePublishVO;
import com.welisit.eduservice.mapper.EduChapterMapper;
import com.welisit.eduservice.mapper.EduCourseDescriptionMapper;
import com.welisit.eduservice.mapper.EduCourseMapper;
import com.welisit.eduservice.mapper.EduVideoMapper;
import com.welisit.eduservice.service.EduChapterService;
import com.welisit.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welisit.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Autowired
    private EduChapterMapper eduChapterMapper;

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

    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        EduCourse eduCourse = eduCourseMapper.selectById(courseId);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionMapper.selectById(courseId);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);
        courseInfoForm.setDescription(eduCourseDescription.getDescription());
        return courseInfoForm;
    }

    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        eduCourseMapper.updateById(eduCourse);

        // 更新课程详情表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoForm, eduCourseDescription);
        eduCourseDescriptionMapper.updateById(eduCourseDescription);
    }

    @Override
    public CoursePublishVO getCoursePublishVOById(String courseId) {
        return eduCourseMapper.selectCoursePublishVOById(courseId);
    }

    @Override
    public void publishCourseById(String courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus(EduCourse.COURSE_NORMAL);
        baseMapper.updateById(course);
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQueryParam courseQueryParam) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        if (courseQueryParam == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        String title = courseQueryParam.getTitle();
        String teacherId = courseQueryParam.getTeacherId();
        String subjectId = courseQueryParam.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean removeCourseById(String id) {
        //根据id删除所有视频
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoMapper.delete(eduVideoQueryWrapper.eq("course_id", id));

        //根据id删除所有章节
        eduChapterMapper.delete(new QueryWrapper<EduChapter>().eq("course_id", id));

        // 刪除所有簡介
        eduCourseDescriptionMapper.deleteById(id);

        int result = baseMapper.deleteById(id);
        return result > 0;
    }
}

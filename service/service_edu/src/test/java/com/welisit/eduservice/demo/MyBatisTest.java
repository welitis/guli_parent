package com.welisit.eduservice.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.welisit.eduservice.entity.*;
import com.welisit.eduservice.entity.vo.VideoVO;
import com.welisit.eduservice.mapper.*;
import com.welisit.eduservice.service.EduCourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author welisit
 * @create 2020-06-19 11:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisTest {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Test
    public void testInsert() {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(sort)");

        Integer integer = eduTeacherMapper.selectCount(queryWrapper);
        System.out.println(integer);
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", "数据库")
                .eq("parent_id", "0");
        System.out.println(eduSubjectMapper.selectOne(queryWrapper));
    }

    @Test
    public void testInsertRollback() {
        EduSubject eduSubject = new EduSubject();
        eduSubject.setTitle("aaa");
        eduSubject.setParentId("0");
        eduSubject.setSort(10);
        eduSubjectMapper.insert(eduSubject);
        System.out.println(eduSubject.getId());
    }

    @Test
    public void testBeanUtils() {
        EduVideo eduVideo = new EduVideo();
        VideoVO videoVO = new VideoVO();
        eduVideo.setFree(true);
        eduVideo.setTitle("hello");
        BeanUtils.copyProperties(eduVideo, videoVO);
        System.out.println(videoVO);
    }

    @Test
    public void testEduVideoMapper() {
        List<EduVideo> eduVideos = eduVideoMapper.selectList(null);
        System.out.println(eduVideos);
    }

    @Test
    public void testUpdateIncrement() {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId("1274543294918979586");
//        eduCourse.setTeacherId("aaahello");
//        eduCourse.setLessonNum(-1);
        eduCourseMapper.updateLessonNumPlusById("1274543294918979586");
    }

    @Test
    public void testDeleteCourse() {
//        eduChapterMapper.delete(new QueryWrapper<EduChapter>().eq("course_id", "1275092499760701442"));
        eduCourseService.removeCourseById("1275092499760701442");
    }
}

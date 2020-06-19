package com.welisit.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.welisit.eduservice.entity.EduTeacher;
import com.welisit.eduservice.entity.dto.TeacherQueryParam;
import com.welisit.eduservice.mapper.EduSubjectMapper;
import com.welisit.eduservice.mapper.EduTeacherMapper;
import com.welisit.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-12
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    @Override
    public void pageQuery(Page<EduTeacher> page, TeacherQueryParam teacherQueryParam) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");

        if (teacherQueryParam == null) {
            baseMapper.selectPage(page, queryWrapper);
            return;
        }

        String name = teacherQueryParam.getName();
        Integer level = teacherQueryParam.getLevel();
        String begin = teacherQueryParam.getBegin();
        String end = teacherQueryParam.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (level != null) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void saveAndSort(EduTeacher teacher) {
        // 先查询最大排序值，然后设置当前对象的排序为最大值+1
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(sort)");
        Integer maxSort = eduTeacherMapper.selectCount(queryWrapper);
        teacher.setSort(maxSort);
        eduTeacherMapper.insert(teacher);
    }
}

package com.welisit.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.welisit.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.welisit.eduservice.entity.dto.TeacherQueryParam;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-12
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 讲师列表分页查询
     * @param page 分页对象
     * @param teacherQueryParam 查询参数对象
     */
    void pageQuery(Page<EduTeacher> page, TeacherQueryParam teacherQueryParam);

}

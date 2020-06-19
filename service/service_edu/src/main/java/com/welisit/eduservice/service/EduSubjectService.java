package com.welisit.eduservice.service;

import com.welisit.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.welisit.eduservice.entity.vo.SubjectNestedVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-19
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 通过Excel保存类目
     * @param multipartFile
     * @throws IOException
     */
    void saveByExcel(MultipartFile multipartFile) throws IOException;

    /**
     * 获取所有分类嵌套列表
     * @return
     */
    List<SubjectNestedVO> nestedList();
}

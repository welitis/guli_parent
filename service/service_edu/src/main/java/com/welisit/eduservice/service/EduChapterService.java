package com.welisit.eduservice.service;

import com.welisit.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.welisit.eduservice.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 获取嵌套课程章节视图列表
     * @param courseId
     * @return
     */
    List<ChapterVO> nestedList(String courseId);

    /**
     * 根据章节id删除章节信息
     * @param id
     * @return
     */
    boolean removeChapterById(String id);
}

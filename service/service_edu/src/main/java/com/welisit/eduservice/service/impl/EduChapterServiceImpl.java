package com.welisit.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.Property;
import com.welisit.eduservice.entity.EduChapter;
import com.welisit.eduservice.entity.EduVideo;
import com.welisit.eduservice.entity.vo.ChapterVO;
import com.welisit.eduservice.entity.vo.VideoVO;
import com.welisit.eduservice.mapper.EduChapterMapper;
import com.welisit.eduservice.mapper.EduVideoMapper;
import com.welisit.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduChapterMapper eduChapterMapper;

    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Override
    public List<ChapterVO> nestedList(String courseId) {
        // 获取所以的章节信息
        List<EduChapter> eduChapterList = eduChapterMapper.selectList(new QueryWrapper<EduChapter>()
                .orderByAsc("sort")
                .eq("course_id", courseId));
        // 遍历章节信息, 并转换为章节视图对象, 创建map, 指定章节id和章节视图对象的map
        Map<String, ChapterVO> chapterVOMap = new HashMap<>();
        for (EduChapter eduChapter : eduChapterList) {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(eduChapter, chapterVO);
            chapterVOMap.put(eduChapter.getId(), chapterVO);
        }
        // 获取所有的视频小节信息
        List<EduVideo> eduVideoList = eduVideoMapper.selectList(new QueryWrapper<EduVideo>()
                .orderByAsc("sort")
                .eq("course_id", courseId));
        // 遍历所以的视频小节信息
        for (EduVideo eduVideo : eduVideoList) {
            // 获取小节信息的章节id
            String chapterId = eduVideo.getChapterId();
            // 通过map找到对应的章节视图对象
            ChapterVO chapterVO = chapterVOMap.get(chapterId);
            // 并将小节信息转换为小节视图对象放入章节视图对象的children数组中
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(eduVideo, videoVO);
            chapterVO.getChildren().add(videoVO);
        }
        // 获取map的values()数组作为返回值
        List<ChapterVO> chapterVOList = new ArrayList<>();
        for (ChapterVO chapterVO : chapterVOMap.values()) {
            chapterVOList.add(chapterVO);
        }
        return chapterVOList;
    }
}

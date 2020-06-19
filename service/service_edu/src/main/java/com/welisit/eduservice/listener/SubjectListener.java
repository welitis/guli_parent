package com.welisit.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.welisit.eduservice.entity.EduSubject;
import com.welisit.eduservice.entity.excel.SubjectData;
import com.welisit.eduservice.mapper.EduSubjectMapper;
import com.welisit.servicebase.exception.ApiException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author welisit
 * @create 2020-06-19 11:31
 */
@AllArgsConstructor
@NoArgsConstructor
public class SubjectListener extends AnalysisEventListener<SubjectData> {

    private EduSubjectMapper eduSubjectMapper;

    /**
     * 监听器一行一行读取时调用的方法
     * @param subjectData 分类数据
     * @param analysisContext 分析上下文
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        String oneSubjectName = subjectData.getOneSubjectName();
        String twoSubjectName = subjectData.getTwoSubjectName();
        if (StringUtils.isEmpty(oneSubjectName) || StringUtils.isEmpty(twoSubjectName)) {
            throw new ApiException(20004, "Excel数据不能为空");
        }
        // 判断一级目录在数据库中是否存在，存在就不保存
        EduSubject oneEduSubject = existOneSubjectName(oneSubjectName);
        if (oneEduSubject == null) {
            oneEduSubject = new EduSubject();
            oneEduSubject.setTitle(oneSubjectName);
            // 一级分类的父Id为0
            oneEduSubject.setParentId("0");
            Integer sort = getOneSubjectLastSort();
            oneEduSubject.setSort(sort + 1);
            eduSubjectMapper.insert(oneEduSubject);
        }
        // 获取一级类目的id, 用作后续二级类目的父id
        String pid = oneEduSubject.getId();

        EduSubject twoEduSubject = existTwoSubjectName(twoSubjectName, pid);
        if (twoEduSubject == null) {
            twoEduSubject = new EduSubject();
            twoEduSubject.setParentId(pid);
            twoEduSubject.setTitle(twoSubjectName);
            Integer sort = getTwoSubjectLastSort(pid);
            twoEduSubject.setSort(sort + 1);
            eduSubjectMapper.insert(twoEduSubject);
        }

    }

    /**
     * 获取二级类目最后一个排序序号
     * @return
     */
    private Integer getTwoSubjectLastSort(String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(sort)")
                .eq("parent_id", pid);
        return eduSubjectMapper.selectCount(queryWrapper);
    }

    /**
     * 获取一级类目最后一个排序序号
     * @return
     */
    private Integer getOneSubjectLastSort() {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(sort)")
                .eq("parent_id", "0");
        return eduSubjectMapper.selectCount(queryWrapper);
    }

    /**
     * 判断一级分类是否存在
     * @param oneSubjectName
     * @return
     */
    private EduSubject existOneSubjectName(String oneSubjectName) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", oneSubjectName)
                .eq("parent_id", "0");
        return eduSubjectMapper.selectOne(queryWrapper);
    }

    /**
     * 判断二级分类是否存在
     * @param twoSubjectName
     * @return
     */
    private EduSubject existTwoSubjectName(String twoSubjectName, String pid) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", twoSubjectName)
                .eq("parent_id", pid);
        return eduSubjectMapper.selectOne(queryWrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

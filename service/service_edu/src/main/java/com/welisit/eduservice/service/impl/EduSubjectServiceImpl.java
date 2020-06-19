package com.welisit.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.welisit.eduservice.entity.EduSubject;
import com.welisit.eduservice.entity.excel.SubjectData;
import com.welisit.eduservice.entity.vo.SubjectNestedVO;
import com.welisit.eduservice.listener.SubjectListener;
import com.welisit.eduservice.mapper.EduSubjectMapper;
import com.welisit.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Welisit
 * @since 2020-06-19
 */
@Transactional(readOnly = true)
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private EduSubjectMapper eduSubjectMapper;

    private static int count = 0;

    @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
    @Override
    public void saveByExcel(MultipartFile multipartFile) throws IOException {
        InputStream in = multipartFile.getInputStream();
        /*
        使用easyexcel读取Excel表格中的数据
         SubjectData.class: 表格中的数据对象
         new SubjectListener(): 读取数据时用到的监听器
         */
        EasyExcel.read(in, SubjectData.class, new SubjectListener(eduSubjectMapper)).sheet().doRead();
    }

    /**
     * 方式一：普通for循环实现分类层级列表
     * @return
     */
    @Override
    public List<SubjectNestedVO> nestedList() {
        // 获取所有一级类目并排序
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort", "id")
                .eq("parent_id", "0");
        List<EduSubject> oneSubjectList = eduSubjectMapper.selectList(queryWrapper);
        // 获取所有的二级类目
        QueryWrapper<EduSubject> twoQueryWrapper = new QueryWrapper<>();
        twoQueryWrapper.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = eduSubjectMapper.selectList(twoQueryWrapper);
        List<SubjectNestedVO> subjectNestedVOList = new ArrayList<>();
        for (EduSubject eduSubject : oneSubjectList) {
            SubjectNestedVO subjectNestedVO = new SubjectNestedVO();
            BeanUtils.copyProperties(eduSubject, subjectNestedVO);
            subjectNestedVOList.add(subjectNestedVO);

            for (EduSubject subSubject : twoSubjectList) {
//                System.out.println(" 循环次数：" + count++);
                // 如果该二级目录的是当前类目的子级, 则创建一个二级类目视图对象加入到当前类目的children中
                if (Objects.equals(subSubject.getParentId(), eduSubject.getId())) {
                    SubjectNestedVO twoSubjectNestedVO = new SubjectNestedVO();
                    BeanUtils.copyProperties(subSubject, twoSubjectNestedVO);
                    subjectNestedVO.getChildren().add(twoSubjectNestedVO);
                }
            }
            // 对children中的二级类目排序
            Collections.sort(subjectNestedVO.getChildren());
        }
        // 对一级类目排序
        Collections.sort(subjectNestedVOList);
        return subjectNestedVOList;
    }

    /**
     * 方式二：利用递归展示分类层级
     * @return
     */
    public List<SubjectNestedVO> nestedList2() {
        List<EduSubject> eduSubjectList = eduSubjectMapper.selectList(null);
        List<SubjectNestedVO> subjectNestedVOList = new ArrayList<>(eduSubjectList.size());
        for (EduSubject eduSubject : eduSubjectList) {
            SubjectNestedVO subjectNestedVO = new SubjectNestedVO();
            BeanUtils.copyProperties(eduSubject, subjectNestedVO);
            subjectNestedVOList.add(subjectNestedVO);
        }
        /**
         * 递归
         * 获取一个分类的所有子类
         *
         */
        return rescurve(subjectNestedVOList, "0");

    }

    /**
     * 递归实现获取所有子类
     * @param subjectNestedVOList
     * @param id
     * @return
     */
    private List<SubjectNestedVO> rescurve(List<SubjectNestedVO> subjectNestedVOList, String id) {
        List<SubjectNestedVO> childrenSubjectVOList = new ArrayList<>();
        // 遍历传入的分类对象列表
        for (SubjectNestedVO subjectNestedVO : subjectNestedVOList) {
//            System.out.println(" 循环次数：" + count++);
            // 如果该分类对象的父节点id等于当前id, 则说明该分类为当前分类的子类
            if (Objects.equals(id, subjectNestedVO.getParentId())) {
                // 将该分类添加进子类数组中
                childrenSubjectVOList.add(subjectNestedVO);
                // 获取该分类的子类
                subjectNestedVO.setChildren(rescurve(subjectNestedVOList, subjectNestedVO.getId()));
            }
        }
        return childrenSubjectVOList;
    }
}

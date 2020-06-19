package com.welisit.eduservice.demo;

import com.welisit.eduservice.service.EduSubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author welisit
 * @create 2020-06-19 18:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEduSubjectService {

    @Autowired
    private EduSubjectService eduSubjectService;

    @Test
    public void test() {
        System.out.println(eduSubjectService.nestedList());
    }
}

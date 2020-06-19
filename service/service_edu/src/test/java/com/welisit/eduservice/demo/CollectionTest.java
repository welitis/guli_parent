package com.welisit.eduservice.demo;

import com.welisit.eduservice.entity.EduSubject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * @author welisit
 * @create 2020-06-19 16:24
 */
public class CollectionTest {

    @Test
    public void testSort() {
        List<EduSubject> list = new ArrayList<>();
        EduSubject eduSubject1 = new EduSubject();
        EduSubject eduSubject2 = new EduSubject();
        EduSubject eduSubject3 = new EduSubject();
        eduSubject1.setSort(1);
        eduSubject2.setSort(2);
        eduSubject3.setSort(3);
        list.add(eduSubject2);
        list.add(eduSubject1);
        list.add(eduSubject3);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }

    @Test
    public void testTreeMap() {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(2, "aa");
        treeMap.put(3, "aa");
        treeMap.put(1, "aa");
        System.out.println(treeMap);
    }
}

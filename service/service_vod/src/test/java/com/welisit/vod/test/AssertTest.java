package com.welisit.vod.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author welisit
 * @Description TODO
 * @create 2020-06-25 23:00
 */
public class AssertTest {

    @Test
    public void testAssert() {
        String str = "null";
//        Asser str != null;
        String substring = str.substring(0, 2);
        System.out.println(substring);
    }

    @Test
    public void testJoin() {
        List<Integer> list = Arrays.asList(1);
        String s = String.join(",", "2", "3");
        System.out.println(s);
        String s1 = StringUtils.join(list, ",");
        System.out.println(s1);
    }
}

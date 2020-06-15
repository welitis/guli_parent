package com.welisit.commonutils.test;

import com.welisit.commonutils.ResultCode;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author welisit
 * @Description TODO
 * @create 2020-06-13 10:46
 */
public class EnumTest {

    @Test
    public void test() {
        System.out.println(Math.PI);
    }

    @Test
    public void test2() throws IOException {
        try (StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw)) {
            ArithmeticException exception = new ArithmeticException();
            exception.printStackTrace(pw);
            System.out.println("sw.tostring");
            System.out.println(sw.toString());
        }
    }
}

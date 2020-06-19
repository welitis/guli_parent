package com.welisit.eduservice.demo;

import com.alibaba.excel.EasyExcel;
import com.welisit.eduservice.demo.entity.DemoData;
import com.welisit.eduservice.demo.listen.ExcelListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author welisit
 * @Description 测试Excel工具
 * @create 2020-06-18 21:43
 */
public class ExcelTest {

    @Test
    public void main2() {
        // 写法1
        String fileName = "E:\\11.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("写入方法一").doWrite(data());
    }

    @Test
    public void main1() {
        // 写法1：
        String fileName = "E:\\11.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    //循环设置要添加的数据，最终封装到list集合中
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}

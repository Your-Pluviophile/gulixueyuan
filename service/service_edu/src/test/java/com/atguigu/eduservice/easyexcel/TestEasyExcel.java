package com.atguigu.eduservice.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    //循环设置要添加的数据，最终封装到list集合中
    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list; }

    public static void main(String[] args) {
        String filename = "/Users/linhong/test.xlsx";
//        EasyExcel.write(filename,DemoData.class).sheet("学生信息表").doWrite(data());
        EasyExcel.read(filename,DemoData.class,new ReadListener()).sheet().doRead();

    }
}

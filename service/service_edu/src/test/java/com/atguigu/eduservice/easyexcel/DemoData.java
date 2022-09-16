package com.atguigu.eduservice.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty("学生编号")
    private int sno;
    @ExcelProperty("学生姓名")
    private String sname;
}

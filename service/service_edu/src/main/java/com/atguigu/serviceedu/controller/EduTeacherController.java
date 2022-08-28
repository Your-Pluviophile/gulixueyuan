package com.atguigu.serviceedu.controller;


import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-08-22
 */
@RestController
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {
//    注入service
    @Autowired
    private EduTeacherService teacherService;
    //1 查询讲师表所有内容

    @GetMapping("findAll")
    private List<EduTeacher> findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    //2 逻辑删除讲师
    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        return teacherService.removeById(id);
    }

}


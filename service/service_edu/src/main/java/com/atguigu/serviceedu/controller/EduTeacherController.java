package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
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
    private R findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //2 逻辑删除讲师
    @DeleteMapping("{id}")
    public R removeById(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

}


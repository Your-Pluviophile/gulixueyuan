package com.atguigu.serviceedu.service;

import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-17
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();

}

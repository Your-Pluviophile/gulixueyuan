package com.atguigu.serviceedu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.serviceedu.entity.EduSubject;
import com.atguigu.serviceedu.entity.excel.SubjectData;
import com.atguigu.serviceedu.entity.subject.OneSubject;
import com.atguigu.serviceedu.entity.subject.TwoSubject;
import com.atguigu.serviceedu.listener.SubjectExcelListener;
import com.atguigu.serviceedu.mapper.EduSubjectMapper;
import com.atguigu.serviceedu.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-09-17
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //1 添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream in = file.getInputStream();
            //使用new的方式代表，此类不能交由spring来管理
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception  e){
            e.printStackTrace();
        }
    }

    //2 课程分类列表 （树形）
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapper1);

        QueryWrapper<EduSubject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id","0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapper2);

        //封装
        List<OneSubject> finalSubject = new ArrayList<>();
        for (int i = 0; i < oneSubjectList.size(); i++) {
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubject.add(oneSubject);

            List<TwoSubject> twofinalSubjectList = new ArrayList<>();
             for (int j = 0; j < twoSubjectList.size(); j++) {
                EduSubject tSubject = twoSubjectList.get(j);
                //判断二级分类属于哪个一级分类
                 if(tSubject.getParentId().equals(eduSubject.getId())){
                     TwoSubject twoSubject = new TwoSubject();
                     BeanUtils.copyProperties(tSubject,twoSubject);
                     twofinalSubjectList.add(twoSubject);
                 }
            }
             oneSubject.setChildren(twofinalSubjectList);
        }
        return finalSubject;
    }
}

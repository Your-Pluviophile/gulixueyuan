package com.atguigu.serviceoss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.serviceoss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传文件的方法
     * @param file
     * @return
     */
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvator(file);
        return R.ok().data("url",url);
    }
}

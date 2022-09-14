package com.atguigu.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {

    String uploadFileAvator(MultipartFile file);
}

package com.atguigu.serviceoss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.serviceoss.service.OssService;
import com.atguigu.serviceoss.util.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvator(MultipartFile file) {
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);


        String url = null;
        try {


            //获取文件上传输入流
            InputStream inputStream = file.getInputStream();

            //按照日期分类文件
            String dataPath = new DateTime().toString("yyyy/MM/dd");

            //获取文件名称
            String filename = dataPath + "/" + file.getOriginalFilename() + UUID.randomUUID();//避免文件名重复


            //bucket,文件路径或名称，输入流
            ossClient.putObject(bucketName, filename, inputStream);

            //把上传之后的文件路径返回
            url = "http://" + bucketName + "." + endPoint + "/" + filename;

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url;

    }
}

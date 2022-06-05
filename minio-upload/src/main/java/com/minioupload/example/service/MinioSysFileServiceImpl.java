package com.minioupload.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.minioupload.example.Configuration.MinioConfig;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Service
public class MinioSysFileServiceImpl implements ISysFileService {
	
	@Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioClient client;

    /**
     * Local file upload interface
     * @param file Uploaded files
     * @return Access address
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception
    {
        String fileName = file.getOriginalFilename();
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(minioConfig.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build();
        client.putObject(args);
        return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + fileName;
    }

}

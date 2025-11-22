package com.iremeldutar.ToDoBackendApplication.Services.Impl;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServices {

    private final MinioClient minioClient;
    private final String bucketName;

    public FileServices(
            @Value("${minio.server.url}") String url,
            @Value("${minio.server.access-key}") String accessKey,
            @Value("${minio.server.secret-key}") String secretKey,
            @Value("${minio.bucketName}") String bucketName) throws Exception {

        this.bucketName = bucketName;
        this.minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();

        boolean exists = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket(bucketName).build());
        if (!exists) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    public void putObject(String objectKey, InputStream is, long size, String contentType) throws Exception {
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectKey)
                .stream(is, size, -1) 
                .contentType(contentType != null ? contentType : "application/octet-stream")
                .build();

        
        minioClient.putObject(args);

        
        minioClient.statObject(StatObjectArgs.builder()
                .bucket(bucketName).object(objectKey).build());

        log.info("MinIO upload OK -> bucket={}, key={}", bucketName, objectKey);
    }

    public String presignedGetUrl(String objectKey, int minutes) throws Exception {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectKey)
                .method(Method.GET)
                .expiry(minutes, TimeUnit.MINUTES)
                .build();
        return minioClient.getPresignedObjectUrl(args);
    }

    public void deleteObject(String objectKey) throws Exception {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName).object(objectKey).build());
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    




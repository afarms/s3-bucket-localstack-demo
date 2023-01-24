package com.example.s3bucketlocalstackdemo.services;

import org.springframework.web.multipart.MultipartFile;

import com.example.s3bucketlocalstackdemo.services.model.DownloadedResource;

public interface StorageService {
    
    String upload(MultipartFile multipartFile);
    
    DownloadedResource download(String id);
}
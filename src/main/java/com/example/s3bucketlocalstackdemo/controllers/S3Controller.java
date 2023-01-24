package com.example.s3bucketlocalstackdemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.s3bucketlocalstackdemo.services.StorageService;
import com.example.s3bucketlocalstackdemo.services.model.DownloadedResource;

@RestController
@RequestMapping("/s3")
public class S3Controller {
	
	private final StorageService storageService;
	
	@Value("${aws.s3.bucket-name}")
    private String bucketName;

    S3Controller(StorageService storageService) {
        this.storageService = storageService;
    }
	
	@GetMapping("/name")
	public String getBucketName() {
		return this.bucketName;
	}
	
	@PostMapping(value = "/upload", produces = "application/json")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String key = storageService.upload(file);
        return new ResponseEntity<>(key, HttpStatus.OK);
    }
	
	@GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id) {
        DownloadedResource downloadedResource = storageService.download(id);
        
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadedResource.getFileName())
                .contentLength(downloadedResource.getContentLength()).contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(downloadedResource.getInputStream()));
    }
}

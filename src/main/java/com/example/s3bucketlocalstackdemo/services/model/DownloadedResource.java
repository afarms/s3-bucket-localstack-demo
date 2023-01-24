package com.example.s3bucketlocalstackdemo.services.model;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class DownloadedResource {
	private String id;
    private String fileName;
    private Long contentLength;
    private InputStream inputStream;
}

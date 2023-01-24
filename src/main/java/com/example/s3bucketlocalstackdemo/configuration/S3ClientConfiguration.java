package com.example.s3bucketlocalstackdemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class S3ClientConfiguration {
    
    @Value("${aws.s3.endpoint-url}")
    private String endpointUrl;
    
    @Value("${aws.s3.access-key-id}")
    private String accessKeyId;
    
    @Value("${aws.s3.secret-key-id}")
    private String secretKeyId;
    
    @Bean
    AmazonS3 amazonS3() {
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(endpointUrl,
                Regions.EU_WEST_1.getName());
        
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyId, secretKeyId);
        return AmazonS3ClientBuilder.standard()
        		.withEndpointConfiguration(endpointConfiguration)
        		.withCredentials(new AWSStaticCredentialsProvider(credentials))
        		.withPathStyleAccessEnabled(true)
        		.build();
    }
}
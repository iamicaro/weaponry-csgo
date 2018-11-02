package br.com.inmetrics.weaponry.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class WeaponryConfig {

	@Value("${aws.s3.acess.id}")
	private String acessKeyId;
	
	@Value("${aws.s3.acess.secret}")
	private String acessKeySecret;
	
	@Bean
	public AmazonS3 instance() {
		
		BasicAWSCredentials credentials = new BasicAWSCredentials(acessKeyId, acessKeySecret);
		AmazonS3 client = AmazonS3ClientBuilder.standard().withRegion(Regions.SA_EAST_1)
		                 .withCredentials(new AWSStaticCredentialsProvider(credentials)).build();
		return client;
	}	
	
}

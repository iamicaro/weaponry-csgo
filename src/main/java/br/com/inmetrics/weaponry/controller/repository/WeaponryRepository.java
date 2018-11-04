package br.com.inmetrics.weaponry.controller.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

@Service
public class WeaponryRepository {

	@Autowired
	private AmazonS3 s3client;
	
	@Value("${aws.s3.bucketname}")
	private String bucketName;
	
	public List<HashMap<String, String>> findAllWeaponry() {

		ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName);
		List<String> keys = new ArrayList<>();

		ObjectListing objects = s3client.listObjects(listObjectsRequest);

		while(true) {

			List<S3ObjectSummary> summaries = objects.getObjectSummaries();
			if (summaries.size() < 1) {
				break;
			}

			for (S3ObjectSummary item : summaries) {
				if (!item.getKey().endsWith("/"))
					keys.add(item.getKey());
			}
			objects = s3client.listNextBatchOfObjects(objects);
		}
		return preparedToJson(keys);

	}
	
	private List<HashMap<String, String>> preparedToJson(List<String> keys) {
		List<HashMap<String, String>> json = new ArrayList<HashMap<String, String>>();
		
		for(String key : keys) {
			HashMap<String, String> auxiliar = new HashMap<>();
			auxiliar.put("name", getNameWeapon(key));
			auxiliar.put("weapon", key);
			json.add(auxiliar);
		}
		
		return json;
		
	}
	
	private String getNameWeapon(String weapon) {
		if(weapon.contains("m4a4")) {
			return "M4A4";
		} else if(weapon.contains("ak47")) {
			return "AK47";
		}
		
		return "Unregistered weapon.";
		
	}
	
}

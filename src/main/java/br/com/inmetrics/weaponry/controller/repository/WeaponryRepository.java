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
		if(weapon.contains("aug")) {
			return "AUG | Random Acess";
		} else if(weapon.contains("awp")) {
			return "AWP | Acheron";
		} else if(weapon.contains("fiveseven")) {
			return "Five-SeveN | Coolant";
		} else if(weapon.contains("galilar")) {
			return "Galil AR | Cold Fusion";
		} else if(weapon.contains("glock")) {
			return "Glock-18 | Nuclear Garden";
		} else if(weapon.contains("m4a1")) {
			return "M4A1-S | Control Panel";
		} else if(weapon.contains("m4a4")) {
			return "M4A4 | Mainframe";
		} else if(weapon.contains("mag7")) {
			return "MAG-7 | Core Breach";
		} else if(weapon.contains("mp5")) {
			return "MP5 | Co-Processor";
		} else if(weapon.contains("mp7")) {
			return "MP7 | Motherboard";
		} else if(weapon.contains("negev")) {
			return "Negev | Bulkhead";
		} else if(weapon.contains("nova")) {
			return "Nova | Mandrel";
		} else if(weapon.contains("p250")) {
			return "P250 | Facility Draft";
		} else if(weapon.contains("p90")) {
			return "P90 | Facility Negative";
		} else if(weapon.contains("ppbizon")) {
			return "PP-Bizon | Facility Sketch";
		} else if(weapon.contains("tec9")) {
			return "TEC-9 | Remote Control";
		} else if(weapon.contains("ump45")) {
			return "UMP-45 | Facility Dark";
		}
		return "Unregistered weapon.";
		
	}
	
}

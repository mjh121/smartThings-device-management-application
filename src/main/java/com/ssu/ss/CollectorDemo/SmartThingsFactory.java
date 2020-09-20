package com.ssu.ss.CollectorDemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ssu.ss.CollectorDemo.smartthings.MotionBathIn;
import com.ssu.ss.CollectorDemo.smartthings.MotionBathOut;
import com.ssu.ss.CollectorDemo.smartthings.MultipurposeWokeup;
import com.ssu.ss.CollectorDemo.smartthings.MultipurposeFamilyTime;
import com.ssu.ss.CollectorDemo.smartthings.SmartThings;
import com.ssu.ss.CollectorDemo.smartthings.SmartThingsConstants;

public class SmartThingsFactory {
	
	private String endPointUri;
	private String endPointParamd;
	private String accessDefaultUrl;
	private JSONObject smartInfo;
	
	public SmartThingsFactory() {
		loadThingsConfig();
	}
	
	private void loadThingsConfig() {

		JSONParser parser = new JSONParser();
		
		try {
			String path = SmartThingsConstants.CONFIG_PATH;
			Object obj = parser.parse(new FileReader(path + SmartThingsConstants.CONFIG_FILE_NAME));
			
			setsmartInfo((JSONObject) obj);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String endpoints_url = getSmartInfo().get("api").toString();
		// System.out.println("endpoints_url : " + endpoints_url);
		String endpoint_paramd = getSmartInfo().get("access_token").toString();
		// System.out.println("endpoint_paramd : " + endpoint_paramd);

		JSONObject json_response = new RequestURI().RequestGet(endpoints_url, endpoint_paramd, "", 1);
		this.accessDefaultUrl = json_response.get("url").toString();

		getSmartInfo().put("device_uri", json_response.get("uri"));
	}
	
	public SmartThings createSmartThings(String type) {
		SmartThings smart = null;
		if(type.equals(SmartThingsConstants.MOTION_1_ID)) {
			smart = new MotionBathIn(smartInfo);
		}
		else if(type.equals(SmartThingsConstants.MOTION_2_ID)) {
			smart = new MotionBathOut(smartInfo);
		}
//		else if(type.equals(SmartThingsConstants.MOTION_3_ID)) {
//			smart = ??
//		}
		else if(type.equals(SmartThingsConstants.MULTI_2_ID)) {
			smart = new MultipurposeFamilyTime(smartInfo);
		}
		else if(type.equals(SmartThingsConstants.MULTI_3_ID)) {
			smart = new MultipurposeWokeup(smartInfo);
		}
		return smart;
	}
	
	public void setsmartInfo(JSONObject smartInfo) {
		this.smartInfo = smartInfo;
	}
	
	public JSONObject getSmartInfo() {
		return smartInfo;
	}
	
	
}

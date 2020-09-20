package com.ssu.ss.CollectorDemo.smartthings;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;

import com.ssu.ss.CollectorDemo.Constants;
import com.ssu.ss.CollectorDemo.RequestURI;

public class MotionBathOut extends SmartThings {
	private JSONObject smartInfo;
	private JSONObject currentDeviceInfo;
	private JSONObject valueInfo;
	private RequestURI request;
	
	private String deviceUri;
	private String endPointParamd;
	private String deviceHeader;
	private int sensorValue;
	
	public MotionBathOut(JSONObject smartInfo) {
		this.smartInfo = smartInfo;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		deviceUri = smartInfo.get("device_uri").toString() + "/" + SmartThingsConstants.MOTION_2_ID;
		endPointParamd = smartInfo.get("access_token").toString();
		deviceHeader = "Bearer " + smartInfo.get("access_token");
		request = new RequestURI();
	}

	@Override
	public void responseDeviceInfo() {
		// TODO Auto-generated method stub
		while(true) {
			currentDeviceInfo = request.RequestGet(deviceUri, endPointParamd, deviceHeader, 1);
			
			if (currentDeviceInfo == null) {}
			else {
				valueInfo = (JSONObject) currentDeviceInfo.get("value");
				System.out.println("[MotionBathIn] valueInfo : "+ valueInfo);
				String boolValue = valueInfo.get(SmartThingsConstants.MOTION).toString();
				if(boolValue.equals("false")) {
					sensorValue = 0;
				} else {
					sensorValue = 1;
				}
			}
			String timestamp = (String) valueInfo.get("timestamp");
			SimpleDateFormat esDataFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			long regtime=0;
			try {
				regtime = esDataFormat.parse(timestamp).getTime();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JSONObject sensorCollectionInfo = new JSONObject();
			
			sensorCollectionInfo.put("timestamp", timestamp);
			sensorCollectionInfo.put("value", sensorValue);
			sensorCollectionInfo.put("logtype", SmartThingsConstants.TABLE_NAME);
			sensorCollectionInfo.put("sensortype", SmartThingsConstants.MOTION_2_RESPONSE_ID);
			sensorCollectionInfo.put("regTime", ""+regtime);
			
			System.out.println("[MotionBathIn] sensorCollectionInfo : "+ sensorCollectionInfo.toJSONString());
			
			HttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(SmartThingsConstants.URL);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("id", SmartThingsConstants.MOTION_2_RESPONSE_ID));
			params.add(new BasicNameValuePair("type", SmartThingsConstants.TABLE_NAME));
			params.add(new BasicNameValuePair("value", sensorCollectionInfo.toJSONString()));

			
			try { 
				httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			} 
			catch (UnsupportedEncodingException e) 
			{ // writing error to Log 
				e.printStackTrace(); 			
			}
			
			try { 
				HttpResponse response = httpClient.execute(httpPost); 
				HttpEntity respEntity = response.getEntity();
				if (respEntity != null) 
				{ // EntityUtils to get the response content 
					String content = EntityUtils.toString(respEntity); 
					//System.out.println("res : "+content);
				}
			}
			catch (ClientProtocolException e) { 
				// writing exception to log 
				e.printStackTrace();
			} 
			catch (IOException e) { 
				// writing exception to log 
				e.printStackTrace(); 
			}
			
			try {
				sleep(Constants.INTERVAL*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

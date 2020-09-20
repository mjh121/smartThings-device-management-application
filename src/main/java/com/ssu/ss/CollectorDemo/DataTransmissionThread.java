package com.ssu.ss.CollectorDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

import com.ssu.ss.CollectorDemo.smartthings.SmartThingsConstants;

public class DataTransmissionThread extends Thread {
	
	private static File file;
	private String url;
	
	private String sensorId;
	
	public DataTransmissionThread() {
    	url = Constants.URL;
        sensorId = Constants.SENSOR_ID;
	}
	
	public static void readFile() {
		try {
			file = new File(App.class.getResource("\\test.txt").toURI());
			BufferedReader reader = new BufferedReader(new FileReader(file));
			System.out.println(reader.readLine());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int tmp = 0;
		int count = 0;
		int randIdx = 0;
		String[] sensorName = {SmartThingsConstants.MOTION_1_RESPONSE_ID, SmartThingsConstants.MOTION_2_RESPONSE_ID, SmartThingsConstants.MOTION_3_RESPONSE_ID, SmartThingsConstants.MULTI_3_RESPONSE_ID};
		System.out.println("==start transmission==");
		
		while(true) {
			ArrayList<Integer> idxList = new ArrayList<Integer>();
			idxList.add(0);
			idxList.add(1);
			idxList.add(2);
			idxList.add(3);
			
			for(int i=sensorName.length; i>0; i--) {
				randIdx = Math.abs(rand.nextInt()%i);
				
				tmp = Math.abs(rand.nextInt()%2);
				count = (count+1)%sensorName.length;
				HttpClient httpClient = HttpClients.createDefault();
				HttpPost httpPost = new HttpPost(url);
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				System.out.println("[demo] random index : "+ randIdx);
				System.out.println("[demo] idx List : "+ idxList);
				params.add(new BasicNameValuePair("sensorId", sensorName[idxList.get(randIdx)]));
				params.add(new BasicNameValuePair("value", ""+tmp));
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
				idxList.remove(randIdx);
				System.out.println("[demo] remove idx after : "+ idxList);
			}
			try {
				sleep(Math.abs(rand.nextInt()%500+500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

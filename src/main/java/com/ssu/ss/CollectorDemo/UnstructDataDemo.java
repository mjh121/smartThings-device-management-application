package com.ssu.ss.CollectorDemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UnstructDataDemo {
	public UnstructDataDemo() {
	}
	
	public int sendUnstructData() {
//      System.out.println( "Hello World!" );
//      StatusEventTask task = new StatusEventTask();
//      task.start();
  	
  	// test code : spec request, response vlaue check
  	List<NameValuePair> params = new ArrayList<NameValuePair>();
  	HttpClient httpClient = HttpClients.createDefault();
  	
//  	HttpPost httpPost = new HttpPost("http://localhost:8080/ssu/input/unstruct");
  	HttpPost httpPost = new HttpPost("http://localhost:8080/restfulService/search/unstruct");
  	
  	
  	// unstructure data load format
//  	long regtime = System.currentTimeMillis();
//  	String path = "/home/sslab-data/storage/testUnstructdata/vision/";
//  	String name = "u2_20171028_093452_861.avi";
//  	String type = "vision";
//  	
//  	JSONObject jData = new JSONObject();
//  	jData.put("regTime", regtime);
//  	jData.put("path", path);
//  	jData.put("name", name);
//  	jData.put("type", type);
//  	params.add(new BasicNameValuePair("data", jData.toJSONString()));
  	// end data load  	
	
//	// end
	params.add(new BasicNameValuePair("dataType", "voice"));
	params.add(new BasicNameValuePair("key", ""));
	params.add(new BasicNameValuePair("data", ""));
	params.add(new BasicNameValuePair("size", ""+1));
	
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
			if (respEntity != null) { 
				String content = EntityUtils.toString(respEntity);
				JSONParser parser = new JSONParser();
				JSONObject jobj = (JSONObject)parser.parse(content);
//				File tmpfile = new File("C:\\Users\\JongHyeok Mun\\Desktop\\test Input voice Data\\tmpFile\\test_180102.wav");
//				String unstructData = (String)jobj.get("data");
//				byte[] bytes = Base64.decodeBase64(unstructData);
//				FileOutputStream os = new FileOutputStream(tmpfile, true);
//				System.out.println(jobj.get("data").toString().length());
//				System.out.println(content.length());
//				os.write(bytes);
				
				System.out.println("[UnstructurDataDemo] response : "+ jobj);
			}
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClientProtocolException e) { 
			// writing exception to log 
			e.printStackTrace();
		} 
		catch (IOException e) { 
			// writing exception to log 
			e.printStackTrace(); 
		}
		return 0;
    }
}

package com.ssu.ss.CollectorDemo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author oh
 */
public class RequestURI {

	HttpResponse response = null; // ***********
	HttpClient client = null;// ***********

	public JSONObject RequestGet(String path, String param, String header, int option) {

		JSONObject jobj = null;

		try {
			URIBuilder uribuilder = new URIBuilder();
			URI uri = new URI(path);

			if (option == 1) {
				uri = new URIBuilder(uri).addParameter("access_token", param).build();
			}

			client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.addHeader("Authorization", header);

			response = client.execute(httpGet);

			if (response == null) // **
				System.out.println("[SMARTTHINGS-Response] Smartthings Server Error");// **
			int respones_result = response.getStatusLine().getStatusCode();

			// System.out.println("[response]:" + response);
			if (response != null && respones_result == 200) {
				// if (response != null ) {
				Object obj = JSONValue.parse(EntityUtils.toString(response.getEntity()));
				JSONArray jarr = (JSONArray) obj;
				if (header == "") {
					jobj = (JSONObject) jarr.get(0);
				} else
					for (int i = 0; i < jarr.size(); i++)
						jobj = (JSONObject) jarr.get(i);
			}
			httpGet.abort(); // *****

		} catch (ClientProtocolException e) {
			e.printStackTrace();
			e.getMessage();
			System.out.println("[SMARTTHINGS-ClientError]" + e.getMessage());

		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage();
			System.out.println("[SMARTTHINGS-IOException]" + e.getMessage());
			// Server.setServerInstance(1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("[SMARTTHINGS-URISyntaxException]" + e.getMessage());
		} finally {
			client.getConnectionManager().shutdown();
		}
		return jobj;
	}
}
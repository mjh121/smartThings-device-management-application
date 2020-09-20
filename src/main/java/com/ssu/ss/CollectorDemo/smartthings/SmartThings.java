package com.ssu.ss.CollectorDemo.smartthings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class SmartThings extends Thread {
	
	public abstract void init();
	public abstract void responseDeviceInfo();
	
	@Override
	public void run() {
		init();
		responseDeviceInfo();
	}
}


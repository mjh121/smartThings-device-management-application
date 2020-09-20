package com.ssu.ss.CollectorDemo;

public interface Constants {
	
	int INTERVAL = 1; // interval of Sensor data sending 
	
	int STATUS_PROT = 9800;
	String STAUTS_IP = "127.0.0.1";

	String URL = "http://localhost:8080/ssu/input"; // Server address to receive data 
	
	
	
	//Device info
	String SENSOR_ID = "smart01";
	int DEVICE_STATUS_OFF = 0;
	int DEVICE_STATUS_ON = 1;
	
}

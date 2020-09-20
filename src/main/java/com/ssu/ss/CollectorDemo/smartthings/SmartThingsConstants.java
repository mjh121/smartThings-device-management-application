package com.ssu.ss.CollectorDemo.smartthings;

public interface SmartThingsConstants {
	int STATUS_PROT = 9800;
	String STAUTS_IP = "127.0.0.1";

	String URL = "http://localhost:8881/SilverCM/collector"; // Server address to receive data 
	
	// config file info
	public String CONFIG_PATH = "D:\\workspace\\sanghwan_projects\\projects\\CollectorDemo\\src\\main\\resources\\";
	public String CONFIG_FILE_NAME = "smartthings.json";
	// smart sensor info
	public String TABLE_NAME = "smartthing";
	public String MOTION = "motion";
	public String MULTI = "multi";
	
	public String MOTION_1_ID = "motion1";
	public String MOTION_2_ID = "motion2";
	public String MOTION_3_ID = "motion3";
	public String MULTI_2_ID = "multi2";
	public String MULTI_3_ID = "multi3";
	
	public String MOTION_1_RESPONSE_ID = "motion_bathIn";
	public String MOTION_2_RESPONSE_ID = "motion_bathOut";
	public String MOTION_3_RESPONSE_ID = "motion_centerIn";
	public String MULTI_2_RESPONSE_ID = "multipurpose_FamilyTime";
	public String MULTI_3_RESPONSE_ID = "multipurpose_Wokeup";
}

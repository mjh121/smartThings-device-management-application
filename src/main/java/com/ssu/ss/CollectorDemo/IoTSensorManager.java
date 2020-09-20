package com.ssu.ss.CollectorDemo;

import org.apache.http.impl.client.EntityEnclosingRequestWrapper;

import com.ssu.ss.CollectorDemo.smartthings.MotionBathIn;
import com.ssu.ss.CollectorDemo.smartthings.MotionBathOut;
import com.ssu.ss.CollectorDemo.smartthings.MultipurposeWokeup;
import com.ssu.ss.CollectorDemo.smartthings.MultipurposeFamilyTime;
import com.ssu.ss.CollectorDemo.smartthings.SmartThings;
import com.ssu.ss.CollectorDemo.smartthings.SmartThingsConstants;

public class IoTSensorManager {
	
	private SmartThingsFactory factory;
	private SmartThings motionBathIn;
//	private SmartThings motionBathOut;
	private SmartThings multipurposeFamilyTime;
	private SmartThings multipurposeWokeup;
	
	public IoTSensorManager() {
		factory = new SmartThingsFactory();
		
		motionBathIn = factory.createSmartThings(SmartThingsConstants.MOTION_1_ID);
//		motionBathOut = factory.createSmartThings(SmartThingsConstants.MOTION_2_ID);
		multipurposeFamilyTime = factory.createSmartThings(SmartThingsConstants.MULTI_2_ID);
		multipurposeWokeup = factory.createSmartThings(SmartThingsConstants.MULTI_3_ID);
	}
	
	public void startSmartThings() {
		motionBathIn.start();
//		motionBathOut.start();
		multipurposeFamilyTime.start();
		multipurposeWokeup.start();
	}
}

package com.ssu.ss.CollectorDemo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException
    {    	
    	IoTSensorManager ioTSensorManager = new IoTSensorManager(); // real sensor collection
    	ioTSensorManager.startSmartThings();

//    	DataTransmissionThread demo = new DataTransmissionThread(); // demo
//    	demo.start();
    	/* this code is used test for unstructured data sending speed measure. */
//    	//test
//    	UnstructDataDemo dataTest = new UnstructDataDemo();
//    	System.out.println("[UnstructDataDemo] start record");
//    	try {
//    		BufferedWriter recordFile = new BufferedWriter(new FileWriter("client(engine)_request_measure_result(2).txt", true));
//    		
//    		for(int i=1; i<=count; i++) {
//    			preTime = System.currentTimeMillis();
//    			if(i==1) {
//    				startTime = preTime;
//    				recordFile.write("* time unit of the record is milliseconds *"+"\n\n");
//    	    		recordFile.write("0. recording start time : "+startTime+"\n\n");
//    	    		recordFile.write("NO\t preTime\t currentTime\t cost\t total\n");
////    	    		recordFile.write("NO preTime currentTime cost total");
//    			}
//	    		
//	    		dataTest.sendUnstructData();
//	    		afterTime = System.currentTimeMillis();
//	    		cost = afterTime - preTime;
//	    		totalTime += cost;
//	    		recordFile.write(""+i+"\t"+preTime+"\t"+afterTime+"\t"+cost+"\t"+totalTime+"\n");
////	    		recordFile.write(""+i+" "+preTime+" "+afterTime+" "+cost+" "+totalTime+"\n");
//	    		System.out.println("[" +i+ "]" +cost);
//    		}
//    		endTime = afterTime;
//    		recordFile.write("\n[result]\n");
//    		recordFile.write("start : "+startTime+", end : "+endTime+"\n");
//    		recordFile.write("total cost is "+totalTime);
//    		
//    		recordFile.close();
//    	} catch (Exception e) {
//			// TODO: handle exception
//		}
//    	System.out.println("[UnstructDataDemo] end record");
//    	// end test
    }
}

package com.ssu.ss.CollectorDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;

public class SatusTranmissionThread extends Thread {
	private Socket statusSocket = null;
    private OutputStream os = null;
    private OutputStreamWriter osw =null;
    private BufferedWriter bw = null;
    
    private InputStream is =null;
    private InputStreamReader isr = null;
    private BufferedReader br = null;
    
    private int deviceStatus = Constants.DEVICE_STATUS_OFF; // device on/off status, off : 0, on : 1
    private Boolean isConnect = false;
    
	public SatusTranmissionThread() {

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	if(isConnect == false) {
		// when socket not connect, try to new connection
		try {
			statusSocket = new Socket(Constants.STAUTS_IP, Constants.STATUS_PROT);
            os = statusSocket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
        
            is = statusSocket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            
            isConnect = true;
            deviceStatus = Constants.DEVICE_STATUS_ON;
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	} else {
		// socket connect, if. status check signal input, this send current device status to server.
		
		
	}	
		
	}
	
	private int getStatus() {
		return deviceStatus;
	}
}

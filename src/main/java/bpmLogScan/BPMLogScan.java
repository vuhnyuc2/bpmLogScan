package bpmLogScan;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BPMLogScan {
	private BPMLog log;
	
	public BPMLogScan(){
		log = new BPMLog();
	}

	public BPMLog getBPMLog() {
		log.readFromFile("pmr.log");

		return log;
	}
	
	public static void main(String []args){

	}
}

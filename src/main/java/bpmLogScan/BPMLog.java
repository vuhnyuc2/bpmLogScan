package bpmLogScan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BPMLog {
	private HashMap<String,ProcessThread> threads;
	private String[] environmentInformation;
	private ArrayList<LogMessage> logFile;
	public static Pattern newMessageRule = Pattern.compile("^\\[.*");
	public static Pattern threadIDRule = Pattern.compile("^\\[.*[T]\\]");

	public BPMLog(){
		this.threads = new HashMap<String,ProcessThread>();
		this.environmentInformation = null;
		this.logFile = new ArrayList<LogMessage>();
	}

	public BPMLog(String fileName){
		this.threads = new HashMap<String,ProcessThread>();
		this.environmentInformation = null;
		this.readFromFile(fileName);
	}

	public void readFromFile(String fileName){
		this.buildThreads(this.readInLog(fileName));
	}

	public String[] getEnvironmentInformation(){
		return this.environmentInformation;
	}

	public String[] getProcessIDs(){
		return this.threads.keySet().toArray(new String[this.threads.size()]);
	}

	public ProcessThread getProcess(Object threadID){
		return this.threads.get(threadID);
	}

	public LogMessage[] getLogFile(){
		return this.logFile.toArray(new LogMessage[this.logFile.size()]);
	}

	private LinkedList<String> readInLog(String fileName){
		LinkedList<String> log = new LinkedList<String>();
		BufferedReader br = null;
		FileReader fr = null;
		try{
			//fr = new FileReader(fileName);
			br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/"+fileName)));

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				log.add(sCurrentLine);
				if(sCurrentLine.equals("************* End Display Current Environment *************")){
					this.environmentInformation = log.toArray(new String[log.size()]);
					log.clear();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return log;
	}

	private void addMessage(String message){
		Matcher match = BPMLog.threadIDRule.matcher(message);
		if(match.find()){
			int idStart = match.start() + match.end();
			String threadId = message.substring(idStart, idStart+8);
			if(!this.threads.containsKey(threadId)){
				this.threads.put(threadId, new ProcessThread());
			}
			this.threads.get(threadId).pushMessage(message);
			this.logFile.add(new LogMessage(message));
		}
	}

	private void buildThreads(LinkedList<String> log){
		String sCurrentMessage = log.poll();
		while(!log.isEmpty()){
			if(BPMLog.newMessageRule.matcher(log.peek()).find()){
				this.addMessage(sCurrentMessage);
				sCurrentMessage = "";
			}
			sCurrentMessage += log.poll() + "\n";
		}
	}
}



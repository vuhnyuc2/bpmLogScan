package bpmLogScan;

import java.util.ArrayList;
import java.util.HashMap;

public class ProcessThread {
	private ArrayList<LogMessage> thread;
	private HashMap<String, ArrayList<LogMessage>> messages;

	public ProcessThread(){
		thread = new ArrayList<LogMessage>();
		messages = new HashMap<String, ArrayList<LogMessage>>();
	}

	public void pushMessage(String message){
		LogMessage curMessage = new LogMessage(message);
		this.thread.add(curMessage);
		if(!messages.containsKey(curMessage.getMessageType())){
			messages.put(curMessage.getMessageType(), new ArrayList<LogMessage>());
		}

		messages.get(curMessage.getMessageType()).add(curMessage);
	}

	public boolean containsTypeMessages(String messageType){
		return this.messages.containsKey(messageType);
	}

	public int numOfTypeMessages(String messageType){
		return this.messages.get(messageType).size();
	}

	public LogMessage[] getTypeMessages(String messageType){
		return this.messages.get(messageType).toArray(new LogMessage[this.numOfTypeMessages(messageType)]);
	}

	public String toString(){
		String threadToString = "";
		for(LogMessage message : this.thread){
			threadToString += message.getMessage();
		}
		return threadToString;
	}
}

package bpmLogScan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogMessage {
	public static Pattern messageTypeRule = Pattern.compile("^\\[.*[T]\\](\\s+\\w+){2}\\s+");
	private String message;
	private String messageType;
	private String threadID;

	public LogMessage(String message){
		this.message = message;
		Matcher messageTypeMatcher = messageTypeRule.matcher(message);
		Matcher processIDMatcher = BPMLog.threadIDRule.matcher(message);
		if(messageTypeMatcher.find()){
			int messageTypeStart = messageTypeMatcher.start() + messageTypeMatcher.end();
			this.messageType = message.substring(messageTypeStart, messageTypeStart+1);
		}
		if(processIDMatcher.find()) {
			int idStart = processIDMatcher.start() + processIDMatcher.end();
			this.threadID = message.substring(idStart, idStart + 8);
		}
	}

	public String getMessage(){
		return this.message;
	}

	public String getMessageType(){
		return this.messageType;
	}

	public String toString(){
		return this.message;
	}

	public String getThreadID(){
		return this.threadID;
	}

	public int threadIDIndex(){
		Matcher processIDMatcher = BPMLog.threadIDRule.matcher(message);
		if(processIDMatcher.find()){
			int idStart = processIDMatcher.start() + processIDMatcher.end();
			return idStart;
		}
		return -1;
	}
}

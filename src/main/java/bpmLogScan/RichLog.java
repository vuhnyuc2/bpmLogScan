package bpmLogScan;

import java.util.regex.Matcher;

/**
 * Created by VasilUhnyuck on 6/27/2017.
 */
public class RichLog {

    String innerHtml;
    String rawLog;

    public RichLog(String raw) {
        innerHtml = "";
        rawLog = raw;
        generateRichLog();
    }

    private void generateRichLog() {


    }
}

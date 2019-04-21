package step3vmgit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InstructionUtils {
	
	// 현재 위치 가져오기
	public String getCurrentPath() {
		String currentPath = System.getProperty("user.dir");
		return currentPath;
	}
	
	// 현재 시간 가져오기
	public String getCurrentTime() {
		long curr = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(new Date(curr));
	}
	
	// 로그 파일 만들기
	public void createLogFile(String path, String fileContent) throws IOException {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(path + "commitLog.txt"));
	    writer.write(fileContent);
	    writer.close();
	}
	
	// 문자열 형태로 로그 메시지 반환
	public String getCommitLog(Map<String, List<String>> log, String key) {
		
		List<String> history = log.get(key);
		String commitLog = "\r\n" + "timeStamp : " + history.get(0) 
						+ "\r\n" + "commitMessage : " + history.get(1);
		
		return commitLog;
	}
	
}

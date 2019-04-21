package step3vmgit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoteArea {
	
	// 임의의 remote path 설정
	String RemotePath = "C:\\Users\\nextu\\Desktop\\remote";
	// 커밋 히스토리
	private Map<String, List<String>> commitHistoryList = new HashMap<>();
	
	public String getRemotePath() {
		return RemotePath;
	}
	public void setRemotePath(String remotePath) {
		RemotePath = remotePath;
	}
	public Map<String, List<String>> getCommitHistoryList() {
		return commitHistoryList;
	}
	public void setCommitHistoryList(Map<String, List<String>> commitHistoryList) {
		this.commitHistoryList = commitHistoryList;
	}
}

package step3vmgit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GitArea {
	
	private Map<String, String> workingDirectoryList = new HashMap<>();
	private Map<String, String> stagingAreaList = new HashMap<>();
	private Map<String, List<String>> gitRepositoryList = new HashMap<>();

	public Map<String, String> getWorkingDirectoryList() {
		return workingDirectoryList;
	}
	public void setWorkingDirectoryList(Map<String, String> workingDirectoryList) {
		this.workingDirectoryList = workingDirectoryList;
	}
	public Map<String, String> getStagingAreaList() {
		return stagingAreaList;
	}
	public void setStagingAreaList(Map<String, String> stagingAreaList) {
		this.stagingAreaList = stagingAreaList;
	}
	public Map<String, List<String>> getGitRepositoryList() {
		return gitRepositoryList;
	}
	public void setGitRepositoryList(Map<String, List<String>> gitRepositoryList) {
		this.gitRepositoryList = gitRepositoryList;
	}
}

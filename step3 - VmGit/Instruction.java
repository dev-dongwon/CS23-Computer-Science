package step3vmgit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Instruction {
	
	InstructionUtils instructionUtils;
	GitArea gitArea;
	RemoteArea remoteArea;
	
	public Instruction() {
		this.instructionUtils = new InstructionUtils();
		this.gitArea = new GitArea();
		this.remoteArea = new RemoteArea();
	}
	
	public void init(String repositoryName) {
	
		String currentPath = instructionUtils.getCurrentPath();
		File repository = new File(currentPath + "\\" + repositoryName);
		
		if (repository.mkdir()) {
			System.out.println("created [" + repositoryName + "] repository");
		} else {
			System.out.println("This repository already exists");
		}
	}
	
	
	public void status(String repositoryName, boolean checkoutStatus, String area) {
		
		File repository = new File(repositoryName);
		File[] fileList = repository.listFiles();
		
		if (checkoutStatus || (area.equals("local") && checkoutStatus)) {
			
			Iterator<String> workingDirectoryKeys = this.gitArea.getWorkingDirectoryList().keySet().iterator();
			Iterator<String> stagingAreaKeys = this.gitArea.getStagingAreaList().keySet().iterator();
			Iterator<String> gitRepoKeys = this.gitArea.getGitRepositoryList().keySet().iterator();
			
			System.out.println("---Working Directory/");
			while (workingDirectoryKeys.hasNext()) {
				String key = workingDirectoryKeys.next();
				System.out.println(key + "  " + this.gitArea.getWorkingDirectoryList().get(key));
			}
			
			System.out.println("---Staging area/");
			while (stagingAreaKeys.hasNext()) {
				String key = stagingAreaKeys.next();
				System.out.println(key + "  " + this.gitArea.getStagingAreaList().get(key));
			}
			
			System.out.println("---Git repositories/");
			while (gitRepoKeys.hasNext()) {
				String key = gitRepoKeys.next();
				System.out.println(key + "  " + this.gitArea.getGitRepositoryList().get(key));
			}
			
		} else if (area.equals("remote") && checkoutStatus) {
			repository = new File(this.remoteArea.RemotePath + "\\" + repositoryName);
			fileList = repository.listFiles();
			
			if (repository.exists()) {
				for (File file : fileList) {
					if (file.isDirectory()) {
						System.out.println(file.getName() + "/");
					} else {
						System.out.println(file.getName());
					}
				}
			} else {
				System.out.println("해당 저장소가 존재하지 않습니다");
			}
			
		} else {

			if (repositoryName == "") {
				repository = new File(instructionUtils.getCurrentPath());
				fileList = repository.listFiles();
			
			}
			
			if (fileList.length == 0) {
				System.out.println("this repository is exist, but is empty");
			}
			
			for (File file : fileList) {
				if (file.isDirectory()) {
					System.out.println(file.getName() + "/");
				} else {
					System.out.println(file.getName());
				}
			}
		}
	}
	
	public String checkout(String repositoryName) {
		String repoName = "";
		File repository = new File(repositoryName);
		
		if (repository.exists()) {
			repoName += repositoryName + "";
		}
		
		return repoName;
	}
	
	public void newFile(String checkout, String fileName) throws IOException {
		File file = new File(instructionUtils.getCurrentPath() + "\\" + checkout + "\\" + fileName);

		if (file.createNewFile()) {
			String log = "untracked " + " " + this.instructionUtils.getCurrentTime();
			this.gitArea.getWorkingDirectoryList().put(fileName, log);
			System.out.println(log);
		} else {
			System.out.println("file already exists");
		}
	}
	
	public void add(String fileName) {
		
		String targetFileLog = this.gitArea.getWorkingDirectoryList().get(fileName);
		
		targetFileLog = "Staged " + " " + this.instructionUtils.getCurrentTime();
		this.gitArea.getStagingAreaList().put(fileName, targetFileLog);
		this.gitArea.getWorkingDirectoryList().remove(fileName);
		this.gitArea.setWorkingDirectoryList(this.gitArea.getWorkingDirectoryList());
	}
	
	public void commit(String commitLog) {
		
		Iterator<String> stagingAreaKeys = this.gitArea.getStagingAreaList().keySet().iterator();
		List<String> fileLog = new ArrayList<>();
		
		while (stagingAreaKeys.hasNext()) {
			String key = stagingAreaKeys.next();
			String fileStatuslog = this.gitArea.getStagingAreaList().get(key);
			fileStatuslog = "Unmodified " + " " + this.instructionUtils.getCurrentTime();
			
			fileLog.add(fileStatuslog);
			fileLog.add(commitLog);
			
			this.gitArea.getGitRepositoryList().put(key, fileLog);
		}
		
		while (stagingAreaKeys.hasNext()) {
			String key = stagingAreaKeys.next();
			this.gitArea.getStagingAreaList().remove(key);
		}
		
		
		this.gitArea.setStagingAreaList(this.gitArea.getStagingAreaList());
	}
	
	public void log() {
		Iterator<String> gitRepoKeys = this.gitArea.getGitRepositoryList().keySet().iterator();
		while (gitRepoKeys.hasNext()) {
			String key = gitRepoKeys.next();
			System.out.println("commit " + "\"" +this.gitArea.getGitRepositoryList().get(key).get(1) + "\"");
			System.out.println("fileName : " + key + "  " + this.gitArea.getGitRepositoryList().get(key).get(0));
		}
	}
	
	public void touch(String fileName) {
		
		if (this.gitArea.getGitRepositoryList().get(fileName) != null) {
			String fileStatusLog = this.gitArea.getGitRepositoryList().get(fileName).get(0);
			fileStatusLog = "Modified " + this.instructionUtils.getCurrentTime();
			
			this.gitArea.getWorkingDirectoryList().put(fileName, fileStatusLog);
			
			this.gitArea.getGitRepositoryList().remove(fileName);
			this.gitArea.setGitRepositoryList(this.gitArea.getGitRepositoryList());
		}
	}
	
	public void push(String checkout) throws IOException {
		System.out.println("now push commits...");
		
		Iterator<String> gitRepoKeys = this.gitArea.getGitRepositoryList().keySet().iterator();
		
		File repository = new File(this.remoteArea.RemotePath + "\\" + checkout);
		String log = "\r\n";
		
		if (repository.mkdir()) {
			System.out.println("copy repositories");
			while (gitRepoKeys.hasNext()) {
				String key = gitRepoKeys.next();
				log += "fileName : "+ key;
				log += this.instructionUtils.getCommitLog(this.gitArea.getGitRepositoryList(), key) + "\r\n";
				
				
				File source = new File(this.instructionUtils.getCurrentPath() + "\\" + checkout + "\\" + key);
				File dest = new File(this.remoteArea.RemotePath + "\\" + checkout + "\\" + key);
				
				Files.copy(source.toPath(), dest.toPath());
				
				this.remoteArea.getCommitHistoryList().put(key, this.gitArea.getGitRepositoryList().get(key));
				System.out.println(key + " is pushed");
			}
			
			this.remoteArea.setCommitHistoryList(this.remoteArea.getCommitHistoryList());
			// 로그 파일
			this.instructionUtils.createLogFile(this.remoteArea.RemotePath + "\\" + checkout + "\\", log);

		} else {
			System.out.println("This repository already exists");
		}

		
	}
	
	public void clone(String repositoryName, String localName) throws IOException {

		Iterator<String> remoteKeys = this.remoteArea.getCommitHistoryList().keySet().iterator();
		File localRepository = new File(localName);
		
		if (localRepository.mkdir()) {
			System.out.println("Cloning " + repositoryName + " from " + localName + "...");

			while (remoteKeys.hasNext()) {
				String key = remoteKeys.next();

				String log = "untrackted " + " " + this.instructionUtils.getCurrentTime();
				
		        File source = new File(this.remoteArea.RemotePath + "\\" + repositoryName + "\\" + key);
		        File dest = new File(localName + "\\" + key);
		        
		        Files.copy(source.toPath(), dest.toPath());
		        this.gitArea.getWorkingDirectoryList().put(key, log);
			}
			
		} else {
			System.out.println("This repository already exists");
		}
		
		this.gitArea.setWorkingDirectoryList(this.gitArea.getWorkingDirectoryList());
		
	}
	
	public void delete(String repositoryName) {
		String currentPath = instructionUtils.getCurrentPath();
		File target = new File(currentPath + "\\" + repositoryName);
		
		if (target.exists()) {
			System.out.print("정말 삭제하시겠습니까? [Y/n] : ");
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String answer = scanner.nextLine();
			
			if ("Y".equals(answer)) {
				
				if(target.isDirectory()){ //파일이 디렉토리인지 확인
	                
	                File[] files = target.listFiles();
	                 
	                for( int i=0; i<files.length; i++){
	                    if( files[i].delete() ){
	                        System.out.println(files[i].getName()+" 삭제성공");
	                    }else{
	                        System.out.println(files[i].getName()+" 삭제실패");
	                    }
	                }
	            }
				
	            if(target.delete()){
	                System.out.println("저장소 삭제 성공");
	            } else{
	                System.out.println("저장소 삭제 실패");
	            }

			} else {
				System.out.println("삭제를 취소했습니다");
			}
			
		} else {
			System.out.println("저장소가 존재하지 않습니다");
		}
		
	}
	
}

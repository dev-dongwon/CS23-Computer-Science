package step3vmgit;

import java.io.File;

public class Instruction {
	
	InstructionUtils instructionUtils = new InstructionUtils();
	
	public Instruction() {
		this.instructionUtils = new InstructionUtils();
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
	
	public void status(String repositoryName) {
		
		File repository = new File(repositoryName);
		File[] fileList = repository.listFiles();
		
		if (fileList == null) {
			repository = new File(instructionUtils.getCurrentPath());
			fileList = repository.listFiles();
		}
		
		for (File file : fileList) {
			if (file.isDirectory()) {
				System.out.println(file.getName() + "/");
			} else {
				System.out.println(file.getName());
			}
		}
	}
	
	public String checkout(String repositoryName) {
		String repoName = "";
		File repository = new File(repositoryName);
		
		if (repository.exists()) {
			repoName += repositoryName;
		}
		
		return repoName;
	}
}

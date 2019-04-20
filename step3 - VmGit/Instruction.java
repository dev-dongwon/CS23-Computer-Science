package step3vmgit;

import java.io.File;

public class Instruction {
	
	Utils utils;
	
	public Instruction() {
		this.utils = new Utils();
	}
	
	public void init(String repositoryName) {
		String currentPath = utils.getCurrentPath();
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
		
		try {
		
			for (File file : fileList) {
				System.out.println(file);
			}

		} catch (NullPointerException e) {
			File localRepository = new File(utils.getCurrentPath());
			File[] localRepos = localRepository.listFiles();
			for (File file : localRepos) {
				System.out.println(file);
			}
		}
	}	
}

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
}

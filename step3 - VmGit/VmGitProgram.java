package step3vmgit;

import java.io.IOException;
import java.util.Scanner;

public class VmGitProgram {

	Instruction instruction;
	VmGitProgramUtils gitProgramUtils;

	Scanner scanner = new Scanner(System.in);
	
	public VmGitProgram() {
		this.instruction = new Instruction();
		this.gitProgramUtils = new VmGitProgramUtils();
	}
	
	public void runProgram() throws IOException {
		while (true) {

			if (gitProgramUtils.getCheckout().equals("")) {
				System.out.print(">");
			} else {
				System.out.print(this.gitProgramUtils.getCheckout() + ">");
			}
			
			String inputInstruction = scanner.nextLine();
			
			try {
				gitProgramUtils.decodeInstruction(this.instruction, inputInstruction);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("명령어를 다시 입력해주세요");
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		VmGitProgram vmGit = new VmGitProgram();
		vmGit.runProgram();
	}

}

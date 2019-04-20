package step3vmgit;

public class VmGitProgramUtils {
	
	public String[] makeCommandArray(String inputInstruction) {
		String[] commandList = inputInstruction.split("\\s+");
		return commandList;
	}
	
	public void decodeInstruction(Instruction instruction, String inputInstruction) {
		String[] commandList = makeCommandArray(inputInstruction);
		String mainInstruction = commandList[0];
		
		switch (mainInstruction) {
		case "init":
			String repositoryName = commandList[1];
			instruction.init(repositoryName);
			break;
		default:
			break;
		}
	}

}

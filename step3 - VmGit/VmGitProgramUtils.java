package step3vmgit;

import java.io.IOException;
import java.util.Scanner;

public class VmGitProgramUtils {

	private String checkout = "";
	
	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
	// 명령어 split 후 배열로 반환
	public String[] makeCommandArray(String inputInstruction) {
		String[] commandList = inputInstruction.split("\\s+");
		return commandList;
	}
	
	// checkout 상태인지 boolean 반환
	public boolean isCheckout(String checkout) {
		boolean checkoutFlag = true;
		
		if (checkout.equals("")) {
			checkoutFlag = false;
		}
		return checkoutFlag;
	}
	
	// 명령어 해독기
	public void decodeInstruction(Instruction instruction, String inputInstruction) throws IOException {
		String[] commandList = makeCommandArray(inputInstruction);
		String mainInstruction = commandList[0];
		
		switch (mainInstruction) {
		
		case "new":
			if (isCheckout(this.checkout)) {
				instruction.newFile(this.checkout, commandList[1]);
			}
			
			break;
		
		case "add":
			if (isCheckout(this.checkout)) {
				instruction.add(commandList[1]);
			}
		
			break;
			
		case "commit":
			
			if (isCheckout(this.checkout)) {
				instruction.commit(commandList[1]);
			}
			
			break;
			
		case "init":
			String repositoryNameForInit = commandList[1];
			instruction.init(repositoryNameForInit);
			
			break;

		case "status":
			
			boolean ischeckout = this.isCheckout(this.getCheckout());
			
			try {
			
				String area = commandList[1];
				if (commandList.length == 1) {
					instruction.status("", ischeckout, area);
					break;
				}
				
				if ("local".equals(area)) {
					try {
						String repositoryNameForStatus = commandList[2];
						instruction.status(repositoryNameForStatus, ischeckout, area);
					} catch (ArrayIndexOutOfBoundsException e) {
						instruction.status("", ischeckout, area);
					} catch (NullPointerException e) {
						instruction.status("", ischeckout, area);
					}
					
				} else if ("remote".equals(area)) {
					String repositoryName = commandList[2];
					instruction.status(repositoryName, false, area);
				}

			} catch (Exception e) {
				instruction.status("", ischeckout, "");
			}
			
			break;

		case "checkout":
			
			try {
				String repositoryNameForCheckout = commandList[1];
				this.setCheckout(instruction.checkout(repositoryNameForCheckout));
			} catch (ArrayIndexOutOfBoundsException e) {
				String repositoryNameForCheckout = "";
				this.setCheckout(instruction.checkout(repositoryNameForCheckout));
			}
			
			break;
		
		case "log":
			
			if (isCheckout(this.checkout)) {
				instruction.log();
			}
			
			break;
			
		case "touch":
			
			if (isCheckout(this.checkout)) {
				instruction.touch(commandList[1]);
			}

			break;
			
		case "push":
			if (isCheckout(this.checkout)) {
				instruction.push(this.checkout);
			}
			break;

		case "clone":
			
			String repoName = commandList[1];
			String localName = commandList[2];

			instruction.clone(repoName, localName);
			break;
			
		case "delete":
			instruction.delete(commandList[1]);
			
			break;
		case "exit":
			System.out.println("goodBye VmGit");
			System.exit(0);
			break;
		default:
			System.out.println("명령어를 다시 입력해주세요");
			break;
		}
	}

}

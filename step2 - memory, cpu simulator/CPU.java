package step2ComputerSimulator;

public class CPU {
	
	Memory memory;
	Register register;
	Instruction instruction;
	ALU ALU;

	public CPU(Memory memory) {
		Register register = new Register();
		Instruction instruction = new Instruction();
		ALU ALU = new ALU();
		
		this.memory = memory;
		this.register = register;
		this.instruction = instruction;
		this.ALU = ALU;
	}

	public String BinaryArrayToString(int[] binaryArr) {
		String binaryStr = "";
		for (int i=0; i<binaryArr.length; i++) {
			binaryStr += binaryArr[i];
		}

		return binaryStr;
	}
	
	public int[] StringTo16bitArr(String binaryStr) {
		int[] result = new int[16];
		for (int i=0; i<binaryStr.length(); i++) {
			result[i] = Character.getNumericValue(binaryStr.charAt(i));
		}
		
		return result;
	}
	
	public int getRegisterNumber(int[] registerBit) {
		int key = 0;
		int Num2bit = 1;
		
		for (int i=registerBit.length-1; i>=0; i--) {
			key += registerBit[i] * Num2bit;
			Num2bit *= 2;
		}
		return key;
	}
	
	public void reset() {
		Register register = new Register();
		this.register = register;
	}
	
	public int[] fetch() {
		this.register.PC += 1;
		return this.memory.fetch(this.register.PC);
	}
	
	public Register[] dump() {
		Register[] registers = {this.register};
		return registers;
	}
	
	
	public void decoder(int bit, int[] data) {
		if (bit == 1) this.register.R1 = data;
		else if (bit == 2) this.register.R2 = data;
		else if (bit == 3) this.register.R3 = data;
		else if (bit == 4) this.register.R4 = data;
		else if (bit == 5) this.register.R5 = data;
		else if (bit == 6) this.register.R6 = data;
		else if (bit == 7) this.register.R7 = data;
	}

	public int[] decoder(int bit) {
		if (bit == 1) return this.register.R1;
		else if (bit == 2) return this.register.R2;
		else if (bit == 3) return this.register.R3;
		else if (bit == 4) return this.register.R4;
		else if (bit == 5) return this.register.R5;
		else if (bit == 6) return this.register.R6;
		else if (bit == 7) return this.register.R7;
		return null;
	}
	
	public void execute(int[] IR) {
		int bit = 0;
		int binary = 1;
		
		for (int i=3; i>=0; i--) {
			bit += IR[i] * binary;
			binary *= 2;
		}
		
		switch (bit) {
		case 1:
			instruction.LOAD(this, IR);
			break;
		case 2:
			instruction.LOAD(this, IR);
			break;
		case 3:
			instruction.STORE(this, IR);
			break;
		case 4:
			instruction.STORE(this, IR);
			break;
		case 5:
			instruction.AND(this, IR);
			break;
		case 6:
			instruction.OR(this, IR);
			break;
//		case 7:
//			instruction.ADD(this, IR);
//			break;
//		case 8:
//			instruction.ADD(this, IR);
//			break;
//		case 9:
//			instruction.SUB(this, IR);
//			break;
//		case 10:
//			instruction.SUB(this, IR);
//			break;
//		case 11:
//			instruction.MOV(this, IR);
		default:
			break;
		}
	}
	
}

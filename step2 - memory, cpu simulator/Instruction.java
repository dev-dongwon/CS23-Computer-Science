package step2ComputerSimulator;

public class Instruction {
	
	public void LOAD(CPU cpu, int[] IR) {
		int[] dstRegBit = {IR[4], IR[5], IR[6]};
		int[] baseRegBit = {IR[7], IR[8], IR[9]};
		int[] baseReg = cpu.decoder(cpu.getRegisterNumber(baseRegBit));
		
		String offsetRegBinary = "";

		if (IR[10] == 0) {
			int[] offsetRegBit = {IR[13], IR[14], IR[15]};
			int[] offsetReg = cpu.decoder(cpu.getRegisterNumber(offsetRegBit));
			offsetRegBinary = cpu.BinaryArrayToString(offsetReg);
		} else {
			int[] offsetValue = {IR[11], IR[12], IR[13], IR[14], IR[15]};
			offsetRegBinary = cpu.BinaryArrayToString(offsetValue);
		}
		
		String baseRegBinary = cpu.BinaryArrayToString(baseReg);
		Integer address = cpu.ALU.ADD(Integer.valueOf(baseRegBinary, 2), Integer.valueOf(offsetRegBinary, 2)) + 0x10000;
		int[] result = cpu.memory.peek(Integer.toBinaryString(address));

		cpu.decoder(cpu.getRegisterNumber(dstRegBit), result);
	}
	
	public void STORE(CPU cpu, int[] IR ) {
		int[] dstRegBit = {IR[4], IR[5], IR[6]};
		int[] dstRegData = cpu.decoder(cpu.getRegisterNumber(dstRegBit));
		
		int[] baseRegBit = {IR[7], IR[8], IR[9]};
		int[] baseRegData = cpu.decoder(cpu.getRegisterNumber(baseRegBit));

		String baseRegBinary = cpu.BinaryArrayToString(baseRegData);
		String offsetRegBinary = "";

		if (IR[10] == 0) {
			int[] offsetRegBit = {IR[13], IR[14], IR[15]};
			int[] offsetReg = cpu.decoder(cpu.getRegisterNumber(offsetRegBit));
			offsetRegBinary = cpu.BinaryArrayToString(offsetReg);
		} else {
			int[] offsetValue = {IR[11], IR[12], IR[13], IR[14], IR[15]};
			offsetRegBinary = cpu.BinaryArrayToString(offsetValue);
		}
		
		Integer address = cpu.ALU.ADD(Integer.valueOf(baseRegBinary, 2), Integer.valueOf(offsetRegBinary, 2));
		cpu.memory.store(address, dstRegData);
	}
	
	
	public void AND(CPU cpu, int[] IR) {
		int[] op1RegBit = {IR[7], IR[8], IR[9]};
		int[] op1RegData = cpu.decoder(cpu.getRegisterNumber(op1RegBit));

		int[] op2RegBit = {IR[13], IR[14], IR[15]};
		int[] op2RegData = cpu.decoder(cpu.getRegisterNumber(op2RegBit));

		int[] andResult = cpu.ALU.AND(op1RegData, op2RegData);
		
		int[] dstRegBit = {IR[4], IR[5], IR[6]};
		cpu.decoder(cpu.getRegisterNumber(dstRegBit), andResult);
	}
	
	public void OR(CPU cpu, int[] IR) {
		int[] op1RegBit = {IR[7], IR[8], IR[9]};
		int[] op1RegData = cpu.decoder(cpu.getRegisterNumber(op1RegBit));

		int[] op2RegBit = {IR[13], IR[14], IR[15]};
		int[] op2RegData = cpu.decoder(cpu.getRegisterNumber(op2RegBit));

		int[] andResult = cpu.ALU.OR(op1RegData, op2RegData);
		
		int[] dstRegBit = {IR[4], IR[5], IR[6]};
		cpu.decoder(cpu.getRegisterNumber(dstRegBit), andResult);
	}
	
	public void ADD(CPU cpu, int[] IR) {

		int[] op1RegBit = {IR[7], IR[8], IR[9]};
		int[] op1RegData = cpu.decoder(cpu.getRegisterNumber(op1RegBit));

		String op1RegBinary = cpu.BinaryArrayToString(op1RegData);
		String op2RegBinary = "";
		
		if (IR[10] == 0) {
			int[] op2RegBit = {IR[13], IR[14], IR[15]};
			int[] op2RegData = cpu.decoder(cpu.getRegisterNumber(op2RegBit));
			op2RegBinary = cpu.BinaryArrayToString(op2RegData);
		} else {
			int[] op2RegBit = {IR[11], IR[12], IR[13], IR[14], IR[15]};
			op2RegBinary = cpu.BinaryArrayToString(op2RegBit);
		}
		
		Integer result = cpu.ALU.ADD(Integer.valueOf(op1RegBinary, 2), Integer.valueOf(op2RegBinary, 2));
		String answer = Integer.toBinaryString(result);
		
		int[] dstRegBit = {IR[4], IR[5], IR[6]};
		cpu.decoder(cpu.getRegisterNumber(dstRegBit), cpu.StringTo16bitArr(answer));
	}

	public void SUB(CPU cpu, int[] IR) {
		
		int[] op1RegBit = {IR[7], IR[8], IR[9]};
		int[] op1RegData = cpu.decoder(cpu.getRegisterNumber(op1RegBit));
		String op1RegBinary = cpu.BinaryArrayToString(op1RegData);
		String op2RegBinary = "";
		
		if (IR[10] == 0) {
			int[] op2RegBit = {IR[13], IR[14], IR[15]};
			int[] op2RegData = cpu.decoder(cpu.getRegisterNumber(op2RegBit));
			op2RegBinary = cpu.BinaryArrayToString(op2RegData);
		} else {
			int[] op2RegBit = new int[16];
			int index = 10;
			
			for (int i=11; i<op2RegBit.length; i++) {
				op2RegBit[i] = IR[index];
				index++;
			}
		}
		
		Integer result = cpu.ALU.SUB(Integer.valueOf(op1RegBinary, 2), Integer.valueOf(op2RegBinary, 2));
		
		
		// 음수일 때 2의 보수(1의 보수 + 1) 취해주고 저장 (음수로 binary 처리하면 java에서는 32비트로 처리가 된다.... 그래서 직접 해줘야 한다. 도대체 왜!!!)
		if (result < 0) {
			result = ~result + 1;
		}

		String answer = Integer.toBinaryString(result);
		int[] dstRegBit = {IR[4], IR[5], IR[6]};
		cpu.decoder(cpu.getRegisterNumber(dstRegBit), cpu.StringTo16bitArr(answer));
	}
	
	public void MOV(CPU cpu, int[] IR) {
		
		int[] dstRegBit = {IR[4], IR[5], IR[6]};
		
		int[] opValueBit = new int[16];
		int index = 7;
		
		for (int i=7; i<opValueBit.length; i++) {
			opValueBit[i] = IR[index];
			index++;
		}
		
		cpu.decoder(cpu.getRegisterNumber(dstRegBit), opValueBit);
	}



	
}

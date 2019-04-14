package step2ComputerSimulator;

import java.util.Arrays;

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
			int[] offsetData = cpu.decoder(cpu.getRegisterNumber(offsetValue));
			offsetRegBinary = cpu.BinaryArrayToString(offsetData);
		}
		
		String baseRegBinary = cpu.BinaryArrayToString(baseReg);
		Integer address = cpu.ALU.ADD(Integer.valueOf(baseRegBinary, 2), Integer.valueOf(offsetRegBinary, 2));
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
			int[] offsetData = cpu.decoder(cpu.getRegisterNumber(offsetValue));
			offsetRegBinary = cpu.BinaryArrayToString(offsetData);
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
	
	
	
	
	
	
//	
//	public void OR(int[] dstReg, int[] opReg1, int[] opReg2) {
//		dstReg = this.ALU.OR(opReg1, opReg2);
//	}
//	
//	public void ADD(int[] dstReg, int[] opReg1, int[] opReg2) {
//		String baseRegBinary = BinaryArrayToString(opReg1);
//		String offsetRegBinary = BinaryArrayToString(opReg2);
//		
//		Integer value = ALU.ADD(Integer.valueOf(baseRegBinary, 2), Integer.valueOf(offsetRegBinary, 2));
//		dstReg = StringTo16bitArr(Integer.toBinaryString(value));
//	}
//	
//	
//	public void SUB(int[] dstReg, int[] opReg1, int[] opReg2) {
//		String opReg1Binary = BinaryArrayToString(opReg1);
//		String opReg2Binary = BinaryArrayToString(opReg2);
//		
//		Integer value = ALU.SUB(Integer.valueOf(opReg1Binary, 2), Integer.valueOf(opReg2Binary, 2));
//		dstReg = StringTo16bitArr(Integer.toBinaryString(value));
//	}
	



	
}

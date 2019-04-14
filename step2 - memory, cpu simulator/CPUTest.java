package step2ComputerSimulator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class CPUTest {
	
	// test 계획 필요
	
	@Test
	public void loadTest() {
		Memory memory = new Memory();
		ALU ALU = new ALU();
		Instruction instruction = new Instruction();
		Register register = new Register();
		CPU cpu = new CPU(memory, register, ALU, instruction);
		
		int[] baseReg = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0};
		int[] offsetReg = {0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0};
		
		int[] data = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};

		// LOAD, R1, R2, R3 => R2 + R3 의 주소 메모리값을 읽어서 R1로 로드
		int[] instructionBit = {0,0,0,1,0,0,1,0,1,0,0,0,0,0,1,1};
		
		cpu.memory.getMEMORY_MODEL()[0][30] = data;

		cpu.register.R2 = baseReg;
		cpu.register.R3 = offsetReg;
		
		cpu.execute(instructionBit);
		assertArrayEquals(data, cpu.register.R1);
	}
	
	@Test
	public void storeTest() {
		
	}

}

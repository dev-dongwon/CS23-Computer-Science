package step2ComputerSimulator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class CPUTest {
	
	// test 계획 필요
	
	@Test
	public void loadTest() {
		Memory memory = new Memory();
		CPU cpu = new CPU(memory);
		
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
		Memory memory = new Memory();
		CPU cpu = new CPU(memory);
		
		int[] baseReg = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0};
		int[] offsetReg = {0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0};
		
		int[] data = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};

//		STORE R5, R3, R4 ==> 0011 101 011 000 100 => R5 값을 R3 + R4 메모리 어드레스에 저장
		int[] instructionBit = {0,0,1,1,1,0,1,0,1,1,0,0,0,1,0,0};
		
		cpu.register.R3 = baseReg;
		cpu.register.R4 = offsetReg;
		cpu.register.R5 = data;
		
		cpu.execute(instructionBit);
		assertArrayEquals(data, cpu.memory.getMEMORY_MODEL()[1][30]);
	}
	
	@Test
	public void andTest() {
		Memory memory = new Memory();
		CPU cpu = new CPU(memory);
		
		int[] case1 = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		int[] case2 = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		int[] expectedResult = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		
//		AND R3, R1, R6 ==> 0101 011 001 000 110 => R3 = R1 && R6 
		cpu.register.R1 = case1;
		cpu.register.R6 = case2;
		
		int[] instructionBit = {0,1,0,1,0,1,1,0,0,1,0,0,0,1,1,0};
		cpu.execute(instructionBit);
		
		assertArrayEquals(expectedResult, cpu.register.R3);
	}
	
	@Test
	public void orTest() {
		Memory memory = new Memory();
		CPU cpu = new CPU(memory);
		
		int[] case1 = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		int[] case2 = {0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1};
		int[] expectedResult = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
		
//		OR R3, R1, R6 ==> 0110 011 001 000 110 => R3 = R1 || R6 
		cpu.register.R1 = case1;
		cpu.register.R6 = case2;
		
		int[] instructionBit = {0,1,1,0,0,1,1,0,0,1,0,0,0,1,1,0};
		cpu.execute(instructionBit);
		
		assertArrayEquals(expectedResult, cpu.register.R3);
	}

}

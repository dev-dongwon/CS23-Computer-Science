package step2ComputerSimulator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class SimulationTest {
	
//	0x0000 MOV R4, 0xA0 => 1011 100 01010 0000 
//	0x0002 MOV R5, 0x02 => 1011 101 00000 0010
//	0x0004 LOAD R1, R4, R5 => 0001 001 100 000 101
//	0x0006 ADD R2, R1, #4 => 1000 010 001 1 00100 
//	0x0008 SUB R3, R1, R2 => 1001 011 001 000 010
//	0x000A STORE R3, R4, #4 => 0100 011 100 1 00100

	@Test
	public void simulationTest() {
		Memory memory = new Memory();
		CPU cpu = new CPU(memory);
		
		int[] MOV1 = {1,0,1,1,1,0,0,0,1,0,1,0,0,0,0,0};
		int[] MOV2 = {1,0,1,1,1,0,1,0,0,0,0,0,0,0,1,0};
		int[] LOAD = {0,0,0,1,0,0,1,1,0,0,0,0,0,1,0,1};
		int[] ADD = {1,0,0,0,0,1,0,0,0,1,1,0,0,1,0,0};
		int[] SUB = {1,0,0,1,0,1,1,0,0,1,0,0,0,0,1,0};
		int[] STORE = {0,1,0,0,0,1,1,1,0,0,1,0,0,1,0,0};
		
		int[][] IR = {MOV1, MOV2, LOAD, ADD, SUB, STORE};
		
		// validation check
		for (int[] instruction : IR) {
			assertEquals(16, instruction.length);
		}
		
		int[] data = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		
		// memory 에 데이터 적재 (R4 + R5 address)
		int memoryAddress = 0xA0 + 0x02;
		cpu.memory.store(memoryAddress, data);

		memoryAddress = 0x10000 + memoryAddress;
		String bitAddress = Integer.toBinaryString(memoryAddress);
		
		// instruction program을 memory에 locate
		cpu.memory.locate(IR);
		
		for (int i=0; i<IR.length; i++) {
			
			// fetch 로 memory에서 instruction 가져오기
			int[] instruction = cpu.fetch();
			
			// execute로 instruction 실행
			cpu.execute(instruction);
			
			System.out.println("=============================================");
			System.out.println("instruction : " + Arrays.toString(instruction));
			System.out.println("=============================================");
			System.out.println("R1 레지스터 : " + Arrays.toString(cpu.dump()[0]));
			System.out.println("R2 레지스터 : " + Arrays.toString(cpu.dump()[1]));
			System.out.println("R3 레지스터 : " + Arrays.toString(cpu.dump()[2]));
			System.out.println("R4 레지스터 : " + Arrays.toString(cpu.dump()[3]));
			System.out.println("R5 레지스터 : " + Arrays.toString(cpu.dump()[4]));
			System.out.println("R6 레지스터 : " + Arrays.toString(cpu.dump()[5]));
			System.out.println("R7 레지스터 : " + Arrays.toString(cpu.dump()[6]));
			
			System.out.println("=============================================");
			System.out.println("memory value : " + Arrays.toString(cpu.memory.peek(bitAddress)));
			System.out.println("=============================================");
			

		}
		
		
		
		
	}

}

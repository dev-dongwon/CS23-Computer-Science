package step2ComputerSimulator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MemoryTest {

	@Test
	public void peekTest() {

		// 0(program_Text 최소경계값), 1, 65535(promgram_Text 최대경계값), 65536(program_heap 최소경계값), 131071(program_heap 최대경계값), 30000(text 영역 중위값), 90000(heap 영역 중위값)
		String[] address32Cases = {"00000000000000000000000000000000","00000000000000000000000000000001","00000000000000001111111111111111", "00000000000000010000000000000000",
									"00000000000000011111111111111111", "00000000000000000111010100110000", "00000000000000010101111110010000"};

		int[] expectedArr = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};

		for (String test32bitString : address32Cases) {
			Memory memory = new Memory();
			
			int[][][] memoryModel = memory.getMEMORY_MODEL();
			int[][] textArea = memoryModel[0];
			int[][] heapArea = memoryModel[1];

			Integer address = Integer.valueOf(test32bitString, 2);

			if (address >= 0x0000 && address <= 0xFFFF) {
						textArea[address] = expectedArr;
			} else if (address >= 0x10000 && address <= 0x1FFFF) {
						heapArea[address-0x10000] = expectedArr;
			}
			
			assertArrayEquals(expectedArr, memory.peek(test32bitString));
		}
	}
	
	@Test
	public void peekOutOfRangeExceptionTest() {
		Memory memory = new Memory();
		
		// -1, 131072 (memory 최대값 + 1), "32진수 최대값"
		String[] excpetionCases = {"-1", "00000000000000100000000000000000", "11111111111111111111111111111111"};
		
		for (String test : excpetionCases) {
			assertThrows(RuntimeException.class, () ->
			memory.peek(test),
					"메모리 범위를 초과했습니다"); 
		}
	}
	
	@Test
	public void locateTest() {
		
		int[][] testCases = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},{1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0}};
		Memory memory = new Memory();
		
		memory.locate(testCases);
		
		// locate test
		for (int i=0; i<testCases.length; i++) {
			assertArrayEquals(testCases[i], memory.getMEMORY_MODEL()[0][i]);
		}

		// locate and peek test
		for (int i=0; i<testCases.length; i++) {
			String address = Integer.toString(i);
			assertArrayEquals(testCases[i], memory.peek(address));
		}
	}
	
	@Test
	public void fetchTest() {
		Memory memory = new Memory();
		
		// 0, 1, 65535, 30000
		String[] programCountCases = {"0000000000000000", "0000000000000001", "0111111111111111", "0111010100110000"};
		int[] instruction = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		
		for (String programCount : programCountCases) {
			int integerProgramCount = Integer.valueOf(programCount, 2);
			memory.getMEMORY_MODEL()[0][integerProgramCount] = instruction;
			assertArrayEquals(instruction, memory.fetch(integerProgramCount));
			// fetch test
			assertArrayEquals(instruction, memory.peek(programCount));
		}
	}
	
	@Test
	public void loadTest() {
		Memory memory = new Memory();

		// 0, 1, 65535, 30000
		String[] addressCases = {"0000000000000000", "0000000000000001", "0111111111111111", "0111010100110000"};
		int[] memoryValue = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		
		for (String binaryAddress : addressCases) {
			int address = Integer.valueOf(binaryAddress, 2);
			memory.getMEMORY_MODEL()[1][address] = memoryValue;
			assertArrayEquals(memoryValue, memory.load(address));
		}
		
		// peek test
		for (String binaryAddress : addressCases) {
			Integer address = Integer.valueOf(binaryAddress, 2);
			address = 0x10000 + address;
			String bitAddress = Integer.toBinaryString(address);
			assertArrayEquals(memoryValue, memory.peek(bitAddress));
		}
	}
	
	@Test
	public void storeTest() {
		
		Memory memory = new Memory();
		
		// 0, 1, 65535, 30000
		String[] addressCases = {"0000000000000000", "0000000000000001", "0111111111111111", "0111010100110000"};
		int[] data = {1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0};
		
		for (String binaryAddress : addressCases) {
			int address = Integer.valueOf(binaryAddress, 2);
			memory.store(address, data);
			assertArrayEquals(data, memory.getMEMORY_MODEL()[1][address]);
		}
		
		// peek test
		for (String binaryAddress : addressCases) {
			Integer address = Integer.valueOf(binaryAddress, 2);
			address = 0x10000 + address;
			String bitAddress = Integer.toBinaryString(address);
			assertArrayEquals(data, memory.peek(bitAddress));
		}
	}
}
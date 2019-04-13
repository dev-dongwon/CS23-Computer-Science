package step2ComputerSimulator;

import java.util.Arrays;

public class Memory {
	
	// 데이터 형식은 모두 binary로 통일

	private int[] WORD = new int[16];
	private int[][] PROGRAM_TEXT = new int[(int) Math.pow(2, 16)][WORD.length];
	private int[][] PROGRAM_HEAP = new int[(int) Math.pow(2, 16)][WORD.length];
	private int[][][] MEMORY_MODEL = {this.PROGRAM_TEXT, this.PROGRAM_HEAP};
	
	public int[][][] getMEMORY_MODEL() {
		return MEMORY_MODEL;
	}

	public int[] peek(String address32) {
		
		Integer value = Integer.valueOf(address32, 2);
		
		if (value >= 0x0000 && value <= 0xFFFF) {
			return MEMORY_MODEL[0][value];
		} else if (value >= 0x10000 && value <= 0x1FFFF) {
			return MEMORY_MODEL[1][value-0x10000];
		} else {
			RuntimeException OutOfRangeException = new RuntimeException("메모리 범위를 초과했습니다");
			throw OutOfRangeException;
		}
	}
	
	public void locate(int[][] program) {
		
		int[] emptyArea = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		int programTextAreaIndex = 0;
		
		for (int i=0; i<program.length; i++) {
			if (Arrays.equals(emptyArea, MEMORY_MODEL[0][programTextAreaIndex])) {
				MEMORY_MODEL[0][programTextAreaIndex++] = program[i];
			}
		}
	}
	
	public int[] fetch(int program_count) {
		return PROGRAM_TEXT[program_count];
	}
	
	public int[] load(int address) {
		return MEMORY_MODEL[1][address];
	}
	
	public void store(int address, int[] data) {
		MEMORY_MODEL[1][address] = data;
	}
}

package step2ComputerSimulator;

import java.util.HashMap;
import java.util.Map;

public class Register {
	
	public Register() {
		this.PC = 0;
		this.R1 = new int[16];
		this.R2 = new int[16];;
		this.R3 = new int[16];;
		this.R4 = new int[16];;
		this.R5 = new int[16];;
		this.R6 = new int[16];;
		this.R7 = new int[16];;
		this.registers = new HashMap<>();
	}
	
	public int PC;
	public int[] R1;
	public int[] R2;
	public int[] R3;
	public int[] R4;
	public int[] R5;
	public int[] R6;
	public int[] R7;
	
	Map<Integer, int[]> registers;

	public Map<Integer, int[]> getRegisters() {
		registers.put(1, this.R1);
		registers.put(2, this.R2);
		registers.put(3, this.R3);
		registers.put(4, this.R4);
		registers.put(5, this.R5);
		registers.put(6, this.R6);
		registers.put(7, this.R7);
		return registers;
	}
	
}

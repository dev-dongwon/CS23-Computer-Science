package step2ComputerSimulator;

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
	}
	
	public int PC;
	public int[] R1;
	public int[] R2;
	public int[] R3;
	public int[] R4;
	public int[] R5;
	public int[] R6;
	public int[] R7;	
}

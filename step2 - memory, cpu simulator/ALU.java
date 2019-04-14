package step2ComputerSimulator;

public class ALU {
	
	public int ADD(int A, int B) {
		return A + B;
	}
	
	public int SUB(int A, int B) {
		return A - B;
	}
	
	public int[] AND(int[] A, int[] B) {

		int[] result = new int[A.length];
		
		for (int i=0; i<A.length; i++) {
			if (A[i] == 1 && B[i] == 1) {
				result[i] = 1;
			} else {
				result[i] = 0;
			}
		}

		return result;
	}

	public int[] OR(int[] A, int[] B) {
		
		int[] result = new int[A.length];
		
		for (int i=0; i<A.length; i++) {
			if (A[i] == 0 && B[i] == 0) {
				result[i] = 0;
			} else {
				result[i] = 1;
			}
		}
		
		return result;
	}
	
	

}

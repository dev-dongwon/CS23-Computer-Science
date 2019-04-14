package step2ComputerSimulator;


public class ALU {
	
	// ADD 연산
	public int ADD(int A, int B) {
		return A + B;
	}
	
	// SUB 연산
	public int SUB(int A, int B) {
		return A - B;
	}
	
	// AND 연산
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

	// OR 연산
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

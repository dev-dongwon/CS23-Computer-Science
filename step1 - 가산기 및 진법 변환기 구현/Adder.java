package step1디지털논리회로;

public class Adder {
	
	public static boolean nand(boolean paramA, boolean paramB) {
		boolean answer = !(paramA && paramB);
		return answer;
	}
	
	public static boolean nor(boolean paramA, boolean paramB) {
		boolean answer = !(paramA || paramB);
		return answer;
	}

	public static boolean sum (boolean bitA, boolean bitB) {
		return (bitA != bitB);
	}

	public static boolean carry (boolean bitA, boolean bitB) {
		return (bitA && bitB);
	}
	
    public static boolean[] halfadder (boolean bitA, boolean bitB) {
        boolean[] answer = new boolean[2];
        
        answer[0] = sum(bitA, bitB);
        answer[1] = carry(bitA, bitB);
        
        return answer;
    }
    
    public static boolean[] fulladder (boolean bitA, boolean bitB, boolean carry) {
        boolean[] answer = new boolean[2];
        
        answer[0] = halfadder(carry, sum(bitA, bitB))[0];
        answer[1] = (halfadder(carry, sum(bitA, bitB))[1] || carry(bitA, bitB));
        
        return answer;
    }
    
    public static boolean[] byteadder(boolean[] byteA, boolean[] byteB) {
        boolean[] answer = new boolean[byteA.length + 1];

        boolean carry = false;
        
        for (int i=0; i<byteA.length; i++ ) {
        	answer[i] = fulladder(byteA[i], byteB[i], carry)[0];
        	carry = fulladder(byteA[i], byteB[i], carry)[1];
        }
        
        answer[byteA.length] = fulladder(byteA[byteA.length-1], byteB[byteA.length-1], carry)[1];
        
        return answer;
    }
    
}

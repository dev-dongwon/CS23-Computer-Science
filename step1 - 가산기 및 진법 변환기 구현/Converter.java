package step1디지털논리회로;

public class Converter {
	
    public static boolean[] dec2bin(int decimal) {
    	
    	int index = 0;
    	int temp = decimal;
    	
    	if (temp == 0) {
    		index = 1;
    	}
    	
    	while (temp > 0) {
    		temp = temp / 2;
    		index++;
    	}
    	
        boolean[] answer = new boolean[index];

        int count = 0;
        
        while (decimal > 0) {
        	if (decimal % 2 == 0) {
        		answer[count++] = false;
        	} else {
        		answer[count++] = true;
        	}
        	decimal = decimal / 2;
        }
        
        return answer;
    }
    
    public static int bin2dec(boolean[] bin) {
        int answer = 0;

        for (int i=bin.length-1; i>=0; i--) {
        	if (bin[i]) {
        		answer += power(2, i);
        	}
        }
        return answer;
    }
    
    private static int power(int number, int count) {
    	if (count == 0) return 1;
    	return number * power(number, count-1);
    }

}

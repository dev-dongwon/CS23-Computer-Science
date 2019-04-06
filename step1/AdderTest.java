package step1디지털논리회로;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class AdderTest {
	
	private final static Logger LOG = Logger.getGlobal();
	
	@Test
	public void testSum() {
		
		assertEquals(false, Adder.sum(true, true));
		assertEquals(true, Adder.sum(true, false));
		assertEquals(true, Adder.sum(false, true));
		assertEquals(false, Adder.sum(false, false));
	}
	
	@Test
	public void testCarry() {
		assertEquals(true, Adder.carry(true, true));
		assertEquals(false, Adder.carry(true, false));
		assertEquals(false, Adder.carry(false, true));
		assertEquals(false, Adder.carry(false, false));
	}
	
	@Test
	public void testHalfAdder() {
		boolean[] falsetrue = {false, true};
		boolean[] truefalse = {true, false};
		boolean[] falsefalse = {false, false};
		
		assertArrayEquals(falsetrue, Adder.halfadder(true, true));
		assertArrayEquals(truefalse, Adder.halfadder(true, false));
		assertArrayEquals(truefalse, Adder.halfadder(false, true));
		assertArrayEquals(falsefalse, Adder.halfadder(false, false));
	}
	
	@Test
	public void testFullAdder() {
		
		//			A   B   C   | sum carry
		//case1		0	0	0		0	0
		//case2		0	0	1		1	0
		//case3		0	1	0		1	0
		//case4		0	1	1		0	1
		//case5		1	0	0		1	0
		//case6		1	0	1		0	1
		//case7		1	1	0		0	1
		//case8		1	1	1		1	1
				
		boolean[] case1 = {false, false, false, false, false};
		boolean[] case2 = {false, false, true, true, false};
		boolean[] case3 = {false, true, false, true, false};
		boolean[] case4 = {false, true, true, false, true};
		boolean[] case5 = {true, false, false, true, false};
		boolean[] case6 = {true, false, true, false, true};
		boolean[] case7 = {true, true, false, false, true};
		boolean[] case8 = {true, true, true, true, true};
		
		boolean[][] testCases = {case1, case2, case3, case4, case5, case6, case7, case8};
		boolean[] expectedArr = new boolean[2];
		
		for(boolean[] test : testCases) {
			expectedArr[0] = test[3];
			expectedArr[1] = test[4];
			
			LOG.info(Arrays.toString(test));
			assertArrayEquals(expectedArr, Adder.fulladder(test[0], test[1], test[2]));
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testByteAdder() {

		boolean[] byteA1 = {true, true, false, true, true, false, true, false};
    	boolean[] byteB1 = {true, false, true, true, false, false, true, true};
    	boolean[] result1 = {false, false, false, true, false, true, false, false, true};
    	boolean[][] case1 = {byteA1, byteB1, result1};
    	
    	boolean[] byteA2 = {true, true, false, false, true, false, true, false};
    	boolean[] byteB2 = {true, true, false, true, true, false, false, true};
    	boolean[] result2 = {false, true, true, true, false, true, true, true, false};
    	boolean[][] case2 = {byteA2, byteB2, result2};
    	
    	boolean[] byteA3 = {false, false, false, false, false, false, false, false};
    	boolean[] byteB3 = {false, false, false, false, false, false, false, false};
    	boolean[] result3 = {false, false, false, false, false, false, false, false, false};
    	boolean[][] case3 = {byteA3, byteB3, result3};

    	boolean[] byteA4 = {true, true, true, true, true, true, true, true};
    	boolean[] byteB4 = {true, true, true, true, true, true, true, true};
    	boolean[] result4 = {false, true, true, true, true, true, true, true, true};
    	boolean[][] case4 = {byteA4, byteB4, result4};
    	
    	
    	List<boolean[][]> testCases = new ArrayList<>();
    	
    	testCases.add(case1);
    	testCases.add(case2);
    	testCases.add(case3);
    	
    	for(boolean[][] test : testCases) {
    		assertArrayEquals(test[2], Adder.byteadder(test[0], test[1]));
    		LOG.info(Arrays.toString(test[2]));
    		LOG.info(Arrays.toString(Adder.byteadder(test[0], test[1])));
    	}
	}
	
}

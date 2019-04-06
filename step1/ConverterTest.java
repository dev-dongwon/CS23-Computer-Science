package step1디지털논리회로;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class ConverterTest {

	private final static Logger LOG = Logger.getGlobal();
	
	@Test
	public void testDec2bin() {
		boolean[] binary256 = {false, false, false, false, false, false, false, false, true};
		boolean[] binary128 = {false, false, false, false, false, false, false, true};
		boolean[] binary0 = {false};
		boolean[] binary2 = {false, true};
		boolean[] binary4 = {false, false, true};
		boolean[] binary5 = {true, false, true};
		
		assertArrayEquals(binary256, Converter.dec2bin(256));
		assertArrayEquals(binary128, Converter.dec2bin(128));
		assertArrayEquals(binary0, Converter.dec2bin(0));
		assertArrayEquals(binary2, Converter.dec2bin(2));
		assertArrayEquals(binary4, Converter.dec2bin(4));
		assertArrayEquals(binary5, Converter.dec2bin(5));
	}	
	
	@Test
	public void testBin2dec() {
		boolean[] binary256 = {false, false, false, false, false, false, false, false, true};
		boolean[] binary128 = {false, false, false, false, false, false, false, true};
		boolean[] binary0 = {false};
		boolean[] binary2 = {false, true};
		boolean[] binary4 = {false, false, true};
		boolean[] binary5 = {true, false, true};
		
		assertEquals(256, Converter.bin2dec(binary256));
		assertEquals(128, Converter.bin2dec(binary128));
		assertEquals(0, Converter.bin2dec(binary0));
		assertEquals(2, Converter.bin2dec(binary2));
		assertEquals(4, Converter.bin2dec(binary4));
		assertEquals(5, Converter.bin2dec(binary5));
		
	}
}

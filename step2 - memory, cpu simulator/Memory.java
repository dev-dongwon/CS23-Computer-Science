package step2MemoryCpuSimule;

public class Memory {

	private static boolean[] BYTES = new boolean[8];
	private static boolean[] WORD = new boolean[16];
	private static boolean[] PROGRAM_TEXT = new boolean[(int) (Math.pow(2, 16) * WORD.length)];
	private static boolean[] PROGRAM_HEAP = new boolean[(int) (Math.pow(2, 16) * WORD.length)];
	
	
	public String peek(int address) {

		if (address >= 0x0000 && address <= 0xFFFF) {
			for (int i=0; i<PROGRAM_TEXT.length; i+=WORD.length) {
				for (int j=i; j<16; j++) {
					if (j == address) {
						return Integer.toHexString(i);
					}
				}
			}
			
		} else if (address >= 0x10000 && address <= 0x1FFFF) {
			for (int i=0; i<PROGRAM_HEAP.length; i+=WORD.length) {
				for (int j=i; j<16; j++) {
					if (j == address) {
						return Integer.toHexString(i);
					}
				}
			}
			
		} else {
			Exception outOfRangeException = new Exception("out Of Range Exception");
			
			try {
				throw outOfRangeException;
			} catch (Exception e) {
				System.out.println("메모리 범위를 초과하였습니다");
				e.printStackTrace();
			}
			
			System.out.println("시스템을 종료합니다");
		}
		
		return null;
		
	}
	
	public static void main(String[] args) {
		
		Memory memory = new Memory();
		System.out.println(memory.peek(4));
	}
	
}
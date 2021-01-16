package busTestPartie1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BusTest {
	
		private long a;
		private String b;
		private Bus c = new Bus();
	
		@Test
		public void test() {
			// Variable
			a = 42;
			b = "1,x,2,x,5,11";

			// Test
			assertEquals(0, c.calc(a,b));
		}
		
		@Test
		public void testsansbus() {
			// Variable
			a = 42;
			b = "x,x,x,x,x,x";

			// Test
			assertEquals(-1, c.calc(a,b));
		}
		
		@Test
		public void testdernierbus() {
			// Variable
			a = 999;
			b = "x,x,x,x,3";

			// Test
			assertEquals(0, c.calc(a,b));
		}
		
		@Test
		public void testexemple() {
			// Variable
			a = 939;
			b = "7,13,x,x,59,x,31,19";

			// Test
			assertEquals(295, c.calc(a,b));
		}
}

package busTestPartie2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BusTest2 {
	
	private String a;				// Le contenu de la ligne d'entrée (la liste des bus)
	private Bus2 c = new Bus2();

	@Test
	public void test() {	// Test basique
		// Variable
		a = "1,x,2,x,5,11";

		// Test
		assertEquals(6, c.calc(a));
	}
	
	@Test
	public void testsansbus() {		// Test dans la condition qu'aucun bus est disponible
		// Variable
		a = "x,x,x,x,x,x";

		// Test
		assertEquals(-1, c.calc(a));
	}
	
	@Test
	public void testexemple1() {		// Test de l'exemple 1
		// Variable
		a = "17,x,13,19";

		// Test
		assertEquals(3417, c.calc(a));
	}
	
	@Test
	public void testexemple2() {		// Test de l'exemple 2
		// Variable
		a = "67,7,59,61";

		// Test
		assertEquals(754018, c.calc(a));
	}
	
	@Test
	public void testexemple3() {		// Test de l'exemple 3
		// Variable
		a = "67,x,7,59,61";

		// Test
		assertEquals(779210, c.calc(a));
	}
	
	@Test
	public void testexemple4() {		// Test de l'exemple 4
		// Variable
		a = "67,7,x,59,61";

		// Test
		assertEquals(1261476, c.calc(a));
	}
	
	@Test
	public void testexemple5() {		// Test de l'exemple 5
		// Variable
		a = "1789,37,47,1889";

		// Test
		assertEquals(1202161486, c.calc(a));
	}
}

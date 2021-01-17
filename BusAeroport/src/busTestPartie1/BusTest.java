package busTestPartie1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BusTest {
	
		private long a;					// Le contenu de la première ligne d'entrée (l'horodatage d'arrivé sur le quai)
		private String b;				// Le contenu de la deuxième ligne d'entrée (la liste des bus)
		private Bus c = new Bus();
	
		@Test
		public void test() {	//Test basique
			// Variable
			a = 42;
			b = "1,x,2,x,5,11";

			// Test
			assertEquals(0, c.calc(a,b));
		}
		
		@Test
		public void testsansbus() {		//Test dans la condition qu'aucun bus est disponible
			// Variable
			a = 42;
			b = "x,x,x,x,x,x";

			// Test
			assertEquals(-1, c.calc(a,b));
		}
		
		@Test
		public void testdernierbus() {		//Test où le seul bus se trouve à la fin de la liste
			// Variable
			a = 999;
			b = "x,x,x,x,3";

			// Test
			assertEquals(0, c.calc(a,b));
		}
		
		@Test
		public void testexemple() {			//Test de l'exemple donné au problème
			// Variable
			a = 939;
			b = "7,13,x,x,59,x,31,19";

			// Test
			assertEquals(295, c.calc(a,b));
		}
}

package busPartie2;

public class Main2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner entree =   new java.util.Scanner(System.in);				// Utilisation d'un Scanner afin de pouvoir entrée des valeurs
		
		System.out.println("A quel horodatage arriverez-vous au quai? : ");
		long timestamp = entree.nextInt();											// Le contenu de la première ligne d'entrée (l'horodatage d'arrivé sur le quai)
		
		System.out.println("Donnez le numéro de tous les bus, y compris ceux qui sont indisponibles par un 'x' : ");
		String buslist = entree.next();												// Le contenu de la deuxième ligne d'entrée (la liste des bus)
		entree.nextLine();
		
		//long result = calc(timestamp, buslist);										// Appel de la fonction calc() pour calculer le numéro de bus multiplier par le temps d'attente sur le quai
		 
		//if (result==-1) {															// S'il n'y a aucun bus, alors on dit qu'aucun bus n'arrivera sur ce quai
		//	System.out.println("Il n'y a aucun bus qui peux vous prendre...");
		//} else {																	// Sinon, on dit le résultat du numéro de bus multiplier par le temps d'attente sur le quai
		//	System.out.println("Le résultat attendu (Numéro du bus  x  Le temps d'attente sur le quai) est de " + result + " minutes.");
		//}
		entree.close();																// On ferme le Scanner
	}
}

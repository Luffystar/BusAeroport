package busPartie1;

import java.util.ArrayList;

public class Main {
	
	/*
	 * Problème de la Partie 1.
	 * 
	 * Le Problème est qu'il faut prendre un bus pour pouvoir aller à l'aéroport.
	 * Pour celà, on a un horodatage 'timestamp' qui défini la minute d'arrivée au quai de bus
	 * Chaque bus est numéroté. Les bus Hors Service sont marqués par un 'x'
	 * Chaque bus font un allez-retour en minute équivalent à leur numéro.
	 * Pour résoudre ce problème, il faut non seuleument savoir quel sera le prochain bus une fois arrivé au quai...
	 * Mais aussi savoir combien de temps il faudra attendre.
	 * Ainsi il faut multiplier le numéro bus avec le temps d'attente au quai pour avoir le résultat.
	 * 
	 * 
	 * Exemple : J'arrive au quai à 404 minutes
	 * Les bus disponibles sont : "2,x,49,x,x"
	 * 
	 * A 404 minutes, le bus numéro 2 est déjà au quai car 404 modulo 2 = 0.
	 * Etant donnée que on n'attend pas au quai on considère donc que le résultat est 0. Car 0 (temps d'attente au quai) x 2 (numéro du bus) = 0.
	 * 
	 * 
	 * Autre Exemple : J'arrive au quai à 127 minutes
	 * Les bus disponibles sont : "64,x,x,5"
	 * Essayons chaque bus disponibles : 
	 * 127 modulo 64 = 63
	 * 127 modulo 5 = 2
	 * 
	 * Pas de résultat à 0. Je vais devoir attendre 1 minute.
	 * 
	 * Donc je suis à présent à 128 minutes. Et vérifions : 
	 * 128 modulo 64 = 0
	 * 
	 * Tiens! Le bus numéro 64 vient d'arriver.
	 * Du coup, j'ai attendu 1 minute au quai pour avoir le bus numéro 64. Donc le résultat attendu est 1 x 64 = 64.
	 * 
	 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner entree =   new java.util.Scanner(System.in);				// Utilisation d'un Scanner afin de pouvoir entrée des valeurs
		
		System.out.println("A quel horodatage arriverez-vous au quai? : ");
		long timestamp = entree.nextInt();											// Le contenu de la première ligne d'entrée (l'horodatage d'arrivé sur le quai)
		
		System.out.println("Donnez le numéro de tous les bus, y compris ceux qui sont indisponibles par un 'x' : ");
		String buslist = entree.next();												// Le contenu de la deuxième ligne d'entrée (la liste des bus)
		entree.nextLine();
		
		long result = calc(timestamp, buslist);										// Appel de la fonction calc() pour calculer le numéro de bus multiplier par le temps d'attente sur le quai
		
		if (result==-1) {															// S'il n'y a aucun bus, alors on dit qu'aucun bus n'arrivera sur ce quai
			System.out.println("Il n'y a aucun bus qui peux vous prendre...");
		} else {																	// Sinon, on dit le résultat du numéro de bus multiplier par le temps d'attente sur le quai
			System.out.println("Le résultat attendu (Numéro du bus  x  Le temps d'attente sur le quai) est de " + result + " minutes.");
		}
		entree.close();																// On ferme le Scanner
	}
	
	public static long calc(long timestamp, String buslist) {		// Fonction qui retourne le résultat attendu du problème de la Partie 1
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();					// Liste contenant la liste des bus disponibles
		String s = "";												// Variable pour prendre tous les bus disponibles (éviter les ',' et 'x')
		long num;													// Variable pour pouvoir prendre le numéro du bus (pris par chaques caractères de 's')
		boolean bb = false;											// Variable booléenne pour pouvoir faire une boucle et chercher le prochain bus à tel horodatage
		long r=0;													// Variable correspondant aux minutes attendus au quai
		long idbus=0;												// Variable correspondant au numéro du prochain bus
		long max = Integer.MIN_VALUE;								// Variable correspondant aux minutes attendus au quai mais permet de savoir aussi s'il n'y a aucun bus
		
		for(int c=0; c<buslist.length(); c++) {						// Boucle pour compléter la liste des bus disponibles
			if(buslist.charAt(c)!=',') {								// Si le caractère n'est pas une virgule...
				if(buslist.charAt(c)=='x') {								// Si le caractère est un 'x'...
																				// On saute. Car le bus est indisponible
				} else {													// Sinon, le caractère est un chiffre
					s+=buslist.charAt(c);										// On prend donc ce numéro (en chaine de caractère)
				}
			} else {													// Sinon c'est une virgule
				if (s!="") {												// Si on a déjà un numéro non enregistré
					num = Integer.parseInt(s);									// Alors on transforme cette chaine de caractère en numéro
					bus.add(num);												// On l'ajoute à la liste des bus disponibles
					s="";														// Et on prépare le numéro du prochain bus
				}
			}
			if(c+1==buslist.length() && s!="") {					// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule à la fin de la liste)
				num = Integer.parseInt(s);								// On transforme cette chaine de caractère en numéro
				bus.add(num);											// Et on l'ajoute à la liste des bus disponibles
			}
		}
																	// On a à présent la liste des bus disponibles
		
		while(bb==false && bus.isEmpty()==false) {					// Boucle pour savoir quel sera le prochain bus à tel horodatage
			for(int y=0; y<bus.size(); y++) {							// Boucle pour vérifier chaques bus à tel horodatage
				if((timestamp+r)%bus.get(y)==0) {							// Condition pour savoir si le bus est à quai
					bb=true;													// Si oui, on entre dans le bus
					idbus=bus.get(y);											// On note son numéro
					max= (timestamp+r) - timestamp;								// Et on note le temps qu'on a attendu au quai
				}
			}
			if(bb==false) {												//Si aucun bus n'est arrivé...
				r++;														//On attend 1 minutes de plus...
			}
		}
		
		
		if(max == Integer.MIN_VALUE) {								//S'il n'y a aucun bus...
			return -1;													//On a rien à faire ici
		} else {													//Sinon
			return idbus*max;											//On retourne le numéro du bus multiplié par le temps d'attente au quai
		}
	}
}

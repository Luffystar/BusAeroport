package busPartie1;

import java.util.ArrayList;

public class Main {
	
	/*
	 * Probl�me de la Partie 1.
	 * 
	 * Le Probl�me est qu'il faut prendre un bus pour pouvoir aller � l'a�roport.
	 * Pour cel�, on a un horodatage 'timestamp' qui d�fini la minute d'arriv�e au quai de bus
	 * Chaque bus est num�rot�. Les bus Hors Service sont marqu�s par un 'x'
	 * Chaque bus font un allez-retour en minute �quivalent � leur num�ro.
	 * Pour r�soudre ce probl�me, il faut non seuleument savoir quel sera le prochain bus une fois arriv� au quai...
	 * Mais aussi savoir combien de temps il faudra attendre.
	 * Ainsi il faut multiplier le num�ro bus avec le temps d'attente au quai pour avoir le r�sultat.
	 * 
	 * 
	 * Exemple : J'arrive au quai � 404 minutes
	 * Les bus disponibles sont : "2,x,49,x,x"
	 * 
	 * A 404 minutes, le bus num�ro 2 est d�j� au quai car 404 modulo 2 = 0.
	 * Etant donn�e que on n'attend pas au quai on consid�re donc que le r�sultat est 0. Car 0 (temps d'attente au quai) x 2 (num�ro du bus) = 0.
	 * 
	 * 
	 * Autre Exemple : J'arrive au quai � 127 minutes
	 * Les bus disponibles sont : "64,x,x,5"
	 * Essayons chaque bus disponibles : 
	 * 127 modulo 64 = 63
	 * 127 modulo 5 = 2
	 * 
	 * Pas de r�sultat � 0. Je vais devoir attendre 1 minute.
	 * 
	 * Donc je suis � pr�sent � 128 minutes. Et v�rifions : 
	 * 128 modulo 64 = 0
	 * 
	 * Tiens! Le bus num�ro 64 vient d'arriver.
	 * Du coup, j'ai attendu 1 minute au quai pour avoir le bus num�ro 64. Donc le r�sultat attendu est 1 x 64 = 64.
	 * 
	 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner entree =   new java.util.Scanner(System.in);				// Utilisation d'un Scanner afin de pouvoir entr�e des valeurs
		
		System.out.println("A quel horodatage arriverez-vous au quai? : ");
		long timestamp = entree.nextInt();											// Le contenu de la premi�re ligne d'entr�e (l'horodatage d'arriv� sur le quai)
		
		System.out.println("Donnez le num�ro de tous les bus, y compris ceux qui sont indisponibles par un 'x' : ");
		String buslist = entree.next();												// Le contenu de la deuxi�me ligne d'entr�e (la liste des bus)
		entree.nextLine();
		
		long result = calc(timestamp, buslist);										// Appel de la fonction calc() pour calculer le num�ro de bus multiplier par le temps d'attente sur le quai
		
		if (result==-1) {															// S'il n'y a aucun bus, alors on dit qu'aucun bus n'arrivera sur ce quai
			System.out.println("Il n'y a aucun bus qui peux vous prendre...");
		} else {																	// Sinon, on dit le r�sultat du num�ro de bus multiplier par le temps d'attente sur le quai
			System.out.println("Le r�sultat attendu (Num�ro du bus  x  Le temps d'attente sur le quai) est de " + result + " minutes.");
		}
		entree.close();																// On ferme le Scanner
	}
	
	public static long calc(long timestamp, String buslist) {		// Fonction qui retourne le r�sultat attendu du probl�me de la Partie 1
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();					// Liste contenant la liste des bus disponibles
		String s = "";												// Variable pour prendre tous les bus disponibles (�viter les ',' et 'x')
		long num;													// Variable pour pouvoir prendre le num�ro du bus (pris par chaques caract�res de 's')
		boolean bb = false;											// Variable bool�enne pour pouvoir faire une boucle et chercher le prochain bus � tel horodatage
		long r=0;													// Variable correspondant aux minutes attendus au quai
		long idbus=0;												// Variable correspondant au num�ro du prochain bus
		long max = Integer.MIN_VALUE;								// Variable correspondant aux minutes attendus au quai mais permet de savoir aussi s'il n'y a aucun bus
		
		for(int c=0; c<buslist.length(); c++) {						// Boucle pour compl�ter la liste des bus disponibles
			if(buslist.charAt(c)!=',') {								// Si le caract�re n'est pas une virgule...
				if(buslist.charAt(c)=='x') {								// Si le caract�re est un 'x'...
																				// On saute. Car le bus est indisponible
				} else {													// Sinon, le caract�re est un chiffre
					s+=buslist.charAt(c);										// On prend donc ce num�ro (en chaine de caract�re)
				}
			} else {													// Sinon c'est une virgule
				if (s!="") {												// Si on a d�j� un num�ro non enregistr�
					num = Integer.parseInt(s);									// Alors on transforme cette chaine de caract�re en num�ro
					bus.add(num);												// On l'ajoute � la liste des bus disponibles
					s="";														// Et on pr�pare le num�ro du prochain bus
				}
			}
			if(c+1==buslist.length() && s!="") {					// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule � la fin de la liste)
				num = Integer.parseInt(s);								// On transforme cette chaine de caract�re en num�ro
				bus.add(num);											// Et on l'ajoute � la liste des bus disponibles
			}
		}
																	// On a � pr�sent la liste des bus disponibles
		
		while(bb==false && bus.isEmpty()==false) {					// Boucle pour savoir quel sera le prochain bus � tel horodatage
			for(int y=0; y<bus.size(); y++) {							// Boucle pour v�rifier chaques bus � tel horodatage
				if((timestamp+r)%bus.get(y)==0) {							// Condition pour savoir si le bus est � quai
					bb=true;													// Si oui, on entre dans le bus
					idbus=bus.get(y);											// On note son num�ro
					max= (timestamp+r) - timestamp;								// Et on note le temps qu'on a attendu au quai
				}
			}
			if(bb==false) {												//Si aucun bus n'est arriv�...
				r++;														//On attend 1 minutes de plus...
			}
		}
		
		
		if(max == Integer.MIN_VALUE) {								//S'il n'y a aucun bus...
			return -1;													//On a rien � faire ici
		} else {													//Sinon
			return idbus*max;											//On retourne le num�ro du bus multipli� par le temps d'attente au quai
		}
	}
}

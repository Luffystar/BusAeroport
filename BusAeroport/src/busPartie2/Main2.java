package busPartie2;

import java.util.ArrayList;

public class Main2 {
	
	/*
	 * Probl�me de la Partie 2.
	 * 
	 * Le Probl�me est qu'il faut savoir � partir de quand chaque bus de la liste se suivra minutes apr�s minutes jusqu'� ce que tous les bus ont pris le quai, qu'ils soient disponibles ou indisponibles
	 * Pour cel�, on a un la liste des bus qui arrive sur le quai
	 * Chaque bus est num�rot�. Les bus Hors Service sont marqu�s par un 'x', on consid�rera qu'ils arriveront 1 minute apr�s le bus pr�c�dent.
	 * Chaque bus font un allez-retour en minute �quivalent � leur num�ro.
	 * Pour r�soudre ce probl�me, il faut avoir le temps exact ou chaque bus se suivront minutes apr�s minutes
	 * Mais aussi la suite de bus doit se faire de la m�me fa�on que l'emplacement des bus dans la liste.
	 * 
	 * 
	 * Exemple : Les bus disponibles sont : "2,x,5"
	 * 
	 * A 2 minutes, le bus num�ro 2 est au quai. Car 2 modulo 2 = 0
	 * Normalement, A 3 minutes, le bus 'x' doit arriver. Et on le consid�re comme arriv� m�me s'il est indisponible
	 * A 4 minutes, le bus num�ro 5 n'est pas arriv�. Car 4 modulo 5 = 4
	 * Donc, il n'y a pas de suite � l'horodatage de 2 minutes
	 * 
	 * Du coup, on recommence en ajoutant le num�ro du premier bus � l'horodatage : (2 + 2 = 4)
	 * 
	 * A 4 minutes, le bus num�ro 2 est au quai.
	 * ...
	 * ...etc... (4 + 2 = 6)
	 * ...
	 * (6 + 2 = 8)
	 * 
	 * A 8 minutes, le bus num�ro 2 est au quai. Car 8 modulo 2 = 0.
	 * Normalement, A 9 minutes, le bus 'x' doit arriver. Et on le consid�re comme arriv� m�me s'il est indisponible
	 * A 10 minutes, le bus num�ro 5 est au quai. Car 10 modulo 5 = 0.
	 * 
	 * On � v�rifi� tous les bus de la liste et il y a une suite.
	 * Donc, le d�but de la suite commence � l'horodatage de 8 minutes.
	 * 
	 * 
	 * */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner entree =   new java.util.Scanner(System.in);				// Utilisation d'un Scanner afin de pouvoir entr�e des valeurs
		
		System.out.println("Donnez le num�ro de tous les bus, y compris ceux qui sont indisponibles par un 'x' : ");
		String buslist = entree.next();												// Le contenu de la ligne d'entr�e (la liste des bus)
		entree.nextLine();
		
		long result = calc(buslist);												// Appel de la fonction calc() pour calculer et voir � quel horodatage il y aura une suite
		 
		if (result==-1) {															// S'il n'y a aucun bus, alors on dit qu'aucun bus n'arrivera sur ce quai
			System.out.println("Il n'y a aucun bus qui peux vous prendre...");
		} else {																	// Sinon, on dit le r�sultat en minutes � partir duquel chaque bus de la liste se suivent
			System.out.println("Le r�sultat attendu pour avoir une suite de bus est � " + result + " minutes.");
		}
		entree.close();																// On ferme le Scanner
	}

	private static long calc(String buslist) {
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();					// Liste contenant la liste des bus disponibles
		String s = "";												// Variable pour prendre tous les bus disponibles (�viter les ',' et 'x')
		long num;													// Variable pour pouvoir prendre le num�ro du bus (pris par chaques caract�res de 's')
		boolean bb = false;											// Variable bool�enne pour pouvoir faire une boucle et chercher la suite de bus
		long max = Integer.MIN_VALUE;								// Variable correspondant aux minutes attendus au quai mais permet de savoir aussi s'il n'y a aucun bus
		long timestamp;												// Variable qu'on doit calculer. Elle prendra le premier num�ro de bus de la liste et se cumulera jusqu'� trouver une suite 
		
		for(int c=0; c<buslist.length(); c++) {						// Boucle pour compl�ter la liste des bus
			if(buslist.charAt(c)!=',') {								// Si le caract�re n'est pas une virgule...
				if(buslist.charAt(c)=='x') {								// Si le caract�re est un 'x'...
					bus.add((long) -1);											// On ajoute le bus indisponible afin de continuer la suite
				} else {													// Sinon, le caract�re est un chiffre
					s+=buslist.charAt(c);										// On prend donc ce num�ro (en chaine de caract�re)
				}
			} else {													// Sinon c'est une virgule
				if (s!="") {												// Si on a d�j� un num�ro non enregistr�
					num = Integer.parseInt(s);									// Alors on transforme cette chaine de caract�re en num�ro
					bus.add(num);												// On l'ajoute � la liste des bus
					s="";														// Et on pr�pare le num�ro du prochain bus
				}
			}
			if(c+1==buslist.length() && s!="") {					// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule � la fin de la liste)
				num = Integer.parseInt(s);								// On transforme cette chaine de caract�re en num�ro
				bus.add(num);											// Et on l'ajoute � la liste des bus
			}
		}
																	// On a � pr�sent la liste des bus
		timestamp=bus.get(0);										// On prend le premier num�ro de bus car on sait qu'il arrivera � la minute descrivant son num�ro
		
		while(bb==false && bus.isEmpty()==false) {					// Boucle pour savoir quand se d�roulera la suite � tel horodatage
			long y=0;													// Variable pour savoir s'il y a une suite ou non � tel horodatage.
			for(int x=0; x<bus.size(); x++) {							// Boucle pour v�rifier chaques bus � tel horodatage
				if(bus.get(x)==-1) {										// Condition pour savoir si le bus est indisponible
					y++;														// Si oui, on attend une minutes avant le bus suivant
				} else {														// Sinon,
					if((timestamp+y)%bus.get(x)==0) {								// Condition pour savoir si le bus est � quai � tel horodatage 'timestamp' + les minutes attendus 'y'
						y++;															// S'il est pr�sent, il y a une possible suite. On attend de voir si � la prochaine minute, le bus suivant arrive
					} else {														// Sinon.
						y--;															// Il n'y a pas de suite de bus � tel horodatage 'timestamp' + les minutes attendus 'y'
					}
				}
			}
			if(y==bus.size()) {											// Condition pour savoir s'il y a une suite parfaite
				bb=true;													// Si oui, on change 'bb' afin de sortir de la boucle
				max=timestamp;												// Et on prend le d�but de l'horodatage auquel la suite parfaite commence
			} else {													// Sinon
				timestamp+=bus.get(0);										// On attend X minutes (X est �gal au premier num�ro de bus de la liste). Et on voit apr�s si les bus se suivront
			}
		}
		
		
		if(max == Integer.MIN_VALUE) {								//S'il n'y a aucun bus...
			return -1;													//On a rien � faire ici
		} else {													//Sinon
			return max;													//On retourne l'horodatage de d�part avant la suite de bus
		}
	}
}

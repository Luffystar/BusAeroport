package busTestPartie2;

import java.util.ArrayList;

public class Bus2 {

	public long calc(String a) {
		ArrayList<Long> bus = new ArrayList<>();					// Liste contenant la liste des bus disponibles
		String s = "";												// Variable pour prendre tous les bus disponibles (�viter les ',' et 'x')
		long num;													// Variable pour pouvoir prendre le num�ro du bus (pris par chaques caract�res de 's')
		boolean bb = false;											// Variable bool�enne pour pouvoir faire une boucle et chercher le prochain bus � tel horodatage
		long max = Integer.MIN_VALUE;								// Variable correspondant au minutes attendu au quai mais permet de savoir aussi s'il n'y a aucun bus
		long timestamp;												// Variable qu'on doit calculer. Elle prendra le premier num�ro de bus de la liste et se cumulera jusqu'� trouver une suite 
		
		for(int c=0; c<a.length(); c++) {						// Boucle pour compl�ter la liste des bus
			if(a.charAt(c)!=',') {								// Si le caract�re n'est pas une virgule...
				if(a.charAt(c)=='x') {								// Si le caract�re est un 'x'...
					bus.add((long) -1);											// On ajoute le bus indisponible afin de continuer la suite
				} else {													// Sinon, le caract�re est un chiffre
					s+=a.charAt(c);										// On prend donc ce num�ro (en chaine de caract�re)
				}
			} else {													// Sinon c'est une virgule
				if (s!="") {												// Si on a d�j� un num�ro non enregistr�
					num = Integer.parseInt(s);									// Alors on transforme cette chaine de caract�re en num�ro
					bus.add(num);												// On l'ajoute � la liste des bus
					s="";														// Et on pr�pare le num�ro du prochain bus
				}
			}
			if(c+1==a.length() && s!="") {					// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule � la fin de la liste)
				num = Integer.parseInt(s);								// On transforme cette chaine de caract�re en num�ro
				bus.add(num);											// Et on l'ajoute � la liste des bus
			}
		}
																	// On a � pr�sent la liste des bus disponibles
		timestamp=bus.get(0);										// On prend le premier num�ro de bus car on sait qu'il arrivera � la minute descrivant son num�ro
		
		while(bb==false && bus.isEmpty()==false) {					// Boucle pour savoir quel sera le prochain bus � tel horodatage
			long y=0;													// Variable pour savoir s'il y a une suite ou non.
			for(int x=0; x<bus.size(); x++) {							// Boucle pour v�rifier chaques bus � tel horodatage
				if(bus.get(x)==-1) {										// Condition pour savoir si le bus est indisponible
					y++;														// Si oui, on attend une minutes avant le bus suivant
				} else {														// Sinon,
					if((timestamp+y)%bus.get(x)==0) {								// Condition pour savoir si le bus est � quai � tel horodatage 'timestamp'
						y++;															// S'il est pr�sent, il y a une possible suite. On attend de voir si � la prochaine minute, le bus suivant arrive
					} else {														// Sinon.
						y--;															// Il n'y a pas de suite de bus � tel horodatage 'timestamp'
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
			return max;											//On retourne l'horodatage de d�part avant la suite de bus
		}
	}

}

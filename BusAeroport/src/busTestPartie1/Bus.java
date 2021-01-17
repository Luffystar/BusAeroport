package busTestPartie1;

import java.util.ArrayList;

public class Bus {

	public long calc(long a, String b) {		// Fonction qui retourne le r�sultat attendu du probl�me de la Partie 1
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();		// Liste contenant la liste des bus disponibles
		String s = "";									// Variable pour prendre tous les bus disponibles (�viter les ',' et 'x')
		long num;										// Variable pour pouvoir prendre le num�ro du bus (pris par chaques caract�res de 's')
		boolean bb = false;								// Variable bool�enne pour pouvoir faire une boucle et chercher le prochain bus � tel horodatage
		long r=0;										// Variable correspondant au minutes attendu au quai
		long idbus=0;									// Variable correspondant au num�ro du prochain bus
		long max = Integer.MIN_VALUE;					// Variable correspondant au minutes attendu au quai mais permet de savoir aussi s'il n'y a aucun bus
		
		for(int c=0; c<b.length(); c++) {				// Boucle pour compl�ter la liste des bus disponibles
			if(b.charAt(c)!=',') {							// Si le caract�re n'est pas une virgule...
				if(b.charAt(c)=='x') {							// Si le caract�re est un 'x'...
																	// On saute. Car le bus est indisponible
				} else {										// Sinon, le caract�re est un chiffre
					s+=b.charAt(c);									// On prend donc ce num�ro (en chaine de caract�re)
				}
			} else {										// Sinon c'est une virgule
				if (s!="") {									// Si on a d�j� un num�ro non enregistr�
					num = Integer.parseInt(s);						// Alors on transforme cette chaine de caract�re en num�ro
					bus.add(num);									// On l'ajoute � la liste des bus disponibles
					s="";											// Et on pr�pare le num�ro du prochain bus
				}
			}
			if(c+1==b.length() && s!="") {					// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule � la fin de la liste)
				num = Integer.parseInt(s);						// On transforme cette chaine de caract�re en num�ro
				bus.add(num);									// Et on l'ajoute � la liste des bus disponibles
			}
		}
														// On a � pr�sent la liste des bus disponibles
		
		while(bb==false && bus.isEmpty()==false) {		// Boucle pour savoir quel sera le prochain bus � tel horodatage
			for(int y=0; y<bus.size(); y++) {				// Boucle pour v�rifier chaques bus � tel horodatage
				if((a+r)%bus.get(y)==0) {						// Condition pour savoir si le bus est � quai
					bb=true;										// Si oui, on entre dans le bus
					idbus=bus.get(y);								// On note son num�ro
					max= (a+r) - a;									// Et on note le temps qu'on a attendu au quai
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

package busTestPartie2;

import java.util.ArrayList;

public class Bus2 {

	public long calc(String a) {
		ArrayList<Long> bus = new ArrayList<>();					// Liste contenant la liste des bus disponibles
		String s = "";												// Variable pour prendre tous les bus disponibles (éviter les ',' et 'x')
		long num;													// Variable pour pouvoir prendre le numéro du bus (pris par chaques caractères de 's')
		boolean bb = false;											// Variable booléenne pour pouvoir faire une boucle et chercher le prochain bus à tel horodatage
		long max = Integer.MIN_VALUE;								// Variable correspondant au minutes attendu au quai mais permet de savoir aussi s'il n'y a aucun bus
		long timestamp;												// Variable qu'on doit calculer. Elle prendra le premier numéro de bus de la liste et se cumulera jusqu'à trouver une suite 
		
		for(int c=0; c<a.length(); c++) {						// Boucle pour compléter la liste des bus
			if(a.charAt(c)!=',') {								// Si le caractère n'est pas une virgule...
				if(a.charAt(c)=='x') {								// Si le caractère est un 'x'...
					bus.add((long) -1);											// On ajoute le bus indisponible afin de continuer la suite
				} else {													// Sinon, le caractère est un chiffre
					s+=a.charAt(c);										// On prend donc ce numéro (en chaine de caractère)
				}
			} else {													// Sinon c'est une virgule
				if (s!="") {												// Si on a déjà un numéro non enregistré
					num = Integer.parseInt(s);									// Alors on transforme cette chaine de caractère en numéro
					bus.add(num);												// On l'ajoute à la liste des bus
					s="";														// Et on prépare le numéro du prochain bus
				}
			}
			if(c+1==a.length() && s!="") {					// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule à la fin de la liste)
				num = Integer.parseInt(s);								// On transforme cette chaine de caractère en numéro
				bus.add(num);											// Et on l'ajoute à la liste des bus
			}
		}
																	// On a à présent la liste des bus disponibles
		timestamp=bus.get(0);										// On prend le premier numéro de bus car on sait qu'il arrivera à la minute descrivant son numéro
		
		while(bb==false && bus.isEmpty()==false) {					// Boucle pour savoir quel sera le prochain bus à tel horodatage
			long y=0;													// Variable pour savoir s'il y a une suite ou non.
			for(int x=0; x<bus.size(); x++) {							// Boucle pour vérifier chaques bus à tel horodatage
				if(bus.get(x)==-1) {										// Condition pour savoir si le bus est indisponible
					y++;														// Si oui, on attend une minutes avant le bus suivant
				} else {														// Sinon,
					if((timestamp+y)%bus.get(x)==0) {								// Condition pour savoir si le bus est à quai à tel horodatage 'timestamp'
						y++;															// S'il est présent, il y a une possible suite. On attend de voir si à la prochaine minute, le bus suivant arrive
					} else {														// Sinon.
						y--;															// Il n'y a pas de suite de bus à tel horodatage 'timestamp'
					}
				}
			}
			if(y==bus.size()) {											// Condition pour savoir s'il y a une suite parfaite
				bb=true;													// Si oui, on change 'bb' afin de sortir de la boucle
				max=timestamp;												// Et on prend le début de l'horodatage auquel la suite parfaite commence
			} else {													// Sinon
				timestamp+=bus.get(0);										// On attend X minutes (X est égal au premier numéro de bus de la liste). Et on voit après si les bus se suivront
			}
		}
		
		
		if(max == Integer.MIN_VALUE) {								//S'il n'y a aucun bus...
			return -1;													//On a rien à faire ici
		} else {													//Sinon
			return max;											//On retourne l'horodatage de départ avant la suite de bus
		}
	}

}

package busTestPartie1;

import java.util.ArrayList;

public class Bus {

	public long calc(long a, String b) {		// Fonction qui retourne le résultat attendu du problème de la Partie 1
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();	// Liste contenant la liste des bus disponibles
		String s = "";					// Variable pour prendre tous les bus disponibles (éviter les ',' et 'x')
		long num;					// Variable pour pouvoir prendre le numéro du bus (pris par chaques caractères de 's')
		boolean bb = false;				// Variable booléenne pour pouvoir faire une boucle et chercher le prochain bus à tel horodatage
		long r=0;					// Variable correspondant au minutes attendu au quai
		long idbus=0;					// Variable correspondant au numéro du prochain bus
		long max = Integer.MIN_VALUE;			// Variable correspondant au minutes attendu au quai mais permet de savoir aussi s'il n'y a aucun bus
		
		for(int c=0; c<b.length(); c++) {		// Boucle pour compléter la liste des bus disponibles
			if(b.charAt(c)!=',') {				// Si le caractère n'est pas une virgule...
				if(b.charAt(c)=='x') {				// Si le caractère est un 'x'...
											// On saute. Car le bus est indisponible
				} else {					// Sinon, le caractère est un chiffre
					s+=b.charAt(c);					// On prend donc ce numéro (en chaine de caractère)
				}
			} else {					// Sinon c'est une virgule
				if (s!="") {					// Si on a déjà un numéro non enregistré
					num = Integer.parseInt(s);			// Alors on transforme cette chaine de caractère en numéro
					bus.add(num);					// On l'ajoute à la liste des bus disponibles
					s="";						// Et on prépare le numéro du prochain bus
				}
			}
			if(c+1==b.length() && s!="") {			// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule à la fin de la liste)
				num = Integer.parseInt(s);			// On transforme cette chaine de caractère en numéro
				bus.add(num);					// Et on l'ajoute à la liste des bus disponibles
			}
		}
								// On a à présent la liste des bus disponibles
		
		while(bb==false && bus.isEmpty()==false) {	// Boucle pour savoir quel sera le prochain bus à tel horodatage
			for(int y=0; y<bus.size(); y++) {		// Boucle pour vérifier chaques bus à tel horodatage
				if((a+r)%bus.get(y)==0) {			// Condition pour savoir si le bus est à quai
					bb=true;					// Si oui, on entre dans le bus
					idbus=bus.get(y);				// On note son numéro
					max= (a+r) - a;					// Et on note le temps qu'on a attendu au quai
				}
			}
			if(bb==false) {					//Si aucun bus n'est arrivé...
				r++;						//On attend 1 minutes de plus...
			}
		}
		
		
		if(max == Integer.MIN_VALUE) {			//S'il n'y a aucun bus...
			return -1;					//On a rien à faire ici
		} else {					//Sinon
			return idbus*max;				//On retourne le numéro du bus multiplié par le temps d'attente au quai
		}
	}
}

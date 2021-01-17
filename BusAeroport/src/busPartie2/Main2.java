package busPartie2;

import java.util.ArrayList;

public class Main2 {
	
	/*
	 * Problème de la Partie 2.
	 * 
	 * Le Problème est qu'il faut savoir à partir de quand chaque bus de la liste se suivra minutes après minutes jusqu'à ce que tous les bus ont pris le quai, qu'ils soient disponibles ou indisponibles
	 * Pour celà, on a un la liste des bus qui arrive sur le quai
	 * Chaque bus est numéroté. Les bus Hors Service sont marqués par un 'x', on considérera qu'ils arriveront 1 minute après le bus précédent.
	 * Chaque bus font un allez-retour en minute équivalent à leur numéro.
	 * Pour résoudre ce problème, il faut avoir le temps exact ou chaque bus se suivront minutes après minutes
	 * Mais aussi la suite de bus doit se faire de la même façon que l'emplacement des bus dans la liste.
	 * 
	 * 
	 * Exemple : Les bus disponibles sont : "2,x,5"
	 * 
	 * A 2 minutes, le bus numéro 2 est au quai. Car 2 modulo 2 = 0
	 * Normalement, A 3 minutes, le bus 'x' doit arriver. Et on le considère comme arrivé même s'il est indisponible
	 * A 4 minutes, le bus numéro 5 n'est pas arrivé. Car 4 modulo 5 = 4
	 * Donc, il n'y a pas de suite à l'horodatage de 2 minutes
	 * 
	 * Du coup, on recommence en ajoutant le numéro du premier bus à l'horodatage : (2 + 2 = 4)
	 * 
	 * A 4 minutes, le bus numéro 2 est au quai.
	 * ...
	 * ...etc... (4 + 2 = 6)
	 * ...
	 * (6 + 2 = 8)
	 * 
	 * A 8 minutes, le bus numéro 2 est au quai. Car 8 modulo 2 = 0.
	 * Normalement, A 9 minutes, le bus 'x' doit arriver. Et on le considère comme arrivé même s'il est indisponible
	 * A 10 minutes, le bus numéro 5 est au quai. Car 10 modulo 5 = 0.
	 * 
	 * On à vérifié tous les bus de la liste et il y a une suite.
	 * Donc, le début de la suite commence à l'horodatage de 8 minutes.
	 * 
	 * 
	 * */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner entree =   new java.util.Scanner(System.in);		// Utilisation d'un Scanner afin de pouvoir entrée des valeurs
		
		System.out.println("Donnez le numéro de tous les bus, y compris ceux qui sont indisponibles par un 'x' : ");
		String buslist = entree.next();						// Le contenu de la ligne d'entrée (la liste des bus)
		entree.nextLine();
		
		long result = calc(buslist);						// Appel de la fonction calc() pour calculer et voir à quel horodatage il y aura une suite
		 
		if (result==-1) {							// S'il n'y a aucun bus, alors on dit qu'aucun bus n'arrivera sur ce quai
			System.out.println("Il n'y a aucun bus qui peux vous prendre...");
		} else {								// Sinon, on dit le résultat en minutes à partir duquel chaque bus de la liste se suivent
			System.out.println("Le résultat attendu pour avoir une suite de bus est à " + result + " minutes.");
		}
		entree.close();								// On ferme le Scanner
	}

	private static long calc(String buslist) {
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();				// Liste contenant la liste des bus disponibles
		String s = "";								// Variable pour prendre tous les bus disponibles (éviter les ',' et 'x')
		long num;								// Variable pour pouvoir prendre le numéro du bus (pris par chaques caractères de 's')
		boolean bb = false;							// Variable booléenne pour pouvoir faire une boucle et chercher la suite de bus
		long max = Integer.MIN_VALUE;						// Variable correspondant aux minutes attendus au quai mais permet de savoir aussi s'il n'y a aucun bus
		long timestamp;								// Variable qu'on doit calculer. Elle prendra le premier numéro de bus de la liste et se cumulera jusqu'à trouver une suite 
		
		for(int c=0; c<buslist.length(); c++) {					// Boucle pour compléter la liste des bus
			if(buslist.charAt(c)!=',') {						// Si le caractère n'est pas une virgule...
				if(buslist.charAt(c)=='x') {						// Si le caractère est un 'x'...
					bus.add((long) -1);							// On ajoute le bus indisponible afin de continuer la suite
				} else {								// Sinon, le caractère est un chiffre
					s+=buslist.charAt(c);							// On prend donc ce numéro (en chaine de caractère)
				}
			} else {								// Sinon c'est une virgule
				if (s!="") {								// Si on a déjà un numéro non enregistré
					num = Integer.parseInt(s);						// Alors on transforme cette chaine de caractère en numéro
					bus.add(num);								// On l'ajoute à la liste des bus
					s="";									// Et on prépare le numéro du prochain bus
				}
			}
			if(c+1==buslist.length() && s!="") {					// Condition pour prendre le dernier bus de la liste (puisqu'il n'y a pas de virgule à la fin de la liste)
				num = Integer.parseInt(s);						// On transforme cette chaine de caractère en numéro
				bus.add(num);								// Et on l'ajoute à la liste des bus
			}
		}
											// On a à présent la liste des bus
		timestamp=bus.get(0);							// On prend le premier numéro de bus car on sait qu'il arrivera à la minute descrivant son numéro
		
		while(bb==false && bus.isEmpty()==false) {				// Boucle pour savoir quand se déroulera la suite à tel horodatage
			long y=0;								// Variable pour savoir s'il y a une suite ou non à tel horodatage.
			for(int x=0; x<bus.size(); x++) {					// Boucle pour vérifier chaques bus à tel horodatage
				if(bus.get(x)==-1) {							// Condition pour savoir si le bus est indisponible
					y++;									// Si oui, on attend une minutes avant le bus suivant
				} else {								// Sinon,
					if((timestamp+y)%bus.get(x)==0) {					// Condition pour savoir si le bus est à quai à tel horodatage 'timestamp' + les minutes attendus 'y'
						y++;									// S'il est présent, il y a une possible suite. On attend de voir si à la prochaine minute, le bus suivant arrive
					} else {								// Sinon.
						y--;									// Il n'y a pas de suite de bus à tel horodatage 'timestamp' + les minutes attendus 'y'
					}
				}
			}
			if(y==bus.size()) {							// Condition pour savoir s'il y a une suite parfaite
				bb=true;								// Si oui, on change 'bb' afin de sortir de la boucle
				max=timestamp;								// Et on prend le début de l'horodatage auquel la suite parfaite commence
			} else {								// Sinon
				timestamp+=bus.get(0);							// On attend X minutes (X est égal au premier numéro de bus de la liste). Et on voit après si les bus se suivront
			}
		}
		
		
		if(max == Integer.MIN_VALUE) {						//S'il n'y a aucun bus...
			return -1;								//On a rien à faire ici
		} else {								//Sinon
			return max;								//On retourne l'horodatage de départ avant la suite de bus
		}
	}
}

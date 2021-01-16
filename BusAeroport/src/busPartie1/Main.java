package busPartie1;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Scanner entree =   new java.util.Scanner(System.in);
		
		System.out.println("A quel horodatage arriverez-vous au quai? : ");
		long timestamp = entree.nextInt();
		System.out.println("Donnez le numéro de tous les bus, y compris ceux qui sont indisponibles par un 'x' : ");
		String buslist = entree.next();
		entree.nextLine();
		long result = calc(timestamp, buslist);
		if (result==-1) {
			System.out.println("Il n'y a aucun bus qui peux vous prendre...");
		} else {
			System.out.println("Le résultat attendu (Numéro du bus  x  Le temps d'attente sur le quai) est de " + result + " minutes.");
		}
		entree.close();
	}
	
	public static long calc(long timestamp, String buslist) {
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();
		String s = "";
		long num;
		boolean bb = false;
		long r=0;
		long idbus=0;
		long max = Integer.MIN_VALUE;
		
		for(int c=0; c<buslist.length(); c++) {
			if(buslist.charAt(c)!=',') {
				if(buslist.charAt(c)=='x') {
					
				} else {
					s+=buslist.charAt(c);
				}
			} else {
				if (s!="") {
					num = Integer.parseInt(s);
					bus.add(num);
					s="";
				}
			}
			if(c+1==buslist.length() && s!="") {
				num = Integer.parseInt(s);
				bus.add(num);
			}
		}
		
		
		while(bb==false && bus.isEmpty()==false) {
			for(int y=0; y<bus.size(); y++) {
				if((timestamp+r)%bus.get(y)==0) {
					bb=true;
					idbus=bus.get(y);
					max= (timestamp+r) - timestamp;
				}
			}
			r++;
		}
		
		
		if(max == Integer.MIN_VALUE) {
			return -1;
		} else {
			return idbus*max;
		}
	}

}

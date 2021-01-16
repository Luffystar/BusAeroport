package busTestPartie1;

import java.util.ArrayList;

public class Bus {

	public long calc(long a, String b) {
		// TODO Auto-generated method stub
		ArrayList<Long> bus = new ArrayList<>();
		String s = "";
		long num;
		boolean bb = false;
		long r=0;
		long idbus=0;
		long max = Integer.MIN_VALUE;
		
		for(int c=0; c<b.length(); c++) {
			if(b.charAt(c)!=',') {
				if(b.charAt(c)=='x') {
					
				} else {
					s+=b.charAt(c);
				}
			} else {
				if (s!="") {
					num = Integer.parseInt(s);
					bus.add(num);
					s="";
				}
			}
			if(c+1==b.length() && s!="") {
				num = Integer.parseInt(s);
				bus.add(num);
			}
		}
		
		
		while(bb==false && bus.isEmpty()==false) {
			for(int y=0; y<bus.size(); y++) {
				if((a+r)%bus.get(y)==0) {
					bb=true;
					idbus=bus.get(y);
					max= (a+r) - a;
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

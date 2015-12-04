package examples;

import java.util.Arrays;

public class BoyerMoore {

	char t [];
	char p [];
	int [] loctab = new int[256];
	int n;
	int m;
	int cnt;
	public BoyerMoore(String ts, String ps){
		t = ts.toCharArray();
		p = ps.toCharArray();
		n=t.length;
		m=p.length;
		Arrays.fill(loctab,-1);
		for (int i=0;i<m;i++){
			loctab[p[i]]=i; // the last occurence of character p[i] is now i
		}			
	}

	public int find(){
		cnt=0;
		int i= m-1;
		int j= m-1;
		do{
			cnt++;
			if (t[i]==p[j]){
				if(j==0) return i;
				i--;
				j--;
			}
			else {
				i=i+m -Math.min(j,1+loctab[t[i]]);
				j=m-1;						
			}
		} while (i<n);
		return -1;
	}
	
	public static void main(String[] args) {
		
		BoyerMoore bm = new BoyerMoore("algorithm and data structures","tures");
		System.out.println(bm.find());
		System.out.println("Vergleiche: "+bm.cnt);
	}
	
}

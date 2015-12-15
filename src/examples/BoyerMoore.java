package examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

	public BoyerMoore(File file, String ps) throws IOException{
		setText(file);
		p = ps.toCharArray();
		n=t.length;
		m=p.length;
		Arrays.fill(loctab,-1);
		for (int i=0;i<m;i++){
			loctab[p[i]]=i; // the last occurence of character p[i] is now i
		}			
	}

	
	public int findBF(){
		cnt=0;
		int i=0;
		char first = p[0];
		int maxI = n-m;
		while (i<maxI){
			while (i<maxI && t[i++] != first){
				cnt++;
				// now i-1 points to the first matching char 
			}
			int k=1;
			int j=i;
			while (k<m && t[j]==p[k]){
				j++;k++;
			}
			if (k==m) return i-m-1;
		}
		return -1;
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
				if (1+loctab[t[i]]>j) i=i+m-j;
				else i=i+m-1-loctab[t[i]];
				j=m-1;						
			}
		} while (i<n);
		return -1;
	}
	
	public void setText(File file) throws IOException{
		FileInputStream in = null;
		int c =-1;

        try {
            in = new FileInputStream(file);
            int len = in.available();
            t = new char[len];
            int i=0;
            while ((c = in.read()) != -1 && i<len) {
            	char cb = (char) c;
            	// if (cb<=0 || cb>255) System.out.println("i: "+i+", cb: "+cb);
            	t[i++] = cb;          	
            }
            n = t.length;
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
	
	public static void main(String[] args) {
		
		BoyerMoore bm;
		try {
			bm = new BoyerMoore(new File("resources/Goethe.txt"),
					"Herrrr");
			long ts = System.nanoTime();
			for (int i=0;i<1000;i++) bm.find();			
			long te = System.nanoTime();
			System.out.println(bm.find());
			System.out.println("time used: "+(te-ts)*1e-9+" m sec");
			System.out.println("Vergleiche: "+bm.cnt);
			ts = System.nanoTime();
			for (int i=0;i<1000;i++) bm.findBF();			
			te = System.nanoTime();
			System.out.println(bm.findBF());
			System.out.println("time used: "+(te-ts)*1e-9+" m sec");
			System.out.println("Vergleiche: "+bm.cnt);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

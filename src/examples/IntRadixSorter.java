package examples;


import java.util.*;

public class IntRadixSorter {
	// bucket array
	
	// Lenghth of one portion in bits:
	int len = 8;
	// number of portions:
	int anz = 32 % len == 0? 32/len : (32/len)+1;

	// number of buckets:
	int s = (int)Math.pow(2,len);
	
	
	int [][] buckets = new int[s][];
	int [] cnts = new int[s];
	
	

	public void bsort(int [] a){
		System.out.println("sweeps:"+anz+", buckets: "+s+", KeyLength: "+len);
		if (32 % len !=0 ) anz++;
		// initialize the buckets
		for (int i=0; i<s;i++) {
			buckets[i] = new int[(a.length/s)+1000];
			cnts[i] = 0; 
		}
		sort(a);
	}

	private void expand(int i){
		buckets[i] = Arrays.copyOf(buckets[i],buckets[i].length*2);
	}

	private void sort(int [] a){
		for (int d=0;d<anz;d++){ // loop over all portions of the int-number
			// .....
		}
	}

	public boolean check(int [] a){
		// returns true if a is sorted 
		for (int i=0;i<a.length-1;i++){
			if (a[i]>a[i+1]) return false; 
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//		System.out.println(257 & 255);	
		Random rand = new Random();
		int n = 20000000;
		int [] a = new int[n];
		for (int i=0;i<n;i++) a[i]=rand.nextInt(Integer.MAX_VALUE);
		IntRadixSorter ibs = new IntRadixSorter();
		System.out.println(n+" elements");
		long before = System.nanoTime();
		ibs.bsort(a);
		long after = System.nanoTime();
		System.out.println("sorted? "+ibs.check(a)+", time: "+(after-before)/1e6+" msec");

	}
}

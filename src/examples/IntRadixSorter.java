package examples;


import java.util.*;

public class IntRadixSorter {
	// bucket array
	int [][] buckets = new int[256][];
	int [] cnts = new int[256];

	public void bsort(int [] a){
		// initialize the buckets
		for (int i=0; i<256;i++) {
			buckets[i] = new int[(a.length/256)+1000];
			cnts[i] = 0; 
		}
		sort(a);
	}

	private void expand(int i){
		buckets[i] = Arrays.copyOf(buckets[i],buckets[i].length*2);
	}

	private void sort(int [] a){
		// .......
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
		int n = 100000000;
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

package examples;


import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Random;


/**
 * @author ps
 * Various sort programs for int arrays
 */
public class SortTest {
	
	
	public static long cnt;
	static Random rand = new Random();
	static int [] b;

	/**
	 * @param a int aray
	 * @return 'true' if 'a' is sorted
	 */
	public static boolean sortCheck(int[] a) {
		for (int i=0;i<a.length-1;i++){
			if (a[i]>a[i+1]) return false; 
		}
		return true;
	}
	
	public static void mergeSort(int[]a){
		b = new int[a.length];
		mSort(a,0,a.length-1);
	}
	
	private static void mSort(int[] a, int from, int to) {
		if (from == to) return; // nothing to do
		int mid = (from+to)/2;
		mSort(a,from,mid);
		mSort(a,mid+1,to);
		merge(a,from,mid,to);
	}

	/**
	 * Wrapper which calls the recursive version of the 
	 * quick sort program
	 * @param a the int array to be sorted
	 */
	public static void quickSort(int [] a){
		qSort(a,0,a.length-1);
	}

	/**
	 * recursive version of quick sort (sorts 
	 * the range a[from..to] of the int array 'a')
	 * @param a 
	 * @param from 
	 * @param to
	 */
	private static void qSort(int []a, int from, int to){
		if (from >= to) return; // nothing to do if sequence has length 1 or less
		int piv = partition(a,from,to);
		// now a[to..piv-1] <= a[piv] and 
		// a[piv+1..to]>=a[piv]
		qSort(a,from,piv-1);
		qSort(a,piv+1,to);
	}
	
	/**
	 * retuns piv and partitions the
	 * range a[from..to] such that all of the elements 
	 * in the range a[from..piv-1] are <= a[piv] and
	 * all elements in the range a[piv+1..to] are  >= a[piv]
	 * @param a
	 * @param from
	 * @param to
	 * @return the position 'piv' of the pivot
	 */
	private static int partition(int []a, int from, int to){
		if (from==to) return from;
		swap(a,rand.nextInt(to-from)+from,to);
		int pivot = a[to];
		int left = from-1;
		int right = to;
		while(true){
			while(a[++left]  < pivot); // stops at a swap candidate 
			while(a[--right] > pivot)
			{
				if (right==from) break; // break right decrementing when we reach from
			}
			// finished?
			if (right <= left) break;
			// no 
			swap(a,left,right);
		}
		// final swap
		swap(a,to,left);
		return left;      // return the final position of the pivot (to be changed!)
	}
	
	
	private static void merge(int[] a, int from, int mid, int to) {
		// merge the sections a[from..mid] and a[mid+1..to] into
		// b[from..to] and copy back
		int fPtr=from,sPtr=mid+1,toPtr=from;
		while(true){
			if (fPtr > mid) break; // reminder of second section already ok
			else if( sPtr>to) {
				// copy the reminder of first section
				while(fPtr <= mid) b[toPtr++] = a[fPtr++];
				break;
			}
			else {
				// both sections still have something to merge
				// copy the smaller of the candidates
				if (a[sPtr] > a[fPtr]) b[toPtr++]=a[fPtr++];
				else b[toPtr++] = a[sPtr++];
			}
		}
		while (--toPtr >= from) a[toPtr] = b[toPtr];		
	}

	/**
	 * Non optimized bubble sort for an int array 
	 * @param a
	 */
	public static void bubbleSort(int[] a) {
		cnt=0;
		int m = a.length-1;
		for(int i=m; i>0; i--){ 

			for (int k=0; k < i; k++){
				if(a[k]>a[k+1]) swap(a,k,k+1);
			}
			// now a[i] is on its final position!
		}
	}

	/**
	 * swap the array elements a[i] and a[k]
	 * @param a int array 
	 * @param i position in the array 'a'
	 * @param k position in the array 'a'
	 */
	static void swap(int [] a, int i, int k){
		int tmp=a[i];
		a[i]=a[k];
		a[k]=tmp;
		cnt++;
	}
	
	static void heapSort(int [] a){
		// make 'a' to be an max-heap: 
//		for (int i=1; i<a.length; i++) upHeap(a,i);
		for (int i=a.length/2;i>=0;i--) downHeap(a,i,a.length);
		// System.out.println("max-heap? "+heapCheck(a));
		for (int i = a.length-1; i>0; i--){
			swap(a,0,i); // now a[i] is at its final position
			downHeap(a, 0, i);
		}
	}

	private static void upHeap(int[] a, int i) {
		// assume a[0..i-1] is a max-heap, swap element
		// at position i with its parent and so on
		// until a[0..i] is a max-heap
		int parent = (i-1)/2;
		while (i>0 && a[parent] < a[i]){
			swap(a,parent,i);
			i = parent;
			parent = (i-1)/2;
		}
	}

	private static void downHeap(int[] a, int i, int len) {
		// assume a [i..len-1] is a max-heap, but the element
		// at position i possibly violates the heap condition.
		// swap a[i] with its bigger child until a[i..len-1] is a heap.
		int left = i*2+1;
		while (left < len){
			int cand = left;
			int right = left+1;
			// find the bigger child (if there are two)
			if (right < len && a[right] > a[left]) cand = right;
			if (a[i] >= a[cand]) break; 
			swap(a,i,cand);
			i = cand;
			left = i*2+1; 
		} 		
	}

	private static boolean heapCheck(int [] a){
		for (int i=1;i<a.length;i++) if (a[(i-1)/2]<a[i]) return false;
		return true; // we have a correct max-heap
	}

	public static void main(String[] args) {
		long t1=0,t2=0,te1=0,te2=0,eTime=0,time=0;
		int n = 1000000;
		// we need a random generator
		Random rand=new Random();
		//rand.setSeed(54326346); // initialize always in the same state
		ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();	
		// new array
		int [] a = new int[n];
		// fill it randomly
		for (int i=0;i<a.length ;i++) {
			a[i]=1;//rand.nextInt(n);
		}
		cnt=0;  // for statistcs reasons
		// get Time
		te1=System.nanoTime();
		t1 = threadBean.getCurrentThreadCpuTime();
		quickSort(a);
		// System.out.println("heap? "+heapCheck(a));
		te2 = System.nanoTime();
		t2 = threadBean.getCurrentThreadCpuTime();
		time=t2-t1;
		eTime=te2-te1;
		System.out.println("# elements: "+n);
		System.out.println("CPU-Time usage: "+time/1000000.0+" ms");
		System.out.println("elapsed time: "+eTime/1e6+" ms");
		System.out.println("sorted? "+sortCheck(a));
		System.out.println("swap operation needed: "+cnt);
	}


}

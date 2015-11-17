package examples;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Hashtable;

public class MyPriorityQueue<K extends Comparable<? super K>, E> implements
		PriorityQueue<K, E> {
	
	// auxiliary class for the locators
	class PQLoc<K1 extends Comparable<? super K1>,E1> 
		implements Locator<K1 , E1>{
		
		E1 elem;
		K1 key;
		Object creator = MyPriorityQueue.this;
		int pos; // index in the heap array
		
		@Override
		public E1 element() {
			return elem;
		}

		@Override
		public K1 key() {
			return key;
		}
		
	}

	// instance variables
	
	private PQLoc<K,E> [] stor =(PQLoc<K,E>[]) new PQLoc[256]; 
	private int size;
	
	// instance methods
	
	private PQLoc<K,E> castToLNode(Locator<K,E> p){
		PQLoc<K,E> n;
		try {
			n = (PQLoc<K,E>) p;
		} catch (ClassCastException e) {
			throw new RuntimeException("This is not a Locator belonging to MyPriorityQueue"); 
		}
		if (n.creator == null) throw new RuntimeException("locator was allready deleted!");
		if (n.creator != this) throw new RuntimeException("locator belongs to another PriorityQueue!");			
		return n;
	}
	
	@Override
	public Locator<K, E> showMin() {
		if (size == 0) return null;
		return stor[1];
	}

	@Override
	public Locator<K, E> removeMin() {
		if (size==0) return null;
		PQLoc<K,E> n = stor[1];
		remove(n);
		return n;
	}

	@Override
	public Locator<K, E> insert(K key, E element) {
		PQLoc<K,E> loc = new PQLoc<>();
		loc.elem = element;
		loc.key=key;
		if (size == stor.length-1) {
			stor = Arrays.copyOf(stor,stor.length*2);
		}
		stor[++size]=loc;
		loc.pos = size;
		upHeap(size);
		return loc;
	}

	private void upHeap(int i) {
		while (i > 1 && stor[i].key.compareTo(stor[i/2].key) < 0){
			swap(i,i/2);
			i=i/2;
		}
	}

	private void downHeap(int i) {
		int left = i*2;
		while (left <= size){
			int right = left+1;
			int cand = left;
			if (right < size && 
					stor[left].key.compareTo(stor[right].key) > 0) cand = right;
			if (stor[i].key.compareTo(stor[cand].key) <= 0) break;
			swap(i,cand);
			i=cand;
			left=i*2;
		}
		
	}

	private void swap(int i, int k) {
		PQLoc<K,E> tmp = stor[i];
		stor[i]=stor[k];
		stor[k]=tmp;
		// do'nt forget the 'pos' values:
		stor[i].pos=i;
		stor[k].pos=k;
	}

	@Override
	public void remove(Locator<K, E> loc) {
		PQLoc<K,E> n = castToLNode(loc);
		PQLoc<K,E> n2 = stor[size];
		swap(n.pos,size--);
		upHeap(n2.pos);
		downHeap(n2.pos);
		n.creator = null;
	}

	@Override
	public void replaceKey(Locator<K, E> loc, K newKey) {
		PQLoc<K,E> n = castToLNode(loc);
		n.key = newKey;
		// only one of the following calls has an effect
		upHeap(n.pos);
		downHeap(n.pos);
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	public static void main(String[] args) {
		PriorityQueue<Integer,String> pq = new MyPriorityQueue<>();
		pq.insert(7,"bla");
		pq.insert(11,"bla");
		pq.insert(5,"bla");
		Locator<Integer,String > l = pq.insert(14,"bla");
		pq.insert(6,"bla");
		pq.insert(20,"bla");
		pq.remove(l);
		
		System.out.println(pq.removeMin().key());
		System.out.println(pq.removeMin().key());
		System.out.println(pq.removeMin().key());
		System.out.println(pq.removeMin().key());
		System.out.println(pq.removeMin().key());
//		System.out.println(pq.removeMin().key());
		java.util.Random rand = new java.util.Random();
		int n = 10000000;
		long start = System.nanoTime();
		for (int i=0;i<n;i++) pq.insert(n-i,null);
		for (int i=0;i<n;i++) {
			pq.removeMin();
		}
		long end = System.nanoTime();
		System.out.println("elapsed time: "+(end-start)/1.e9+" s for "+n+" locators");
		Hashtable z;
		
	}

}

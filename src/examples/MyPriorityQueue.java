package examples;

public class MyPriorityQueue<K extends Comparable<? super K>, E> implements
		PriorityQueue<K, E> {
	
	// auxiliary class for the locators
	class PQLoc<K1 extends Comparable<? super K1>,E1> implements Locator<K1 , E1>{
		
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
	
	
	@Override
	public Locator<K, E> showMin() {
		if (size == 0) return null;
		return stor[1];
	}

	@Override
	public Locator<K, E> removeMin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locator<K, E> insert(K key, E element) {
		PQLoc<K,E> loc = new PQLoc<>();
		loc.elem = element;
		loc.key=key;
		stor[++size]=loc;
		upHeap(size);
		return loc;
	}

	private void upHeap(int size2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Locator<K, E> loc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replaceKey(Locator<K, E> loc, K newKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		PriorityQueue<Integer,String> pq = new MyPriorityQueue<>();
		pq.insert(7,"bla");
		pq.insert(11,"bla");
		pq.insert(5,"bla");
		pq.insert(4,"bla");
		System.out.println(pq.showMin().key());
	}

}

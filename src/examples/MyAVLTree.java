/**
 * 
 */
package examples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author ps
 *
 */
public class MyAVLTree<K extends Comparable<? super K>, E> implements
		OrderedDictionary<K, E> {

	class AVLNode implements Locator<K, E>{
		
		AVLNode parent,left,right;
		Object creator = MyAVLTree.this;
		E elem;
		K key;
		int height;

		/* (non-Javadoc)
		 * @see examples.Position#element()
		 */
		@Override
		public E element() {
			return elem;
		}

		/* (non-Javadoc)
		 * @see examples.Locator#key()
		 */
		@Override
		public K key() {
			return key;
		}
		
		boolean isExternal(){
			return left==null; // is also true for right
		}
		
		boolean isLeftChild(){
			return parent != null && parent.left==this;
		}
		
		boolean isRightChild(){
			return parent != null && parent.right==this;
		}
		
		void expand(K key,E elem){
			this.elem = elem;
			this.key = key;
			left = new AVLNode();
			right = new AVLNode();
			left.parent = this;
			right.parent = this;
			height = 1;
		}
	}

	
	
	// istance variables:
	private AVLNode root = new AVLNode();
	private int size;
	
	private AVLNode checkAndCast(Locator<K,E> p){
		try {
			AVLNode n = (AVLNode) p;			
			if (n.creator == null) throw new RuntimeException(" allready removed locator!");
			if (n.creator != this) throw new RuntimeException(" locator belongs to another AVLTree instance");
			return n;
		} catch (ClassCastException e) {
			throw new RuntimeException(" locator belongs to another container-type ");
		}
	}

	





	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public Locator<K, E> find(K key) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Locator<K, E>[] findAll(K key) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Locator<K, E> insert(K key, E o) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void remove(Locator<K, E> loc) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public Locator<K, E> closestBefore(K key) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Locator<K, E> closestAfter(K key) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Locator<K, E> next(Locator<K, E> loc) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Locator<K, E> previous(Locator<K, E> loc) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Locator<K, E> min() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Locator<K, E> max() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Iterator<Locator<K, E>> sortedLocators() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] argv){
		MyAVLTree<Integer, String> t = new MyAVLTree<>();
		Random rand = new Random(3463453);
		int n  = 1000000;
		Locator<Integer,String>[] locs = new Locator[n];
		long time1 = System.currentTimeMillis();
		for (int i=0;i<n;i++) {
			 locs[i]=t.insert(rand.nextInt(n),""+i);
			//locs[i]=t.insert(i, "bla");
		}
		// for (int i= 0; i<n/2; i++ ) t.remove(locs[i]);
		long time2 = System.currentTimeMillis(); 
		System.out.println(time2-time1);
		System.out.println(t.root.height);
		// System.out.println((t.find(13).element()));
//		t.print();
//		Locator<Integer,String> loc = t.min();
//		while (loc != null){
//			System.out.println(loc.key());
//			loc = t.next(loc);
//		}
	}

	
}

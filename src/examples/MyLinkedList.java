package examples;

import java.util.Iterator;

public class MyLinkedList<E> implements List<E> {
	
	// auxiliary class for the nodes
	class LNode implements Position<E>{
		E elem;
		LNode prev,next;
		Object creator = MyLinkedList.this;
		
		@Override
		public E element() {
			return elem;
		}		
	}
	

	// instance variables
	private LNode first,last;
	private int size;

	
	/**
	 * @param p a position which should belong to this MyLinkedList instance
	 * @return the casted object 'p' which (is tested for validity)
	 */
	private LNode castToLNode(Position p){
		LNode n;
		try {
			n = (LNode) p;
		} catch (ClassCastException e) {
			throw new RuntimeException("This is not a Position belonging to MyLinkedList"); 
		}
		if (n.creator == null) throw new RuntimeException("position was allready deleted!");
		if (n.creator != this) throw new RuntimeException("position belongs to another MyLinkedList instance!");			
		return n;
	}

	@Override
	public Position<E> first() {
		return first;
	}

	@Override
	public Position<E> last() {
		return last;
	}

	@Override
	public boolean isFirst(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLast(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Position<E> next(Position<E> p) {
		LNode n = (LNode) p;
		if (n.creator != this) throw new RuntimeException("invalis Position");
		return n.next;
	}

	@Override
	public Position<E> previous(Position<E> p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> insertFirst(E o) {
		LNode n = new LNode();
		n.elem = o;
		n.next = first;
		if (first == null) last=n;
		else first.prev = n;
		first = n;
		size++;
		return n;
	}

	@Override
	public Position<E> insertLast(E o) {
		LNode n = new LNode();
		n.elem = o;
		n.prev = last;
		if (last == null) first=n;
		else last.next = n;
		last = n;
		size++;
		return n;		
	}

	@Override
	public Position<E> insertBefore(Position<E> p, E o) {
		LNode n = castToLNode(p);
		LNode newN = new LNode();
		newN.elem = o;
		
		
		newN.next = n;
		newN.prev = n.prev;
		n.prev = newN;
		if (newN.prev==null) first = newN ;
		else newN.prev.next = newN;
		size++;
		return newN;	
	}

	@Override
	public Position<E> insertAfter(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Position<E> p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> elements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		List<String> li = new MyLinkedList<>();
		Position<String> p =li.insertFirst("hans");
		li.insertFirst("beat");
		p = li.insertFirst("ida");
		System.out.println(li.next(li.next(p)).element());
		//li.insertBefore(p,"hans 1");
		//Iterator<String> it = li.elements();
		
		li.remove(p);
		li.insertAfter(p, "bla");
//		while (it.hasNext()){
//			String s = it.next();
//			System.out.println(s);
//		}
		
		
				
	}

}

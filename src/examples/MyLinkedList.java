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
	
	// instance variables of MyLinkedList
	private LNode first,last;
	private int size;

	@Override
	public Position<E> first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> last() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> insertLast(E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> insertBefore(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
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
		List<String> ll = new MyLinkedList<>(); 
		Position<String> p = ll.insertFirst("hans");
		ll.insertFirst("beat");
		ll.insertFirst("ida");
		ll.insertAfter(p,"hans 2");
		Iterator<String> it = ll.elements();
		while (it.hasNext()){
			String s = it.next();
			System.out.println(s);
		}
		
		
		
	}

}

package examples;

public class MyQueue<E> implements Queue<E> {

	private E[] stor = (E[]) new Object[3];
	private int len,head,tail;
	
	@Override
	public void enqueue(E o) {
		// TODO Auto-generated method stub

	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E head() {
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
		MyQueue<String> q = new MyQueue<>(); 
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		System.out.println(q.dequeue());		
		q.enqueue("d");
		System.out.println(q.dequeue());		
		System.out.println(q.dequeue());		
		
		
	}

}

package examples;

public class MyQueue<E> implements Queue<E> {

	private E[] stor = (E[]) new Object[1];
	private int len,head,tail;

	private void expand(){
		E[] newStor = (E[]) new Object[stor.length*2];
		for (int i=0;i<len;i++) {
			newStor[i]=stor[head++];
			if (head==stor.length) head=0;
		}
		stor = newStor;
		head=0;
		tail=len;
	}
	
	@Override
	public void enqueue(E o) {
		if (len == stor.length) expand();
		stor[tail++] = o;
		if (tail==stor.length) tail=0;
		len++;
	}

	@Override
	public E dequeue() {
		if (len==0) throw new RuntimeException("empty Queue!");
		E ret = stor[head++];
		if (head == stor.length) head=0; // wrap around !
		len--;
		return ret;
	}

	@Override
	public E head() {
		return stor[head];
	}

	@Override
	public int size() {
		return len;
	}

	@Override
	public boolean isEmpty() {
		return len == 0;
	}

	public static void main(String[] args) {
		MyQueue<String> q = new MyQueue<>(); 
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		System.out.println(q.dequeue());		
		System.out.println(q.dequeue());		
		q.enqueue("d");
		System.out.println(q.dequeue());		
		System.out.println(q.dequeue());
	}

}

package examples;

import java.util.Arrays;

public class MyStack<E> implements Stack<E> {

	// instance variables
	private E [] stor = (E[]) new Object[1]; 
	private int ptr; // points to the next insertion position
	
	private void expand(){
		stor = Arrays.copyOf(stor,stor.length*2);
	}
	
	
	@Override
	public void push(E o) {
		if (ptr==stor.length) expand();
		stor[ptr++]=o;
	}

	@Override
	public E pop() {
		try {
			return stor[--ptr];
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException("stack underflow");
		}
	}

	@Override
	public E top() {
		try {
			return stor[ptr-1];
		} catch (IndexOutOfBoundsException e) {
			throw new RuntimeException("stack underflow");
		}
	}

	@Override
	public int size() {
		return ptr;
	}

	@Override
	public boolean isEmpty() {
		return ptr==0;
	}
	
	static public void main(String[] argv){
		MyStack<String> st = new MyStack();
		st.push("hans");
		st.push("Beat");
		st.push("Ida");
		st.push("Susi");
		st.pop();
		st.pop();
		st.pop();
		st.pop();
		String last = st.pop();
		System.out.println(last);
	}

}

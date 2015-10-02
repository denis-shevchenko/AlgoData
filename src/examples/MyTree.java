package examples;

import java.util.Iterator;

import javafx.geometry.Pos;
import examples.MyLinkedList.LNode;
import examples.MyTree2.TNode;

public class MyTree<E> implements Tree<E> {

	// auxiliary class for the tree nodes
	class TNode implements Position<E>{
		E elem;
		TNode parent;
		MyLinkedList<TNode> children = new MyLinkedList<>();
		Position<TNode> childrenPos;
		Object creator = MyTree.this;

		@Override
		public E element() {
			return elem;
		}
		
	}
	
	// instance variables
	
	
	private TNode root;
	private int size;
	
	private TNode castToTNode(Position p){
		TNode n;
		try {
			n = (TNode) p;
		} catch (ClassCastException e) {
			throw new RuntimeException("This is not a Position belonging to myTree"); 
		}
		if (n.creator == null) throw new RuntimeException("position was allready deleted!");
		if (n.creator != this) throw new RuntimeException("position belongs to another MyTree instance!");			
		return n;
	}

	
	@Override
	public Position<E> root() {
		return root;
	}

	@Override
	public Position<E> createRoot(E o) {
		root = new TNode();
		root.elem=o;
		return root;
	}

	@Override
	public Position<E> parent(Position<E> child) {
		TNode n = castToTNode(child);
		return n.parent;
	}

	@Override
	public Iterator<Position<E>> childrenPositions(Position<E> parent) {
		TNode n = castToTNode(parent);
		return new Iterator<Position<E>>() {
			Iterator<TNode> it = n.children.elements();
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public Position<E> next() {
				return it.next();
			}
		};
	}

	@Override
	public Iterator<E> childrenElements(Position<E> parent) {
		TNode n = castToTNode(parent);
		return new Iterator<E>() {
			Iterator<TNode> it = n.children.elements();
			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public E next() {
				return it.next().elem;
			}
		};
	}

	@Override
	public int numberOfChildren(Position<E> parent) {
		TNode n = castToTNode(parent);
		return n.children.size();
	}

	@Override
	public Position<E> insertParent(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addChild(Position<E> parent, E o) {
		TNode n = castToTNode(parent);
		TNode newN = new TNode();
		newN.elem = o;
		newN.parent = n;
		newN.childrenPos = n.children.insertLast(newN);
		size++;
		return newN;
	}

	@Override
	public Position<E> addChildAt(int pos, Position<E> parent, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addSiblingAfter(Position<E> sibling, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> addSiblingBefore(Position<E> sibling, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Position<E> p) {
		TNode n = castToTNode(p);
		if (n.children.size() != 0) throw new RuntimeException("cannot remove node with children!");
		n.creator = null;
		size--;
		if (n == root) root = null;
		else n.parent.children.remove(n.childrenPos);
	}

	public void removeSubtree(Position<E> p){
		Iterator<Position<E>> it = childrenPositions(p);
		while (it.hasNext()) removeSubtree(it.next());	
		remove(p);
	}
	
	@Override
	public boolean isExternal(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInternal(Position<E> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	public void print(){
		if (root != null)
		print(root,"");	
	}
	
	
	private void print(Position<E> r, String ind) {
		System.out.println(ind+r.element());
		Iterator<Position<E>> it = childrenPositions(r);
		while (it.hasNext()) print(it.next(),ind+"..");
	}


	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		if (size==0) return "";
		StringBuilder sb = new StringBuilder("--- begin tree ---\n");
		buildString(root,"",sb);
		sb.append("---  end tree  ---");
		return sb.toString();
	}

	private void buildString(Position<E> r, String in, StringBuilder sb) {
		sb.append(in);
		sb.append(r.element().toString());
		sb.append("\n");
		Iterator<Position<E>> it = childrenPositions(r);
		while(it.hasNext()) buildString(it.next(),".."+in,sb);
	}

	public int height(){
		return height(root);
	}

	private int height(Position<E> r) {
		int h=0;
		Iterator<Position<E>> it = childrenPositions(r);
		while(it.hasNext()){
			int height = height(it.next());
			if(height > h) h=height ;
		}
		return h+1;
	}

	public static void main(String[] args) {
		MyTree<String> t = new MyTree<String>();
		Position<String> tit = t.createRoot("Buch");
		Position<String> k1 = t.addChild(tit,"Kapitel 1");
		Position<String> k2 = t.addChild(tit,"Kapitel 2");
		Position<String> k3 = t.addChild(tit,"Kapitel 3");
		Position<String> k1u1 = t.addChild(k1,"Abschnitt 1");
		t.addChild(k1,"Abschnitt 2");
		t.addChild(k1,"Abschnitt 3");
		t.addChild(k1,"Abschnitt 4");
		t.addChild(k1,"Abschnitt 5");
//		t.print();
		System.out.println(t);
//		System.out.println(t.height());
//		System.out.println(t.maxMultPos().element());
		System.out.println(deepestPos(t).element());
	}

	static Position deepest;
	static int maxDepth;
	
	static Position deepestPos(Tree t){
		maxDepth=0;
		deepest = t.root();
		if (t.size() == 0) return null;
		deepestPos(t,t.root(),0);
		return deepest;
	}
	
	static void deepestPos(Tree t, Position p,int lev){
		if (lev > maxDepth){
			maxDepth = lev;
			deepest = p; 
		}		
		Iterator<Position> it = t.childrenPositions(p);
		while(it.hasNext()) deepestPos(t,it.next(),lev+1);
	}
}

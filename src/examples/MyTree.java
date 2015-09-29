package examples;

import java.util.Iterator;

import examples.MyLinkedList.LNode;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numberOfChildren(Position<E> parent) {
		// TODO Auto-generated method stub
		return 0;
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public E replaceElement(Position<E> p, E o) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		MyTree<String> t = new MyTree<String>();
		Position<String> tit = t.createRoot("Buch");
		Position<String> k1 = t.addChild(tit,"Kapitel 1");
		Position<String> k2 = t.addChild(tit,"Kapitel 2");
		Position<String> k3 = t.addChild(tit,"Kapitel 3");
		Position<String> k1u1 = t.addChild(k1,"Abschnitt 1");

	}

}

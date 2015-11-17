package examples;
import graphLib.*;
import graphTool.Algorithm;
import graphTool.Attribute;
import graphTool.GraphTool;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;


public class GraphExamples<V,E> {


	/**
	 * vists all (recursively) vertices starting with 'v' which
	 * can be reached
	 * @param v
	 */
	@Algorithm(vertex=true)
	public void visitDFS(Graph<V,E> g,Vertex<V> v, GraphTool<V,E> t) {
		v.set(Attribute.VISITED,null);
		v.set(Attribute.color,Color.red);
		t.show(g);
		Iterator<Edge<E>> it = g.incidentEdges(v);
		while (it.hasNext()){
			Edge<E> e = it.next();
			Vertex<V> w = g.opposite(e, v);
			if ( ! w.has(Attribute.VISITED)) {
				e.set(Attribute.color,Color.red);
				visitDFS(g,w,t);
			}
		}
	}



	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		
		// make an undirected graph
		IncidenceListGraph<String,String> g = 
				new IncidenceListGraph<>(false);
		GraphExamples<String,String> ge = new GraphExamples<>();
		Vertex vA = g.insertVertex("A");
		vA.set(Attribute.name,"A");
		Vertex vB = g.insertVertex("B");
		vB.set(Attribute.name,"B");
		Vertex vC = g.insertVertex("C");
		vC.set(Attribute.name,"C");
		Vertex vD = g.insertVertex("D");
		vD.set(Attribute.name,"D");
		Vertex vE = g.insertVertex("E");
		vE.set(Attribute.name,"E");
		Vertex vF = g.insertVertex("F");
		vF.set(Attribute.name,"F");
		Vertex vG = g.insertVertex("G");
		vG.set(Attribute.name,"G");

		Edge e_a = g.insertEdge(vA,vB,"AB");
		Edge e_b = g.insertEdge(vD,vC,"DC");
		Edge e_c = g.insertEdge(vD,vB,"DB");
		Edge e_d = g.insertEdge(vC,vB,"CB");
		Edge e_e = g.insertEdge(vC,vE,"CE");
		e_e.set(Attribute.weight,2.0);
		Edge e_f = g.insertEdge(vB,vE,"BE"); 
		e_f.set(Attribute.weight, 7.0); 
		Edge e_g = g.insertEdge(vD,vE,"DE");
		Edge e_h = g.insertEdge(vE,vG,"EG");
		e_h.set(Attribute.weight,3.0);
		Edge e_i = g.insertEdge(vG,vF,"GF");
		Edge e_j = g.insertEdge(vF,vE,"FE");

		GraphTool t = new GraphTool(g,ge);
		
		//    A__B     F
		//      /|\   /|
		//     / | \ / |
		//    C__D__E__G   
		//    \     /
		//     \___/
		//      
	}


}

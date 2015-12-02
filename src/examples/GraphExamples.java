package examples;
import graphLib.*;
import graphTool.Algorithm;
import graphTool.Attribute;
import graphTool.GraphTool;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class GraphExamples<V,E> {
	@Algorithm
	public void kruskal(Graph<V,E> g, GraphTool<V, E> t){
		// gives the Attribute MSF to each
		// edge belonging to an minimal spanning forest
		
		// create clusters, put the vertex in it  
		// and assign them to the vertice
		Iterator<Vertex<V>> it = g.vertices();
		while(it.hasNext()){
			Vertex v =it.next();
			ArrayList<Vertex<V>> cluster = new ArrayList<>();
			cluster.add(v);
			v.set(Attribute.CLUSTER,cluster);
		}
		PriorityQueue<Double,Edge<E>> pq = new MyPriorityQueue<>(); 	
		Iterator<Edge<E>> eit = g.edges();
		while(eit.hasNext()){
			Edge e = eit.next();
			double weight = 1;
			if (e.has(Attribute.weight)) weight=(Double)e.get(Attribute.weight);
			pq.insert(weight, e);
		}
		while (! pq.isEmpty()){
			Edge e = pq.removeMin().element();
			Vertex[] endPts = g.endVertices(e);
			ArrayList<Vertex> cluster0 = (ArrayList)endPts[0].get(Attribute.CLUSTER);
			ArrayList<Vertex> cluster1 = (ArrayList)endPts[1].get(Attribute.CLUSTER);
			if (cluster1 != cluster0){
				// we found an MSF edge
				e.set(Attribute.color,Color.GREEN);
				t.show(g);
				for(Vertex w:cluster1){
					cluster0.add(w);
					w.set(Attribute.CLUSTER,cluster0);
				}
			}
			
		}
	
	
	}	
	
	
	   @Algorithm(vertex=true)
	    public void dijkstra(Graph<V,E> g,Vertex<V> s, GraphTool<V,E> t){
	//-----------------------------------------------------------------

	        
	    	t.show(g);
	//-----------------------------------------------------------------
	        
	        // sets the attribute 's' of each vertex 'u' from wich 
	        // we can reach 's' to the value 'g' where 'g' is the gateway
	        // for 'u' on the shortest path from 'u' to 's' 
	        MyPriorityQueue<Double, Vertex<V>> pq = new MyPriorityQueue<>();
	        Iterator<Vertex<V>> it = g.vertices();
	        // put all vertices to pq and give them
	        // an attribute Attribut.DISTANCE and PQLOCATOR
	        while(it.hasNext()){
	            Vertex<V> v = it.next();
	            v.set(Attribute.DISTANCE,Double.POSITIVE_INFINITY);
	            v.set(Attribute.string,"inf");
	            Locator<Double,Vertex<V>> loc = pq.insert(Double.POSITIVE_INFINITY,v);
	            v.set(Attribute.PQLOCATOR,loc);
	            // v.set(Attribut.color, Color.red);
	            t.show(g);
	        }
	        // correct the attributes for s
	        s.set(Attribute.string,"0");
	        s.set(Attribute.DISTANCE,0.0);
	        t.show(g);
	        pq.replaceKey((Locator<Double,Vertex<V>>)s.get(Attribute.PQLOCATOR),0.0);
	        while( ! pq.isEmpty()){
	            Vertex<V> u = pq.removeMin().element();    
	            u.set(Attribute.color,Color.GREEN);
	            if (u.has(Attribute.DISCOVERY)){
	                Edge<E> e = (Edge<E>)u.get(Attribute.DISCOVERY);
	                e.set(Attribute.color,Color.GREEN);
	                t.show(g);
	            }

	            // now make the relaxation step for all 
	            // neighbours:
	            Iterator<Edge<E>> eIt;
	            if (g.isDirected()) eIt = g.incidentInEdges(u); // backwards!
	            else eIt = g.incidentEdges(u);
	            while (eIt.hasNext()){
	                Edge<E> e = eIt.next();
	                double weight = 1.0; // default weight
	                //Original version: if (e.has(Attribut.weight)) weight = (Double)e.get(Attribut.weight);
	                
	                //Whether the graph comes from graphexamples or not it has to be casted differently
	                if (e.has(Attribute.weight)) {
	                   weight = (Double)e.get(Attribute.weight);
	                }
	                Vertex<V> z = g.opposite(e, u);
	                //Relaxation
	                double newDist = (Double) u.get(Attribute.DISTANCE);// +weight;
	                if (newDist < (Double)z.get(Attribute.DISTANCE)){
	                	z.set(Attribute.DISTANCE,(Double)newDist);
	                	z.set(Attribute.string,""+newDist);
	                	z.set(Attribute.DISCOVERY,e);
	                	z.set(s,u);
	                	pq.replaceKey((Locator<Double,Vertex<V>>)z.get(Attribute.PQLOCATOR),newDist);
	                	t.show(g);
	                }
	            }
	        }
	        t.show(g);
	    }


	   
	@Algorithm
	public void topologicalNumbering(Graph<V,E> g, GraphTool<V,E> t){
		if ( ! g.isDirected()) throw new RuntimeException("should be a directed graph");
		Iterator<Vertex<V>> it = g.vertices(); 
		LinkedList<Vertex> s = new LinkedList();
		while(it.hasNext()){
			Vertex v = it.next();
			v.set(Attribute.DEPENDENCIES,g.inDegree(v));
			if (g.inDegree(v)==0) s.push(v);
		}
		int num=0;
		while (! s.isEmpty()){
			Vertex<V> v = s.pop();
			v.set(Attribute.string,""+num);
			num++;
			v.set(Attribute.color,Color.GREEN);
			t.show(g);
			Iterator<Edge<E>> eit = g.incidentOutEdges(v);
			while (eit.hasNext()){
				Vertex<V> w = g.opposite(eit.next(),v);
				int depcnt = ((Integer)w.get(Attribute.DEPENDENCIES))-1;
				w.set(Attribute.DEPENDENCIES,depcnt);
				if (depcnt==0) s.push(w);			
			}
		}
		if (num!= g.numberOfVertices()) throw new RuntimeException("graph is not acyclic!");
	}
	
	
	@Algorithm(vertex=true,vertex2=true)
	public void findPath(Graph<V,E> g, Vertex<V> v,Vertex<V> v2, GraphTool<V,E> t) {
		LinkedList<Vertex<V>> list = new LinkedList<>();
		v.set(Attribute.color,Color.BLUE);
		v2.set(Attribute.color,Color.RED);
		v.set(Attribute.VISITED,null);
		t.show(g);
		list.addLast(v);
		while (! list.isEmpty()){		
			Vertex w = list.removeFirst();
			Iterator<Edge<E>> it = g.incidentEdges(w); 
			while (it.hasNext()){
				Edge e = it.next();
				Vertex n = g.opposite(e, w);
				if ( ! n.has(Attribute.VISITED)){
					n.set(Attribute.VISITED,null);
					n.set(Attribute.DISCOVERY,e);
					if (n==v2) break;
					list.addLast(n);
				}

			}
			if (v2.has(Attribute.DISCOVERY)) break;		
		}
		while (v2.has(Attribute.DISCOVERY)){
			Edge e = (Edge) v2.get(Attribute.DISCOVERY);
			e.set(Attribute.color,Color.green);
			t.show(g);
			v2 = g.opposite(e, v2);
		}
	}


	
	
	/**
	 * visits all vertices starting with 'v' 
	 * using a breadth first search
	 * 
	 * @param v
	 */
	@Algorithm(vertex=true)
	public void visitBFS(Graph<V,E> g, Vertex<V> v, GraphTool<V,E> t) {
		LinkedList<Vertex<V>> list = new LinkedList<>();
		v.set(Attribute.color,Color.BLUE);
		v.set(Attribute.VISITED,null);
		list.addLast(v);
		t.show(g);
		while (! list.isEmpty()){		
			Vertex w = list.removeFirst();
			Iterator<Edge<E>> it = g.incidentEdges(w); 
			while (it.hasNext()){
				Edge e = it.next();
				Vertex n = g.opposite(e, w);
				if ( ! n.has(Attribute.VISITED)){
					e.set(Attribute.color,Color.GREEN);
					n.set(Attribute.color,Color.GREEN);
					n.set(Attribute.VISITED,null);
					n.set(Attribute.DISCOVERY,e);	// for path finding!
					n.set(v,e); // set the gatway-edge wich leads to 'v'
					list.addLast(n);
					t.show(g);
				}
			}
		}
	}

	/**
	 * visits by a depth-first-search  all reachable vertices starting with 'v'
	 * @param v
	 */
	@Algorithm(vertex=true)
	public void visitDFS2(Graph<V,E> g, Vertex<V> v, GraphTool<V,E> t) {
		LinkedList<Vertex<V>> list = new LinkedList<>();
		v.set(Attribute.color,Color.BLUE);
		v.set(Attribute.DISCOVERY,null);
		list.addLast(v);
		t.show(g);
		while (! list.isEmpty()){		
			Vertex w = list.removeLast();		
			if ( ! w.has(Attribute.VISITED)){
				Edge de = (Edge) (w.get(Attribute.DISCOVERY));
				if (de!=null) de.set(Attribute.color,Color.green);
				w.set(Attribute.VISITED,null);
				w.set(Attribute.color,Color.GREEN);
				t.show(g);
				Iterator<Edge<E>> it = g.incidentEdges(w); 
				while (it.hasNext()){
					Edge e = it.next();
					Vertex n = g.opposite(e, w);
					if (! n.has(Attribute.VISITED)){
						n.set(Attribute.DISCOVERY,e);
						list.addLast(n);
					}

				}
			}			
		}
	}


	/**
	 * visits (recursively) all reachable vertices starting with 'v'
	 * @param v
	 */
	@Algorithm(vertex=true)
	public void visitDFS(Graph<V,E> g, Vertex<V> v, GraphTool<V,E> t) {
		v.set(Attribute.VISITED,null);
		v.set(Attribute.color,Color.green);
		t.show(g);
		Iterator<Edge<E>> it = g.incidentEdges(v);
		while (it.hasNext()){
			Edge<E> e = it.next();
			Vertex<V> w = g.opposite(e, v);
			if ( ! w.has(Attribute.VISITED)) {
				e.set(Attribute.color,Color.green);
				visitDFS(g,w,t);
			}
		}
	}



	/**
	 * @param args
	 * 
	 */
	public static void main(String[] args) {

		//		 make an undirected graph
		IncidenceListGraph<String,String> g = 
				new IncidenceListGraph<>(true);
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

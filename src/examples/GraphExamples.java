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

	@Algorithm
	public void kruskal(Graph<V,E> g, GraphTool t){
//-----------------------------------------------------------------
		t.show(g);
//-----------------------------------------------------------------
		
		// gives the Object MSF to each
		// edge belonging to an minimal spanning forest

		// create clusters, put the vertex in it  
		// and assign them to the vertices
		Iterator<Vertex<V>> it = g.vertices();
		while(it.hasNext()){
			Vertex<V> v = it.next();
			ArrayList<Vertex<V>> cluster = new ArrayList<>();
			cluster.add(v);
			v.set(Attribute.CLUSTER,cluster);
		}
		PriorityQueue<Double,Edge<E>> pq = new MyPriorityQueue<>();
		Iterator<Edge<E>> eIt = g.edges();
		while(eIt.hasNext()){
			Edge<E> e = eIt.next();
			double weight = 1.0;
//-----------------------------------------------------------------
			//Original version: if (e.has(Attribut.weight)) weight = (Double)e.get(Attribut.weight);
			
			//Whether the graph comes from graphexamples or not it has to be casted differently
			if (e.has(Attribute.weight)) {
				
				if (e.get(Attribute.weight) instanceof String) {
					
					try {
						weight = Double.parseDouble((String) e.get(Attribute.weight));
					} catch (NumberFormatException ex) {
						//If the string cannot be parsed, set the weight to 0
						System.out.println("@GraphExamples: Failed to parse a string to a double");
						weight = 0;
					}
				}
					
				//For graphs from graphexamples
				else weight = (Double)e.get(Attribute.weight);
			}
//-----------------------------------------------------------------
			pq.insert(weight,e);
		}
		while ( ! pq.isEmpty()){
			Edge<E> e = pq.removeMin().element();
			Vertex<V>[] endPts = g.endVertices(e);
			ArrayList<Vertex<V>> cluster1 = (ArrayList<Vertex<V>>)endPts[0].get(Attribute.CLUSTER);
			ArrayList<Vertex<V>> cluster2 = (ArrayList<Vertex<V>>)endPts[1].get(Attribute.CLUSTER);
			if (cluster1 != cluster2){
				e.set(Attribute.MSF,null);
//-----------------------------------------------------------------
				e.set(Attribute.color, Color.red);
				t.show(g);
//-----------------------------------------------------------------
				// merge the two clusters
				// make sure that cluster2 is the smaller
				if (cluster1.size() > cluster2.size()){
					for (Vertex<V> v: cluster2){
						cluster1.add(v);
						v.set(Attribute.CLUSTER,cluster1);
//-----------------------------------------------------------------
//						v.set(Attribut.color, Color.red);
						t.show(g);
//-----------------------------------------------------------------
					}
				}
				else{
					for (Vertex<V> v: cluster1){
						cluster2.add(v);
						v.set(Attribute.CLUSTER,cluster2);
//-----------------------------------------------------------------
//						v.set(Attribut.color, Color.red);
						t.show(g);
//-----------------------------------------------------------------
					}
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
        // we can reach 's' to 'g' where 'g' is the gateway
        // of 'u' on the shortest path from 'u' to 's' 
        MyPriorityQueue<Double, Vertex<V>> pq = new MyPriorityQueue<>();
        Iterator<Vertex<V>> it = g.vertices();
        // put all vertices to pq and give them
        // an attribute Attribut.DISTANCE and PQLOCATOR
        while(it.hasNext()){
            Vertex<V> v = it.next();
            v.set(Attribute.DISTANCE,Double.POSITIVE_INFINITY);
            //-------
            v.set(Attribute.string,"inf");
            //-------
            Locator<Double,Vertex<V>> loc = pq.insert(Double.POSITIVE_INFINITY,v);
            v.set(Attribute.PQLOCATOR,loc);
//-----------------------------------------------------------------
            // v.set(Attribut.color, Color.red);
            t.show(g);
//-----------------------------------------------------------------
        }
        // correct the attributes for s
        //-------
        s.set(Attribute.string,"0");
        //-------
        s.set(Attribute.DISTANCE,0.0);
        t.show(g);
        pq.replaceKey((Locator<Double,Vertex<V>>)s.get(Attribute.PQLOCATOR),0.0);
        while( ! pq.isEmpty()){
            Vertex<V> u = pq.removeMin().element();    
            ///-------
            u.set(Attribute.color,Color.RED);
            if (u.has(Attribute.DISCOVERY)){
                Edge<E> e = (Edge<E>)u.get(Attribute.DISCOVERY);
//-----------------------------------------------------------------
                e.set(Attribute.color,Color.RED);
                t.show(g);
//-----------------------------------------------------------------
            }

            // now make the relaxation step for all 
            // neighbours:
            Iterator<Edge<E>> eIt;
            if (g.isDirected()) eIt = g.incidentInEdges(u); // backwards!
            else eIt = g.incidentEdges(u);
            while (eIt.hasNext()){
                Edge<E> e = eIt.next();
                double weight = 1.0; // default weight
//-----------------------------------------------------------------
                //Original version: if (e.has(Attribut.weight)) weight = (Double)e.get(Attribut.weight);
                
                //Whether the graph comes from graphexamples or not it has to be casted differently
                if (e.has(Attribute.weight)) {
                    
                   weight = (Double)e.get(Attribute.weight);
                }
//-----------------------------------------------------------------                
                Vertex<V> z = g.opposite(e, u);
                //Relaxation
                double newDist = (Double) u.get(Attribute.DISTANCE) + weight;
                if (newDist < (Double) z.get(Attribute.DISTANCE)) {
                    z.set(Attribute.DISTANCE,newDist); // new distance of z (can be changed later!)
                    z.set(Attribute.string,""+newDist);
                    z.set(Attribute.DISCOVERY,e);
                    z.set(s, u); // gateway (will eventually be changed later!)
                    pq.replaceKey((Locator<Double,Vertex<V>>) z.get(Attribute.PQLOCATOR), newDist);
                    t.show(g);
                }
            }
        }
        t.show(g);
    }


	/**
	 * vists all vertices starting with 'v' which
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

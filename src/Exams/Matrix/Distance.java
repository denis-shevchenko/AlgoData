package Exams.Matrix;

import examples.MyQueue;

/**
 * Created by denis on 31/01/16.
 */
public class Distance extends MatrixSet{
    static MyQueue<Integer> vertices = new MyQueue<Integer>();
    static int[] dist;
    static boolean[] visited;

    static void BFS(int[][] adj, int vertex){
        int distance = dist[vertex] + 1;

        for (int i = 0; i < adj.length; i++){
            if(adj[i][vertex] == 1 && !visited[i]){
                visited[i] = true;
                vertices.enqueue(i);
                dist[i] = distance;
            }
        }
        if(!vertices.isEmpty())
            BFS(adj, vertices.dequeue());
    }

    public static int distance(int[][] adj, int from, int to){
        dist = new int[adj.length];
        dist[from] = 0;

        visited = new boolean[adj.length];
        visited[from] = true;

        BFS(adj, from);

        if(visited[to])
            return dist[to] - dist[from];

        return -1;
    }
}

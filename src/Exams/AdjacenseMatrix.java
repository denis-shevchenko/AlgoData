package Exams;

import examples.MyQueue;

/**
 * Created by denis on 17/01/16.
 */

class Node{
    int col;
    int row;
    int dist;

    public Node(int _col, int _row, int _dist){
        col = _col;
        row = _row;
        dist = _dist;
    }
}

public class AdjacenseMatrix {

    private static int _count = 0;
    private static boolean[] _marked;
    private static int[] dist;
    private static int[][] discovered;
    private static MyQueue<Node> vertices = new MyQueue<Node>();

    private static void DFS(int[][] adj, int v){
        _marked[v] = true;
        _count++;
        for(int i = 0; i < adj.length; i++){
            if(adj[i][v] == 1 && !_marked[i]){
                System.out.println("adj["+v+"]["+i+"]");
                DFS(adj, i);
            }
        }
    }

    private static int[][] ReverseMatrix(int[][] adj){
        int[][] reverse = new int[adj.length][adj.length];

        for(int i = 0; i < adj.length; i++){
            for (int j = 0; j < adj.length; j++){
                reverse[j][i] = adj[i][j];
            }
        }

        return reverse;
    }

    private static void BFS(int[][] adj, Node vertex, int distance){
        if(discovered[vertex.col][vertex.row] < 1) distance += 1;
        for(int i = 0; i < adj.length; i++){
            if(adj[i][vertex.row] == 1 && discovered[vertex.row][i] < 1){
                if(i == vertex.row) discovered[i][i] = distance;
                discovered[vertex.row][i] = distance;
                vertices.enqueue(new Node(0, i, distance));
            }
        }
        if(!vertices.isEmpty()){
            Node n = vertices.dequeue();
            BFS(adj, n, distance);
        }
    }

    public static int distance(int[][] adj, int from, int to){
        discovered = new int[adj.length][adj.length];
        int distance = 0;

        vertices.enqueue(new Node(0, 0, distance));

        while (!vertices.isEmpty()){
            Node n = vertices.dequeue();
            BFS(adj, n, distance);
        }

        return discovered[to][to] - discovered[from][from];
    }

    public static boolean IsConnected(int[][] adj){
        _marked = new boolean[adj.length];
        DFS(adj, 0);

        return adj.length == _count;
    }

    public static boolean IsStronglyConnected(int[][] adj){
        _marked = new boolean[adj.length];

        DFS(adj, 0);
        if(_count != adj.length) return false;

        _count = 0;
        for (int i = 0; i < adj.length; i++){
            _marked[i] = false;
        }
        int[][] reverse = ReverseMatrix(adj);

        DFS(reverse, 0);
        if(_count != adj.length) return false;

        return true;
    }

    public static int[][] distMat(int[][]rts){
        int[][] dist = new int[rts.length][rts.length];

        distDFS(rts, dist, 0);

        return dist;
    }

    private static void distDFS(int[][] rts, int[][]dist, int vertex){
        for (int i = 0; i < rts.length; i++){
            if(rts[i][vertex] > 0){
                if(vertex == i)
                    dist[vertex][i] = 0;
                else if(rts[i][vertex] == i) {
                    // direct connected
                    dist[vertex][i] = 2 ;
                    dist[i][vertex] = 2 ;
                }
                else{
                    // indirect connected
                    dist[vertex][i] += 1;
                    dist[i][vertex] += 1;
                    distDFS(rts, dist, i);
                }
            }
        }
    }

//    public enum Types{
//        discovered,
//        marked
//    }

    public static boolean isTree(int[][] adj) {
//        Types[] marked = new Types[adj.length];
        boolean[] marked = new boolean[adj.length];
        if (IsConnected(adj)){
            for (int i = 0; i < adj.length; i++) {
                for (int j = 0; j < adj.length; j++) {
                    if (i == j && adj[i][j] == 1)
                        return false;

                    if (adj[i][j] == 1 && adj[j][i] == 1) {
                        if (!marked[j])
                            marked[j] = true;
                    }
                }
            }
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        int[][] adj = new int[6][6];

        //Directed graph

//        adj[0][1] = 0;
//        adj[0][2] = 1;
//        adj[0][3] = 0;
//        adj[0][4] = 0;
//        adj[0][5] = 0;
//
//        adj[1][0] = 1;
//        adj[1][2] = 1;
//        adj[1][3] = 0;
//        adj[1][4] = 0;
//        adj[1][5] = 1;
//
//        adj[2][0] = 0;
//        adj[2][1] = 0;
//        adj[2][3] = 0;
//        adj[2][4] = 0;
//        adj[2][5] = 1;
//
//        adj[3][0] = 1;
//        adj[3][1] = 1;
//        adj[3][2] = 0;
//        adj[3][4] = 0;
//        adj[3][5] = 0;
//
//        adj[4][0] = 0;
//        adj[4][1] = 0;
//        adj[4][2] = 0;
//        adj[4][3] = 1;
//        adj[4][5] = 0;
//
//        adj[5][0] = 0;
//        adj[5][1] = 0;
//        adj[5][2] = 0;
//        adj[5][3] = 0;
//        adj[5][4] = 1;
//
//        IsStronglyConnected(adj);

/*
    Undirected graph
*/
/*
        adj[0][1] = 1;
        adj[0][2] = 1;
        adj[0][3] = 1;
        adj[0][4] = 0;
        adj[0][5] = 0;

        adj[1][0] = 1;
        adj[1][2] = 1;
        adj[1][3] = 0;
        adj[1][4] = 0;
        adj[1][5] = 0;

        adj[2][0] = 1;
        adj[2][1] = 1;
        adj[2][3] = 1;
        adj[2][4] = 1;
        adj[2][5] = 0;

        adj[3][0] = 1;
        adj[3][1] = 0;
        adj[3][2] = 1;
        adj[3][4] = 0;
        adj[3][5] = 0;

        adj[4][0] = 0;
        adj[4][1] = 0;
        adj[4][2] = 1;
        adj[4][3] = 0;
        adj[4][5] = 1;

        adj[5][0] = 0;
        adj[5][1] = 0;
        adj[5][2] = 0;
        adj[5][3] = 0;
        adj[5][4] = 1;
*/
//        System.out.println(IsConnected(adj));
//        System.out.println(distance(adj, 1, 5));

        /*
    Undirected graph
*/

        int[][] rts = new int[adj.length][adj.length];

        rts[0][0] = 0;
        rts[0][1] = 1;
        rts[0][2] = 2;
        rts[0][3] = 1;
        rts[0][4] = 2;
        rts[0][5] = 1;

        rts[1][0] = 0;
        rts[1][2] = 2;
        rts[1][3] = 0;
        rts[1][4] = 0;
        rts[1][5] = 0;

        rts[2][0] = 1;
        rts[2][1] = 1;
        rts[2][3] = 1;
        rts[2][4] = 1;
        rts[2][5] = 0;

        rts[3][0] = 1;
        rts[3][1] = 0;
        rts[3][2] = 1;
        rts[3][4] = 0;
        rts[3][5] = 0;

        rts[4][0] = 0;
        rts[4][1] = 0;
        rts[4][2] = 1;
        rts[4][3] = 0;
        rts[4][5] = 1;

        rts[5][0] = 0;
        rts[5][1] = 0;
        rts[5][2] = 0;
        rts[5][3] = 0;
        rts[5][4] = 1;

//        System.out.println(distMat(rts));

/*
    Undirected graph
*/
//        adj[0][1] = 1;
//        adj[0][2] = 1;
//        adj[0][3] = 0;
//        adj[0][4] = 0;
//        adj[0][5] = 0;
//
//        adj[1][0] = 1;
//        adj[1][2] = 1;
//        adj[1][3] = 0;
//        adj[1][4] = 1;
//        adj[1][5] = 0;
//
//        adj[2][0] = 1;
//        adj[2][1] = 1;
//        adj[2][3] = 0;
//        adj[2][4] = 0;
//        adj[2][5] = 1;
//
//        adj[3][0] = 0;
//        adj[3][1] = 0;
//        adj[3][2] = 0;
//        adj[3][4] = 1;
//        adj[3][5] = 1;
//
//        adj[4][0] = 0;
//        adj[4][1] = 1;
//        adj[4][2] = 0;
//        adj[4][3] = 1;
//        adj[4][5] = 0;
//
//        adj[5][0] = 0;
//        adj[5][1] = 0;
//        adj[5][2] = 1;
//        adj[5][3] = 1;
//        adj[5][4] = 0;
//
//        System.out.println(isTree(adj));

        adj[0][1] = 1;
        adj[0][2] = 0;
        adj[0][3] = 0;
        adj[0][4] = 0;
        adj[0][5] = 0;

        adj[1][0] = 1;
        adj[1][2] = 1;
        adj[1][3] = 1;
        adj[1][4] = 1;
        adj[1][5] = 0;

        adj[2][0] = 0;
        adj[2][1] = 1;
        adj[2][3] = 0;
        adj[2][4] = 0;
        adj[2][5] = 0;

        adj[3][0] = 0;
        adj[3][1] = 1;
        adj[3][2] = 0;
        adj[3][4] = 0;
        adj[3][5] = 1;

        adj[4][0] = 0;
        adj[4][1] = 1;
        adj[4][2] = 0;
        adj[4][3] = 0;
        adj[4][5] = 0;

        adj[5][0] = 0;
        adj[5][1] = 0;
        adj[5][2] = 0;
        adj[5][3] = 1;
        adj[5][4] = 0;

        System.out.println(isTree(adj));
    }
}

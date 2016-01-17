package Exams;

/**
 * Created by denis on 17/01/16.
 */

public class AdjacenseMatrix {

    private static int _count = 0;
    private static boolean[] _marked;

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

    public static void main(String[] args) {
        int[][] adj = new int[6][6];

        //Directed graph

        adj[0][1] = 0;
        adj[0][2] = 1;
        adj[0][3] = 0;
        adj[0][4] = 0;
        adj[0][5] = 0;

        adj[1][0] = 1;
        adj[1][2] = 1;
        adj[1][3] = 0;
        adj[1][4] = 0;
        adj[1][5] = 1;

        adj[2][0] = 0;
        adj[2][1] = 0;
        adj[2][3] = 0;
        adj[2][4] = 0;
        adj[2][5] = 1;

        adj[3][0] = 1;
        adj[3][1] = 1;
        adj[3][2] = 0;
        adj[3][4] = 0;
        adj[3][5] = 0;

        adj[4][0] = 0;
        adj[4][1] = 0;
        adj[4][2] = 0;
        adj[4][3] = 1;
        adj[4][5] = 0;

        adj[5][0] = 0;
        adj[5][1] = 0;
        adj[5][2] = 0;
        adj[5][3] = 0;
        adj[5][4] = 1;

        IsStronglyConnected(adj);

    //Undirected graph


//        adj[0][1] = 1;
//        adj[0][2] = 1;
//        adj[0][3] = 1;
//        adj[0][4] = 0;
//        adj[0][5] = 0;
//
//        adj[1][0] = 1;
//        adj[1][2] = 1;
//        adj[1][3] = 0;
//        adj[1][4] = 0;
//        adj[1][5] = 0;
//
//        adj[2][0] = 1;
//        adj[2][1] = 1;
//        adj[2][3] = 1;
//        adj[2][4] = 1;
//        adj[2][5] = 0;
//
//        adj[3][0] = 1;
//        adj[3][1] = 0;
//        adj[3][2] = 1;
//        adj[3][4] = 0;
//        adj[3][5] = 0;
//
//        adj[4][0] = 0;
//        adj[4][1] = 0;
//        adj[4][2] = 1;
//        adj[4][3] = 0;
//        adj[4][5] = 1;
//
//        adj[5][0] = 0;
//        adj[5][1] = 0;
//        adj[5][2] = 0;
//        adj[5][3] = 0;
//        adj[5][4] = 1;
//
//        System.out.println(IsConnected(adj));
    }
}

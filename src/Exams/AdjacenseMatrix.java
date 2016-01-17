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

    public static boolean IsConnected(int[][] adj){
        _marked = new boolean[adj.length];
        DFS(adj, 0);

        return adj.length == _count;
    }

    public static boolean IsStronglyConnected(int[][] adj){
        return true;
    }

    public static void main(String[] args) {
        int[][] adj = new int[6][6];

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

        System.out.println(IsConnected(adj));
    }
}

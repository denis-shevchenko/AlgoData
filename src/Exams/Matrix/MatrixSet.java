package Exams.Matrix;

/**
 * Created by denis on 31/01/16.
 */
public class MatrixSet {
    private static int mConnected[][] = {
            {0,1,0,0,0,0},
            {1,0,1,1,1,0},
            {0,1,0,0,0,0},
            {0,1,0,0,0,1},
            {0,1,0,0,0,0},
            {0,0,0,1,0,0}
    };

    private static int mDisconnected[][] = {
            {0,1,0,0,0,0},
            {1,0,1,0,1,0},
            {0,1,0,0,0,0},
            {0,0,0,0,0,1},
            {0,1,0,0,0,0},
            {0,0,0,1,0,0}
    };

    enum MatricesTypes{
        CONNECTED,
        DISCONNECTED
    }

    static void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                System.out.print(" | " + matrix[i][j]);
            }
            System.out.println("|");
        }
    }

    public int[][] getMatrix(MatricesTypes t){

        switch (t) {
            case CONNECTED:
                return mConnected;
            case DISCONNECTED:
                return mDisconnected;
            default:
                return null;
        }
    }
}

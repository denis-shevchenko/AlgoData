package Exams.Matrix;

/**
 * Created by denis on 31/01/16.
 */
public class Test{
    public static void main(String[] args) {
        MatrixSet distanceMatrix = new MatrixSet();

        int[][] adjConnected = distanceMatrix.getMatrix(MatrixSet.MatricesTypes.CONNECTED);
        MatrixSet.printMatrix(adjConnected);

        System.out.println(Distance.distance(adjConnected, 0, 5));

        int[][] adjDisconnected = distanceMatrix.getMatrix(MatrixSet.MatricesTypes.DISCONNECTED);
        MatrixSet.printMatrix(adjDisconnected);

        System.out.println(Distance.distance(adjDisconnected, 5, 3));
    }
}

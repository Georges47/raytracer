package org.dimi;

public class MathProcessor {
    public Vector3 matrixVectorMultiplication(float[][] matrix, Vector3 vector) {
        double[] v = new double[]{vector.getX(), vector.getY(), vector.getZ()};
        double[] result = new double[]{0,0,0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i] += v[j] * matrix[i][j];
            }
        }
        return new Vector3(result[0], result[1], result[2]);
    }
}

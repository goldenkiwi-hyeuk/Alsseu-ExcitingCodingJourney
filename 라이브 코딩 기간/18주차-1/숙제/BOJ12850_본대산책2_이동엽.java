package boj;

import java.util.Scanner;

public class Main12850 {

    static final int mod = 1_000_000_007;
    static long[][] map = {
            {0, 1, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long d = sc.nextLong();
        long[][] matrix = dAndC(d);

        System.out.println(matrix[0][0]);
    }

    private static long[][] dAndC(long d) {
        long[][] mat = new long[8][8];

        for (int i = 0; i < 8; i++) {
            mat[i][i] = 1;
        }

        long[][] temp = map;

        while (d > 0) {
            if (d % 2 == 1) {
                mat = multiMatrix(mat, temp);
            }

            temp = multiMatrix(temp, temp);

            d /= 2;
        }

        return mat;
    }

    private static long[][] multiMatrix(long[][] a, long[][] b) {
        long[][] mat = new long[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    mat[i][j] = (mat[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }

        return mat;
    }
}
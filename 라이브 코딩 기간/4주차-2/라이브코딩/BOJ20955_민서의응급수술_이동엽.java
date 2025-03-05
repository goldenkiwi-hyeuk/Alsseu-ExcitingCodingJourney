package boj;

import java.util.Scanner;

public class Main17779 {
    static int n, a[][], ans = Integer.MAX_VALUE, sum = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = sc.nextInt();
                sum += a[i][j];
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int d1 = 1; d1 < n; d1++) {
                    for (int d2 = 1; d2 < n; d2++) {
                        if (x + d1 + d2 >= n || y - d1 < 0 || y + d2 >= n) continue;
                        ans = Math.min(ans, gerrymandering(x, y, d1, d2));
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static int gerrymandering(int x, int y, int d1, int d2) {
        boolean[][] boundary = new boolean[n][n];
        int[] pop = new int[6];

        for (int i = 0; i <= d1; i++) {
            boundary[x + i][y - i] = true;
            boundary[x + d2 + i][y + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            boundary[x + i][y + i] = true;
            boundary[x + d1 + i][y - d1 + i] = true;
        }

        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (boundary[i][j]) break;
                pop[1] += a[i][j];
            }
        }

        for (int i = 0; i <= x + d2; i++) {
            for (int j = n - 1; j > y; j--) {
                if (boundary[i][j]) break;
                pop[2] += a[i][j];
            }
        }

        for (int i = x + d1; i < n; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (boundary[i][j]) break;
                pop[3] += a[i][j];
            }
        }

        for (int i = x + d2 + 1; i < n; i++) {
            for (int j = n - 1; j >= y - d1 + d2; j--) {
                if (boundary[i][j]) break;
                pop[4] += a[i][j];
            }
        }

        pop[5] = sum - (pop[1] + pop[2] + pop[3] + pop[4]);

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            max = Math.max(max, pop[i]);
            min = Math.min(min, pop[i]);
        }
        return max - min;
    }
}

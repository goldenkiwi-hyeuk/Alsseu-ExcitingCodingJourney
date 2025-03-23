package boj;

import java.util.Scanner;

public class Main1799 {

    static int n, chess[][], maxBlack = 0, maxWhite = 0;
    static boolean[] left, right;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        chess = new int[n][n];

        left = new boolean[2 * n];
        right = new boolean[2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chess[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0, 0, true);
        dfs(0, 1, 0, false);

        System.out.println(maxBlack + maxWhite);
    }

    private static void dfs(int x, int y, int cnt, boolean isBlack) {
        if (y >= n) {
            x++;
            if ((y % 2 == 0 && isBlack) || (y % 2 == 1 && !isBlack)) {
                y = 1;
            } else {
                y = 0;
            }
        }

        if (x >= n) {
            if (isBlack) {
                maxBlack = Math.max(maxBlack, cnt);
            } else {
                maxWhite = Math.max(maxWhite, cnt);
            }
            return;
        }

        if (chess[x][y] == 1 && !left[x - y + n - 1] && !right[x + y]) {
            left[x - y + n - 1] = true;
            right[x + y] = true;

            dfs(x, y + 2, cnt + 1, isBlack);

            left[x - y + n - 1] = false;
            right[x + y] = false;
        }

        dfs(x, y + 2, cnt, isBlack);
    }
}

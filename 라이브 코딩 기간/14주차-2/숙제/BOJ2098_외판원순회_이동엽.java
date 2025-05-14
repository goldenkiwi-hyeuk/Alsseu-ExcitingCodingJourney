package boj;

import java.util.Arrays;
import java.util.Scanner;

// 참고: https://withhamit.tistory.com/246

public class Main2098 {
    static int n, w[][], dp[][];
    static final int INF = 16_000_000;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        w = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = sc.nextInt();
            }
        }
        sc.close();

        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        System.out.println(dfs(0, 1));
    }

    static int dfs(int now, int v) {
        if (v == (1 << n) - 1) {
            if (w[now][0] == 0) return INF;
            return w[now][0];
        }

        if (dp[now][v] != -1) return dp[now][v];
        dp[now][v] = INF;

        for (int i = 0; i < n; i++) {
            if ((v & (1 << i)) == 0 && w[now][i] != 0) {
                dp[now][v] = Math.min(dfs(i, v | (1 << i)) + w[now][i], dp[now][v]);
            }
        }
        return dp[now][v];
    }
}


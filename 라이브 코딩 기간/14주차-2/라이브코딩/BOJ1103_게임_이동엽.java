package boj;

import java.util.*;

class Main1103 {

    static int n, m, dp[][], max;
    static char[][] board;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static boolean visited[][], inf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        board = new char[n][m];
        visited = new boolean[n][m];
        dp = new int[n][m];
        inf = false;

        for (int i = 0; i < n; i++) {
            board[i] = sc.next().toCharArray();
        }
        sc.close();

        max = dfs(0, 0);

        if (inf) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }

    }

    private static int dfs(int x, int y) {
        if (!isIn(x, y) || board[x][y] == 'H') {
            return 0;
        }

        if (visited[x][y]) {
            inf = true;
            return 0;
        }

        if(dp[x][y] != 0) {
            return dp[x][y];
        }

        visited[x][y] = true;
        int multi = board[x][y] - '0';
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d] * multi;
            int ny = y + dy[d] * multi;
            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
        }

        visited[x][y] = false;
        return dp[x][y];
    }


    private static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

}
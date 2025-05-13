import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();

    static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M, maxMove;
    static boolean hasCycle;
    static int[][] board;
    static int[][] dp;
    static boolean[][] visited;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        board = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = scan.nextLine();
            for (int j = 0; j < M; j++)
                if (line.charAt(j) == 'H') {
                    board[i][j] = -1;
                } else {
                    board[i][j] = line.charAt(j) - '0';
                }
        }
    }

    static void output() {
        System.out.println(maxMove);
    }

    static void solve() {
        hasCycle = false;
        maxMove = dfs(0, 0);
        if (hasCycle) maxMove = -1;
    }

    static int dfs(int r, int c) {
        if (r < 0 || N <= r || c < 0 || M <= c || board[r][c] == -1) return 0;
        if (hasCycle) return 0;
        if (dp[r][c] != 0) return dp[r][c];

        if (visited[r][c]) {
            hasCycle = true;
            return 0;
        }

        visited[r][c] = true;
        int steps = board[r][c];
        int maxDistance = 0;

        for (int[] dir : DIRS) {
            int nr = r + dir[0] * steps;
            int nc = c + dir[1] * steps;
            maxDistance = Math.max(maxDistance, dfs(nr, nc) + 1);
        }

        visited[r][c] = false;
        dp[r][c] = maxDistance;

        return dp[r][c];
    }

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
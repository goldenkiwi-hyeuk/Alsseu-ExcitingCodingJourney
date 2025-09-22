import java.util.*;
import java.io.*;

class Main {

    static FastScanner fs = new FastScanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static final int INF = 1_000_000;
    static int T, N, W;
    static int[][] wontagon, dp;

    public static void main(String[] args) {
        T = fs.nextInt();
        for (int i = 1; i <= T; i++) {
            input();
            solve();
        }
        output();
    }

    static void input() {
        N = fs.nextInt();
        W = fs.nextInt();
        wontagon = new int[2][N];
        for (int i = 0; i < N; i++) wontagon[0][i] = fs.nextInt();
        for (int i = 0; i < N; i++) wontagon[1][i] = fs.nextInt();
    }

    static void solve() {
        if (N == 1) {
            sb.append((wontagon[0][0] + wontagon[1][0] <= W) ? 1 : 2).append('\n');
            return;
        }
        int ans = INF;
        // dp의 행은 열까지의 상태를 의미
        // 0행 : i번째 열까지 덮으면서 위쪽 i번째 칸만 남은 상태
        // 1행 : i번째 열까지 덮으면서 아래쪽 i번째 칸만 남은 상태
        // 2행 : i번까지 열을 모두 덮은 상태
        dp = new int[3][N + 1];
        // 양쪽 끝 연결 X
        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[2][0] = 0;
        runDp(0);
        ans = Math.min(ans, dp[2][N]);
        // 위쪽 끝 연결
        if (wontagon[0][0] + wontagon[0][N - 1] <= W) {
            dp[0][1] = 2;
            dp[1][1] = (wontagon[1][0] + wontagon[1][1]) <= W ? 1 : 2;
            dp[2][1] = 1;
            runDp(1);
            ans = Math.min(ans, dp[1][N - 1] + 1);
        }
        // 아랫쪽 끝 연결
        if (wontagon[1][0] + wontagon[1][N - 1] <= W) {
            dp[0][1] = (wontagon[0][0] + wontagon[0][1]) <= W ? 1 : 2;
            dp[1][1] = 2;
            dp[2][1] = 1;
            runDp(1);
            ans = Math.min(ans, dp[0][N - 1] + 1);
        }
        // 양쪽 끝 연결
        if (wontagon[0][0] + wontagon[0][N - 1] <= W && wontagon[1][0] + wontagon[1][N - 1] <= W) {
            dp[0][1] = 1;
            dp[1][1] = 1;
            dp[2][1] = 0;
            runDp(1);
            ans = Math.min(ans, dp[2][N - 1] + 2);
        }

        sb.append(ans).append('\n');
    }

    static void runDp(int start) {
        for (int i = start; i < N; i++) {
            // 1) 해당 열 모두 채움 dp[2][i]
            // 1-1)위만 채운 경우 + 1, 아래만 채운 경우 + 1 비교
            dp[2][i + 1] = Math.min(dp[0][i] + 1, dp[1][i] + 1);
            // 1-2) 해당 열 위아래 같이 채움
            if (wontagon[0][i] + wontagon[1][i] <= W) {
                dp[2][i + 1] = Math.min(dp[2][i + 1], dp[2][i] + 1);
            }
            // 1-3) 위 아래 모두 이전 열과 묶어서 채움
            if (i > 0 && wontagon[0][i - 1] + wontagon[0][i] <= W && wontagon[1][i - 1] + wontagon[1][i] <= W) {
                dp[2][i + 1] = Math.min(dp[2][i + 1], dp[2][i - 1] + 2);
            }
            // 2) 해당 열 위 또는 아래만 채움
            if (i < N - 1) {
                // 2-1) 해당 열 위 또는 아래를 하나 채움
                dp[0][i + 1] = dp[2][i + 1] + 1;
                dp[1][i + 1] = dp[2][i + 1] + 1;
                // 2-2) 해당 열 위를 이전 열과 연결하여 채움
                if (wontagon[0][i] + wontagon[0][i + 1] <= W) {
                    dp[0][i + 1] = Math.min(dp[0][i + 1], dp[1][i] + 1);
                }
                // 2-3) 해당 열 아래를 이전 열과 연결하여 채움
                if (wontagon[1][i] + wontagon[1][i + 1] <= W) {
                    dp[1][i + 1] = Math.min(dp[1][i + 1], dp[0][i] + 1);
                }
            }
        }
    }

    static void output() {
        System.out.println(sb.toString());
    }

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int idx = 0, size = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() {
            if (idx >= size) {
                try {
                    size = in.read(buffer);
                    idx = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return size == -1 ? -1 : buffer[idx++];
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            int c = 0;
            while ((c = read()) != -1 && c <= ' ');
            do { sb.append((char) c); }
            while ((c = read()) != -1 && c > ' ');
            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
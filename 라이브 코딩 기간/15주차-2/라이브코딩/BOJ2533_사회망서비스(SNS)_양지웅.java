import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();

    static int N;
    static List<List<Integer>> tree = new ArrayList<>();
    static int[][] dp;
    static boolean[] visited;

    static void input() {
        N = scan.nextInt();
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            tree.get(x).add(y);
            tree.get(y).add(x);
        }
    }

    static void output() {
        System.out.print(Math.min(dp[1][0], dp[1][1]));
    }

    static void solve() {
        dfs(1);
    }

    static void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0;
        dp[node][1] = 1;

        for (int child : tree.get(node)) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1];
                dp[node][1] += Math.min(dp[child][0], dp[child][1]);
            }
        }
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
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
import java.util.*;
import java.io.*;

class Main {

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

            while((c = read()) != -1 && c <= ' ');
            do {
                sb.append((char) c);
            } while ((c = read()) != -1 && c > ' ');

            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static class DSU {
        int[] parent, size;

        DSU(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        void union(int a, int b) {
            int pA = find(a);
            int pB = find(b);
            if (pA == pB) return;
            if (size[pA] < size[pB]) {
                int temp = pA;
                pA = pB;
                pB = temp;
            }
            parent[pB] = pA;
            size[pA] += size[pB];
        }
    }

    static FastScanner scan = new FastScanner(System.in);
    static int N, M, K, ans;
    static int[] candy;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();
        candy = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            candy[i] = scan.nextInt();
        }
    }

    static void solve() {
        DSU dsu = new DSU(N + 1);

        for (int i = 1; i <= M; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();
            dsu.union(a, b);
        }

        int[] compSize = new int[N + 1];
        int[] compValue = new int[N + 1];
        for (int x = 1; x <= N; x++) {
            int p = dsu.find(x);
            compSize[p] += 1;
            compValue[p] += candy[x];
        }

        knapsack(compSize, compValue);
    }

    static void knapsack(int[] compSize, int[] compValue) {
        int[] dp = new int[K];

        for (int i = 1; i <= N; i++) {
            int w = compSize[i];
            if (w == 0 || w >= K) continue;
            int v = compValue[i];
            for (int c = K - 1; c >= w; c--) {
                dp[c] = Math.max(dp[c], dp[c - w] + v);
            }
        }

        ans = 0;
        for (int c = 0; c <= K - 1; c++) {
            ans = Math.max(ans, dp[c]);
        }
    }

    static void output() {
        System.out.println(ans);
    }
}
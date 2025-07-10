import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader(System.in);
    static int C, N, M, ans;
    static int[] A, B;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    private static void input() {
        C = scan.nextInt();
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N];
        B = new int[M];
        for (int i = 0; i < N; i++) A[i] = scan.nextInt();
        for (int i = 0; i < M; i++) B[i] = scan.nextInt();
    }

    private static void solve() {
        ans = 0;
        int[] cntA = new int[C + 1];
        int[] cntB = new int[C + 1];
        fillCounts(A, cntA, C);
        fillCounts(B, cntB, C);

        ans = cntA[C] + cntB[C];
        for (int s = 1; s < C; s++)
            ans += cntA[s] * cntB[C - s];
    }

    private static void fillCounts(int[] arr, int[] cnt, int limit) {
        int n = arr.length;

        int total = 0;
        for (int v : arr) total += v;
        if (total <= limit) cnt[total]++;

        int[] ps = new int[2 * n + 1];
        for (int i = 1; i <= 2 * n; i++)
            ps[i] = ps[i - 1] + arr[(i - 1) % n];

        for (int len = 1; len < n; len++) {
            for (int start = 0; start < n; start++) {
                int sum = ps[start + len] - ps[start];
                if (sum <= limit) cnt[sum]++;
            }
        }
    }

    private static void output() {
        System.out.println(ans);
    }

    static class FastReader {
        private final byte[] buffer = new byte[1 << 16];
        private int size = 0, idx = 0;
        private final InputStream in;

        FastReader(InputStream in) {
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

            return (size == -1) ? -1 : buffer[idx++];
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
}
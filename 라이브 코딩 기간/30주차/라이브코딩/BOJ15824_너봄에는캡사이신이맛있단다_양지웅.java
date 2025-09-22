import java.util.*;
import java.io.*;

class Main {
    static FastScanner scan = new FastScanner();
    static final int MOD = 1_000_000_007;
    static int N;
    static int[] menu;

    public static void main(String[] args) {
        input();
        solve();
    }

    static void input() {
        N = scan.nextInt();
        menu = new int[N];
        for (int i = 0; i < N; i++) {
            menu[i] = scan.nextInt();
        }
    }

    static void solve() {
        Arrays.sort(menu);

        long[] pow2 = new long[N];
        pow2[0] = 1;
        for (int i = 1; i < N; i++) pow2[i] = (pow2[i - 1] * 2) % MOD;

        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += (pow2[i] - pow2[N - 1 - i]) * menu[i];
            ans %= MOD;
        }

        System.out.println(ans);
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() {
            while (st == null || !st.hasMoreElements()) {
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
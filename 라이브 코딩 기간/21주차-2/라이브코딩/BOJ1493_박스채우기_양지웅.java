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
            } while((c = read()) != -1 && c > ' ');

            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static FastScanner scan = new FastScanner(System.in);
    static int L, W, H, N, ans;
    static int[] cubes;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        L = scan.nextInt();
        W = scan.nextInt();
        H = scan.nextInt();
        N = scan.nextInt();

        cubes = new int[20];
        for (int i = 0; i < N; i++) {
            cubes[scan.nextInt()] = scan.nextInt();
        }
    }

    static void solve() {
        ans = 0;

        long filled = 0;
        for (int i = 19; i >= 0 ; i--) {
            int size = 1 << i;
            long max = (long)(L / size) * (W / size) * (H / size);
            max -= filled;

            long use = Math.min(max, cubes[i]);
            filled += use;
            ans += use;
            filled *= 8;
        }
        filled /= 8;

        long total = (long) L * W * H;
        if (filled != total) ans = -1;
    }

    static void output() {
        System.out.println(ans);
    }
}
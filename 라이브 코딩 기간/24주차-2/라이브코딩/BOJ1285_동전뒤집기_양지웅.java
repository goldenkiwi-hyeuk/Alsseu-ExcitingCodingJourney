import java.util.*;
import java.io.*;

class Main {

    static FastReader fr = new FastReader(System.in);
    static int N, ans;
    static int[] rows;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    private static void input() {
        N = fr.nextInt();
        rows = new int[N];
        for (int i = 0; i < N; i++) {
            String line = fr.next();
            int row = 0;
            for (int j = 0; j < N; j++) {
                if (line.charAt(j) == 'T') row |= 1 << j;
            }
            rows[i] = row;
        }
    }

    private static void solve() {
        ans = N * N;

        for (int colType = 0; colType < 1 << N; colType++) {
            int tailsCnt = 0;
            for (int row : rows) {
                int rowTailCnt = Integer.bitCount(row ^ colType);
                tailsCnt += Math.min(rowTailCnt, N - rowTailCnt);
            }
            ans = Math.min(ans, tailsCnt);
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
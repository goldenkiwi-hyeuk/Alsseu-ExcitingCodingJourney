import java.util.*;
import java.io.*;

class Main {

    static FastScanner fs = new FastScanner(System.in);
    static int N, S;
    static int[] arr;
    static long ans;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    private static void input() {
        N = fs.nextInt();
        S = fs.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = fs.nextInt();
        }
    }

    private static void solve() {
        ans = 0L;

        int mid = N / 2;
        int L = mid, R = N - mid;

        int[] leftSums = new int[1 << L];
        int[] rightSums = new int[1 << R];

        for (int mask = 0; mask < (1 << L); mask++) {
            int sum = 0;
            for (int i = 0; i < L; i++) {
                if ((mask & (1 << i)) != 0) sum += arr[i];
            }
            leftSums[mask] = sum;
        }

        for (int mask = 0; mask < (1 << R); mask++) {
            int sum = 0;
            for (int i = 0; i < R; i++) {
                if ((mask & (1 << i)) != 0) sum += arr[mid + i];
            }
            rightSums[mask] = sum;
        }

        Arrays.sort(leftSums);
        Arrays.sort(rightSums);

        int i = 0;
        int j = rightSums.length - 1;

        while (i < leftSums.length && j >= 0) {
            long sum = (long) leftSums[i] + (long) rightSums[j];
            if (sum == S) {
                int lv = leftSums[i];
                int rv = rightSums[j];
                long cntL = 0, cntR = 0;
                while (i < leftSums.length && leftSums[i] == lv) { cntL++; i++; }
                while (j >= 0 && rightSums[j] == rv) { cntR++; j--; }
                ans += cntL * cntR;
            } else if (sum < S) {
                i++;
            } else {
                j--;
            }
        }

        if (S == 0) ans--;

    }

    private static void output() {
        System.out.println(ans);
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
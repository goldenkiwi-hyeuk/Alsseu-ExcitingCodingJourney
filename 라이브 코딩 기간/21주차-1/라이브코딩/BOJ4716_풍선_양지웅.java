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

    static class Balloon {
        int k, da, db, diff;

        Balloon(int k, int da, int db) {
            this.k = k;
            this.da = da;
            this.db = db;
            this.diff = da - db;
        }
    }


    static FastScanner scan = new FastScanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int n, a, b;
    static List<Balloon> ballons;

    public static void main(String[] args) {
        while (true) {
            input();
            if (n == 0 && a == 0 && b == 0) break;
            solve();
        }
        output();
    }

    static void input() {
        n = scan.nextInt();
        a = scan.nextInt();
        b = scan.nextInt();
    }

    static void solve() {
        List<Balloon> balloons = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int k = scan.nextInt();
            int da = scan.nextInt();
            int db = scan.nextInt();
            balloons.add(new Balloon(k, da, db));
        }

        balloons.sort((x, y) -> Math.abs(y.diff) - Math.abs(x.diff));

        long totalCost = 0;

        for (Balloon balloon : balloons) {
            if (balloon.diff < 0) {
                int fromA = Math.min(a, balloon.k);
                a -= fromA;
                int fromB = balloon.k - fromA;
                b -= fromB;

                totalCost += (long) fromA * balloon.da + (long) fromB * balloon.db;
            } else {
                int fromB = Math.min(b, balloon.k);
                b -= fromB;
                int fromA = balloon.k - fromB;
                a -= fromA;

                totalCost += (long) fromA * balloon.da + (long) fromB * balloon.db;
            }
        }

        sb.append(totalCost).append("\n");
    }

    static void output() {
        System.out.println(sb.toString());
    }
}
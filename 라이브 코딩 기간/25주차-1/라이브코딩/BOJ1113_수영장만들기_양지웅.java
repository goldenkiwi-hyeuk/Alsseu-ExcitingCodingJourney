import java.util.*;
import java.io.*;

class Main {

    static class Cell {
        int r, c, h;
        Cell (int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }

    static FastScanner fs = new FastScanner(System.in);
    static int N, M, ans;
    static int[][] G;
    static int[][] DIRS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    private static void input() {
        N = fs.nextInt();
        M = fs.nextInt();
        G = new int[N][M];

        for (int r = 0; r < N; r++) {
            String line = fs.next();
            for (int c = 0; c < M; c++) {
                G[r][c] = line.charAt(c) - '0';
            }
        }
    }

    private static void solve() {
        boolean[][] checked = new boolean[N][M];
        PriorityQueue<Cell> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.h));
        for (int r = 0; r < N; r++) {
            pq.offer(new Cell(r, 0, G[r][0]));
            pq.offer(new Cell(r, M - 1, G[r][M - 1]));
            checked[r][0] = true;
            checked[r][M - 1] = true;
        }
        for (int c = 0; c < M; c++) {
            pq.offer(new Cell(0, c, G[0][c]));
            pq.offer(new Cell(N - 1, c, G[N - 1][c]));
            checked[0][c] = true;
            checked[N - 1][c] = true;
        }

        int maxHeight = 0;
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            maxHeight = Math.max(cur.h, maxHeight);

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DIRS[d][0];
                int nc = cur.c + DIRS[d][1];
                if (nr < 0 || N <= nr || nc < 0 || M <= nc) continue;
                if (checked[nr][nc]) continue;

                checked[nr][nc] = true;
                int nh = G[nr][nc];
                if (nh < maxHeight) {
                    ans += maxHeight - nh;
                    pq.offer(new Cell(nr, nc, maxHeight));
                } else {
                    pq.offer(new Cell(nr, nc, nh));
                }
            }
        }
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
}
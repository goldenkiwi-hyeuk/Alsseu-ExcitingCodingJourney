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

            while ((c = read()) != -1 && c <= ' ');
            do {
                sb.append((char) c);
            } while ((c = read()) != -1 && c > ' ');

            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static class Passenger {
        int sr, sc, dr, dc;
        public Passenger(int sr, int sc, int dr, int dc) {
            this.sr = sr;
            this.sc = sc;
            this.dr = dr;
            this.dc = dc;
        }
    }

    static class State implements Comparable<State> {
        int r, c, dist, idx;

        public State(int r, int c, int dist, int idx) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.idx = idx;
        }

        @Override
        public int compareTo(State o) {
            if (this.dist != o.dist) return this.dist - o.dist;
            if (this.r != o.r) return this.r - o.r;
            return this.c - o.c;
        }
    }

    static FastScanner scan = new FastScanner(System.in);
    static int N, M, fuel, taxiR, taxiC;
    static int[][] map;
    static boolean[][] visited;
    static List<Passenger> passengers = new ArrayList<>();
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        fuel = scan.nextInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = scan.nextInt();
            }
        }

        taxiR = scan.nextInt() - 1;
        taxiC = scan.nextInt() - 1;
        for (int i = 0; i < M; i++) {
            int sr = scan.nextInt() - 1, sc_ = scan.nextInt() - 1;
            int dr = scan.nextInt() - 1, dc = scan.nextInt() - 1;
            passengers.add(new Passenger(sr, sc_, dr, dc));
        }
    }

    static void solve() {
        for (int i = 0; i < M; i++) {
            State p = findNearestPassenger();
            if (p == null || p.dist > fuel) {
                fuel = -1;
                return;
            }
            fuel -= p.dist;
            taxiR = passengers.get(p.idx).sr;
            taxiC = passengers.get(p.idx).sc;

            int toDest = bfs(taxiR, taxiC, passengers.get(p.idx).dr, passengers.get(p.idx).dc);
            if (toDest == -1 || toDest > fuel) {
                fuel = -1;
                return;
            }

            fuel -= toDest;
            fuel += toDest * 2;
            taxiR = passengers.get(p.idx).dr;
            taxiC = passengers.get(p.idx).dc;

            passengers.set(p.idx, null);
        }
    }

    static State findNearestPassenger() {
        visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{taxiR, taxiC, 0});
        visited[taxiR][taxiC] = true;
        PriorityQueue<State> pq = new PriorityQueue<>();

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            for (int i = 0; i < passengers.size(); i++) {
                Passenger p = passengers.get(i);
                if (p != null && p.sr == r && p.sc == c) {
                    pq.offer(new State(r, c, dist, i));
                }
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (inRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }

    static int bfs(int sr, int sc, int tr, int tc) {
        visited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];
            if (r == tr && c == tc) return dist;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (inRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, dist + 1});
                }
            }
        }
        return -1;
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    static void output() {
        System.out.println(fuel);
    }
}
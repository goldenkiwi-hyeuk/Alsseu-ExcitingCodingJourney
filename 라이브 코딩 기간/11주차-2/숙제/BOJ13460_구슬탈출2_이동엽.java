package boj;

import java.util.*;

public class Main13460 {
    static int n, m;
    static char[][] board;
    static boolean[] visited;
    final static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    static class State {
        int ry, rx, by, bx, d;

        State(int ry, int rx, int by, int bx, int d) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.d = d;
        }
    }

    static class MoveResult {
        int y, x, cnt;
        boolean inHole;

        MoveResult(int y, int x, int cnt, boolean inHole) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.inHole = inHole;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        board = new char[n][m];
        int ry = 0, rx = 0, by = 0, bx = 0;

        for (int i = 0; i < n; i++) {
            String line = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'R') {
                    ry = i;
                    rx = j;
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    by = i;
                    bx = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(ry, rx, by, bx));
        sc.close();
    }

    static int bfs(int ry, int rx, int by, int bx) {
        int total = n * m;
        visited = new boolean[total * total];
        Queue<State> queue = new LinkedList<>();

        int startRed = ry * m + rx;
        int startBlue = by * m + bx;
        visited[startRed * total + startBlue] = true;
        queue.add(new State(ry, rx, by, bx, 0));

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            if (cur.d >= 10) break;

            for (int dir = 0; dir < 4; dir++) {
                MoveResult r = move(cur.ry, cur.rx, dir);
                MoveResult b = move(cur.by, cur.bx, dir);

                if (b.inHole) continue;
                if (r.inHole) return cur.d + 1;

                if (r.y == b.y && r.x == b.x) {
                    if (r.cnt > b.cnt) {
                        r.y -= dy[dir];
                        r.x -= dx[dir];
                    } else {
                        b.y -= dy[dir];
                        b.x -= dx[dir];
                    }
                }

                int redIdx = r.y * m + r.x;
                int blueIdx = b.y * m + b.x;
                int stateId = redIdx * total + blueIdx;

                if (!visited[stateId]) {
                    visited[stateId] = true;
                    queue.add(new State(r.y, r.x, b.y, b.x, cur.d + 1));
                }
            }
        }
        return -1;
    }

    static MoveResult move(int y, int x, int dir) {
        int cnt = 0;
        while (true) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (board[ny][nx] == '#') break;
            y = ny;
            x = nx;
            cnt++;
            if (board[ny][nx] == 'O') {
                return new MoveResult(y, x, cnt, true);
            }
        }
        return new MoveResult(y, x, cnt, false);
    }
}

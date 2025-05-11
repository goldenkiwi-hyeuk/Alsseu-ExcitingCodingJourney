package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main16933 {

    static int n, m, k;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m][k + 1][2]; // [x][y][벽 부순 횟수][낮1/밤0]

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Move> q = new LinkedList<>();
        q.add(new Move(0, 0, 1, 0, true));
        visited[0][0][0][1] = true;

        while (!q.isEmpty()) {
            Move cur = q.poll();

            if (cur.x == n - 1 && cur.y == m - 1) {
                return cur.dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if (!isIn(nx, ny)) continue;

                boolean nNoon = !cur.noon;
                int nTime = nNoon ? 1 : 0;
                int nDist = cur.dist + 1;

                if (map[nx][ny] == '0') { // 길
                    if (cur.noon && !visited[nx][ny][cur.cnt][nTime]) {
                        visited[nx][ny][cur.cnt][nTime] = true;
                        q.add(new Move(nx, ny, nDist, cur.cnt, nNoon));
                    } else if (!cur.noon && !visited[nx][ny][cur.cnt][nTime]) {
                        visited[nx][ny][cur.cnt][nTime] = true;
                        q.add(new Move(nx, ny, nDist, cur.cnt, nNoon));
                    }
                } else { // 벽
                    if (cur.noon && cur.cnt < k && !visited[nx][ny][cur.cnt + 1][nTime]) { // 낮에 벽 부숨
                        visited[nx][ny][cur.cnt + 1][nTime] = true;
                        q.add(new Move(nx, ny, nDist, cur.cnt + 1, nNoon));
                    } else if (!cur.noon && !visited[cur.x][cur.y][cur.cnt][nTime]) { // 밤에 벽 못부숨
                        visited[cur.x][cur.y][cur.cnt][nTime] = true;
                        q.add(new Move(cur.x, cur.y, nDist, cur.cnt, nNoon));
                    }
                }
            }
        }

        return -1;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static class Move {
        int x, y, dist, cnt;
        boolean noon;

        public Move(int x, int y, int dist, int cnt, boolean noon) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.cnt = cnt;
            this.noon = noon;
        }
    }
}

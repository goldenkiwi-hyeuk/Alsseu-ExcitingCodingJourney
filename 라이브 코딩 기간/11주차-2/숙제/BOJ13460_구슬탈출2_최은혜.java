import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static Point red, blue;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class State {
        Point red, blue;
        int count;
        State(Point red, Point blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'R') red = new Point(i, j);
                else if (map[i][j] == 'B') blue = new Point(i, j);
            }
        }

        int result = bfs();
        System.out.println(result);
    }

    static int bfs() {
        Queue<State> queue = new ArrayDeque<>();
        queue.add(new State(red, blue, 0));
        visited[red.x][red.y][blue.x][blue.y] = true;

        while (!queue.isEmpty()) {
            State curr = queue.poll();
            if (curr.count >= 10) return -1;

            for (int d = 0; d < 4; d++) {
                Point nRed = move(curr.red, d);
                Point nBlue = move(curr.blue, d);

                if (map[nBlue.x][nBlue.y] == 'O') continue;

                if (map[nRed.x][nRed.y] == 'O') return curr.count + 1;

                if (nRed.x == nBlue.x && nRed.y == nBlue.y) {
                    int redDist = Math.abs(curr.red.x - nRed.x) + Math.abs(curr.red.y - nRed.y);
                    int blueDist = Math.abs(curr.blue.x - nBlue.x) + Math.abs(curr.blue.y - nBlue.y);
                    if (redDist > blueDist) {
                        nRed.x -= dr[d];
                        nRed.y -= dc[d];
                    } else {
                        nBlue.x -= dr[d];
                        nBlue.y -= dc[d];
                    }
                }

                if (!visited[nRed.x][nRed.y][nBlue.x][nBlue.y]) {
                    visited[nRed.x][nRed.y][nBlue.x][nBlue.y] = true;
                    queue.add(new State(nRed, nBlue, curr.count + 1));
                }
            }
        }
        return -1;
    }

    static Point move(Point start, int dir) {
        int x = start.x;
        int y = start.y;

        while (true) {
            if (map[x + dr[dir]][y + dc[dir]] == '#') break;
            x += dr[dir];
            y += dc[dir];
            if (map[x][y] == 'O') break;
        }
        return new Point(x, y);
    }
}

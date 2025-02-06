package softeer.로봇이지나간경로;

import java.io.*;
import java.util.*;

public class Main {

    static int h, w, dir;
    static boolean visited[][];
    static char map[][];
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1}; // 하v 우> 상^ 좌<
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        map = new char[h][w];
        visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            map[i] = br.readLine().toCharArray();
        }
        br.close();

        // 시작점은 무조건 2개
        int startX = 0, startY = 0;
        out:
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == '#' && isStart(i, j)) {
                    startX = i;
                    startY = j;
                    sb.append((i + 1) + " " + (j + 1)).append("\n");
                    break out;
                }
            }
        }

        dir = 0;
        // 방향 찾기
        for (int d = 0; d < 4; d++) {
            int nx = startX + dx[d], ny = startY + dy[d];
            if (isIn(nx, ny) && map[nx][ny] == '#') {
                dir = d;
                if (d == 0) {
                    sb.append("v").append("\n");
                } else if (d == 1) {
                    sb.append(">").append("\n");
                } else if (d == 2) {
                    sb.append("^").append("\n");
                } else {
                    sb.append("<").append("\n");
                }
            }
        }

        robot(startX, startY);
        System.out.println(sb);
    }

    // dfs
    static void robot(int x, int y) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];

            if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == '#') {
                if (dir != d) {
                    sb.append(drift(dir, d));
                    dir = d;
                }
                sb.append("A");
                visited[nx][ny] = true;
                robot(nx + dx[d], ny + dy[d]);
            }
        }

    }

    // 하우상좌 0 1 2 3
    static String drift(int now, int next) {
        return ((now + 1) % 4 == next) ? "L" : "R";
    }

    // 경계 체크
    static boolean isIn(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    // 4방 중 한 쪽만 길이 있을때 시작점
    static boolean isStart(int x, int y) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (isIn(nx, ny) && map[nx][ny] == '#') cnt++;
        }
        return cnt == 1;
    }
}

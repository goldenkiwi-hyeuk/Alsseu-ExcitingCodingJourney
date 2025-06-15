package boj;

import java.util.*;

public class Main18500 {

    static int r, c;
    static char[][] cave;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        cave = new char[r][c];
        for (int i = 0; i < r; i++) {
            cave[i] = sc.next().toCharArray();
        }
        int n = sc.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = r - sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            throwStick(heights[i], i % 2 == 0); // 짝 홀
            simulate();
        }

        StringBuilder sb = new StringBuilder();
        for (char[] c : cave) {
            sb.append(c).append("\n");
        }
        System.out.println(sb);
    }

    static void throwStick(int h, boolean isLeft) {
        if (isLeft) { // 왼 -> 오
            for (int j = 0; j < c; j++) {
                if (cave[h][j] == 'x') {
                    cave[h][j] = '.';
                    break;
                }
            }
        } else { // 오 -> 왼
            for (int j = c - 1; j >= 0; j--) {
                if (cave[h][j] == 'x') {
                    cave[h][j] = '.';
                    break;
                }
            }
        }
    }

    static void simulate() {
        visited = new boolean[r][c];

        // 바닥
        for (int j = 0; j < c; j++) {
            if (cave[r - 1][j] == 'x' && !visited[r - 1][j]) {
                bfs(r - 1, j);
            }
        }

        // 공중
        List<int[]> clusters = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (cave[i][j] == 'x' && !visited[i][j]) {
                    clusters.add(new int[]{i, j});
                }
            }
        }

        if (!clusters.isEmpty()) {
            drop(clusters);
        }

    }

    static void drop(List<int[]> clusters) {
        for (int[] cl : clusters) {
            cave[cl[0]][cl[1]] = '.';
        }

        int min = r;
        for (int[] p : clusters) {
            int nr = p[0];
            int nc = p[1];
            int dist = 0;
            while (nr + dist + 1 < r && cave[nr + dist + 1][nc] == '.') {
                dist++;
            }
            min = Math.min(min, dist);
        }

        clusters.sort(Comparator.comparingInt(p -> -p[0]));
        for (int[] cl : clusters) {
            cave[cl[0] + min][cl[1]] = 'x';
        }
    }

    // 바닥 클러스터 찾기
    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (!isIn(nx, ny) || visited[nx][ny]) continue;
                if (cave[nx][ny] == 'x') {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

}

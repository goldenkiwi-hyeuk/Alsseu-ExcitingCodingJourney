package boj;

import java.util.Scanner;

public class Main3109 {

    static int r, c, ans;
    final static int[] dx = {-1, 0, 1}, dy = {1, 1, 1};
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = sc.next().toCharArray();
        }
        sc.close();

        ans = 0;
        for (int i = 0; i < r; i++) {
            if (dfs(i, 0)) ans++;
        }

        System.out.println(ans);
    }

    static boolean dfs(int x, int y) {
        visited[x][y] = true;

        if (y == c - 1) return true;

        for (int d = 0; d < 3; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!isIn(nx, ny)) continue;
            if (visited[nx][ny]) continue;
            if (map[nx][ny] != '.') continue;

            if (dfs(nx, ny)) return true;
        }
        return false;
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < r && 0 <= y && y < c;
    }

}

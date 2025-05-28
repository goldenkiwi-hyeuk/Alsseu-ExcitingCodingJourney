package boj;

import java.util.Scanner;

public class Main16724 {

    static int n, m, cnt = 0;
    static char[][] map;
    static int[][] visited;

    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(cnt);
    }

    static void dfs(int x, int y) {
        visited[x][y] = 1;

        int dir = turn(map[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (visited[nx][ny] == 0) {
            dfs(nx, ny);
        } else if (visited[nx][ny] == 1) {
            cnt++;
        }

        visited[x][y] = 2;
    }

    static int turn(char c) {
        switch (c) {
            case 'U':
                return 0;
            case 'R':
                return 1;
            case 'D':
                return 2;
            case 'L':
                return 3;
        }
        return -1;
    }
}

package boj;

import java.util.Scanner;

public class Main16957 {

    static int r, c, map[][], balls[][], temp[][];
    static final int dx[] = {0, 1, 1, 1, 0, -1, -1, -1}, dy[] = {1, 1, 0, -1, -1, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        map = new int[r][c];
        balls = new int[r][c];
        temp = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
                temp[i][j] = -1;
            }
        }
        sc.close();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int[] arr = dfs(i, j);
                balls[arr[0]][arr[1]]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(balls[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);

    } // psvm


    static int[] dfs(int x, int y) {
        if (temp[x][y] != -1) {
            return new int[]{temp[x][y] / c, temp[x][y] % c};
        }

        int minX = x, minY = y;

        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
                if (map[nx][ny] < map[minX][minY]) {
                    minX = nx;
                    minY = ny;
                }
            }
        }

        if (minX == x && minY == y) {
            temp[x][y] = x * c + y;
            return new int[]{x, y};
        } else {
            int[] res = dfs(minX, minY);
            temp[x][y] = res[0] * c + res[1];
            return res;
        }
    }
}
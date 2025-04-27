package boj;

import java.util.*;

public class Main17780 {

    static int n, k, board[][];
    static ArrayList<Integer>[][] stacks;
    static Point[] pos;
    static final int[] dx = {0, 0, -1, 1}, dy = {1, -1, 0, 0}; // 1 2 3 4

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();
        board = new int[n][n];
        stacks = new ArrayList[n][n];
        pos = new Point[k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.nextInt();
                stacks[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < k; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            int dir = sc.nextInt() - 1;
            pos[i] = new Point(x, y, dir);
            stacks[x][y].add(i);
        }

        int turn = 0;
        while (true) {
            turn++;
            if (turn > 1000) {
                System.out.println(-1);
                return;
            }

            for (int i = 0; i < k; i++) {
                if (move(i)) {
                    System.out.println(turn);
                    return;
                }
            }
        }
    }

    private static boolean move(int idx) {
        Point p = pos[idx];
        int x = p.x;
        int y = p.y;
        int dir = p.dir;

        if (stacks[x][y].get(0) != idx) {
            return false;
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if (!isIn(nx, ny) || board[nx][ny] == 2) {
            dir = changeDir(dir);
            pos[idx].dir = dir;

            nx = x + dx[dir];
            ny = y + dy[dir];

            if (!isIn(nx, ny) || board[nx][ny] == 2) {
                return false;
            }
        }

        List<Integer> list = new ArrayList<>();
        int start = stacks[x][y].indexOf(idx);
        for (int i = start; i < stacks[x][y].size(); i++) {
            list.add(stacks[x][y].get(i));
        }
        for (int i = stacks[x][y].size() - 1; i >= start; i--) {
            stacks[x][y].remove(i);
        }

        if (board[nx][ny] == 1) {
            Collections.reverse(list);
        }

        for (int num : list) {
            stacks[nx][ny].add(num);
            pos[num].x = nx;
            pos[num].y = ny;
        }

        if (stacks[nx][ny].size() >= 4) {
            return true;
        }

        return false;
    }

    private static int changeDir(int dir) {
        if (dir == 0) return 1;
        if (dir == 1) return 0;
        if (dir == 2) return 3;
        return 2;
    }

    private static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class Point {
        int x, y, dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}

package boj;

import java.util.*;

public class Main21609 {
    static int BLACK = -1, RAINBOW = 99, EMPTY = 0;
    static int n, m, score;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    static class Block {
        int x, y, color, sum, rainbow;
        public Block(int x, int y, int color, int sum, int rainbow) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.sum = sum;
            this.rainbow = rainbow;
        }
        public boolean compareBlock(Block other) {
            if (this.sum != other.sum)
                return this.sum < other.sum;
            if (this.rainbow != other.rainbow)
                return this.rainbow < other.rainbow;
            if (this.x != other.x)
                return this.x < other.x;
            return this.y < other.y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        score = 0;
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = sc.nextInt();
                if (num == -1)
                    map[i][j] = BLACK;
                else if (num == 0)
                    map[i][j] = RAINBOW;
                else
                    map[i][j] = num;
            }
        }

        while (true) {
            boolean[][] visited = new boolean[n][n];
            Block maxBlock = new Block(0, 0, EMPTY, Integer.MIN_VALUE, Integer.MIN_VALUE);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    if (map[i][j] == BLACK || map[i][j] == RAINBOW || map[i][j] == EMPTY) continue;

                    for (int a = 0; a < n; a++) {
                        for (int b = 0; b < n; b++) {
                            if (map[a][b] == RAINBOW)
                                visited[a][b] = false;
                        }
                    }

                    Block cur = bfs(i, j, map[i][j], visited);
                    if (cur == null) continue;
                    if (maxBlock.compareBlock(cur)) {
                        maxBlock = cur;
                    }
                }
            }

            if (maxBlock.color == EMPTY)
                break;

            score += maxBlock.sum * maxBlock.sum;
            remove(maxBlock);
            gravity();
            rotate();
            gravity();
        }

        System.out.println(score);
    }

    static Block bfs(int sx, int sy, int color, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        int sum = 1;
        int rainbowSum = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (!isIn(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] == BLACK || map[nx][ny] == EMPTY) continue;

                if (map[nx][ny] != color) {
                    if (map[nx][ny] == RAINBOW) {
                        rainbowSum++;
                        sum++;
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                    }
                    continue;
                }

                sum++;
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        if (sum < 2)
            return null;
        return new Block(sx, sy, color, sum, rainbowSum);
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static void remove(Block block) {
        boolean[][] visited = new boolean[n][n];
        visited[block.x][block.y] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{block.x, block.y});
        map[block.x][block.y] = EMPTY;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0], y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (!isIn(nx, ny) || visited[nx][ny]) continue;
                if (map[nx][ny] == BLACK || map[nx][ny] == EMPTY) continue;

                if (map[nx][ny] != block.color) {
                    if (map[nx][ny] == RAINBOW) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        map[nx][ny] = EMPTY;
                    }
                    continue;
                }
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny});
                map[nx][ny] = EMPTY;
            }
        }
    }

    static void gravity() {
        for (int j = 0; j < n; j++) {
            for (int i = n - 2; i >= 0; i--) {
                if (map[i][j] == BLACK || map[i][j] == EMPTY) continue;
                moveBlock(i, j);
            }
        }
    }

    static void moveBlock(int x, int y) {
        int cx = x;
        while (true) {
            cx++;
            if (cx >= n)
                break;
            if (map[cx][y] == BLACK || map[cx][y] != EMPTY)
                break;
        }
        cx--;
        if (cx == x)
            return;
        map[cx][y] = map[x][y];
        map[x][y] = EMPTY;
    }

    static void rotate() {
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = map[j][n - 1 - i];
            }
        }
        map = temp;
    }
}

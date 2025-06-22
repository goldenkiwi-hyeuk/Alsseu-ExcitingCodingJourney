package boj;

import java.util.*;

public class Main19238 {

    static int n, m, fuel;
    static int[][] map, time;
    static boolean[][] visited;
    static int taxiX, taxiY;
    static int minX, minY, minD, man;
    static final int WALL = 97;
    static final int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    static Map<Integer, Taxi> goal = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        fuel = sc.nextInt();

        map = new int[n][n];
        time = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt() == 1 ? WALL : 0;
            }
        }

        taxiX = sc.nextInt() - 1;
        taxiY = sc.nextInt() - 1;

        for (int i = 1; i <= m; i++) {
            int sx = sc.nextInt() - 1;
            int sy = sc.nextInt() - 1;
            int dx = sc.nextInt() - 1;
            int dy = sc.nextInt() - 1;
            map[sx][sy] = i;
            goal.put(i, new Taxi(dx, dy));
        }

        System.out.println(start() ? fuel : -1);
        sc.close();
    }

    static boolean start() {
        for (int i = 0; i < m; i++) {
            reset();
            if (!findClosest()) return false;

            fuel -= minD;
            if (fuel < 0) return false;

            taxiX = minX;
            taxiY = minY;

            reset();
            if (!deliver()) return false;

            fuel += minD * 2;
        }
        return true;
    }

    static boolean findClosest() {
        if (map[taxiX][taxiY] > 0 && map[taxiX][taxiY] < WALL) {
            minX = taxiX;
            minY = taxiY;
            minD = 0;
            man = map[taxiX][taxiY];
            return true;
        }

        Queue<Taxi> q = new LinkedList<>();
        q.offer(new Taxi(taxiX, taxiY));
        visited[taxiX][taxiY] = true;
        time[taxiX][taxiY] = 0;

        minD = Integer.MAX_VALUE;
        minX = minY = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Taxi t = q.poll();
            int cx = t.x;
            int cy = t.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!isIn(nx, ny) || isWall(nx, ny) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                time[nx][ny] = time[cx][cy] + 1;

                if (map[nx][ny] > 0 && map[nx][ny] < WALL) {
                    if (time[nx][ny] < minD ||
                            (time[nx][ny] == minD && (nx < minX || (nx == minX && ny < minY)))) {
                        minX = nx;
                        minY = ny;
                        minD = time[nx][ny];
                        man = map[nx][ny];
                    }
                }

                q.offer(new Taxi(nx, ny));
            }
        }

        return minD != Integer.MAX_VALUE;
    }

    static boolean deliver() {
        Taxi t = goal.get(man);
        map[taxiX][taxiY] = 0;

        Queue<Taxi> q = new LinkedList<>();
        q.offer(new Taxi(taxiX, taxiY));
        visited[taxiX][taxiY] = true;
        time[taxiX][taxiY] = 0;

        while (!q.isEmpty()) {
            Taxi cur = q.poll();
            int cx = cur.x, cy = cur.y;

            if (cx == t.x && cy == t.y) {
                minD = time[cx][cy];
                minX = cx;
                minY = cy;
                fuel -= minD;
                if (fuel < 0) return false;

                goal.remove(man);
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (!isIn(nx, ny) || isWall(nx, ny) || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                time[nx][ny] = time[cx][cy] + 1;
                q.offer(new Taxi(nx, ny));
            }
        }

        return false;
    }

    static void reset() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
            Arrays.fill(time[i], -1);
        }
    }

    static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static boolean isWall(int x, int y) {
        return map[x][y] == WALL;
    }
}

class Taxi {
    int x, y;

    Taxi(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

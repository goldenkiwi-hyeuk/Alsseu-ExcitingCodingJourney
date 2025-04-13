package boj;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class Main18809 {

    static int n, m, g, r, max = 0;
    static int[][] map;
    static List<Point> list = new ArrayList<>();
    static final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    static class Garden {
        int x, y, time;
        boolean isGreen;

        Garden(int x, int y, int time, boolean isGreen) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.isGreen = isGreen;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        g = sc.nextInt();
        r = sc.nextInt();
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) {
                    list.add(new Point(i, j));
                }
            }
        }

        comb(new ArrayList<>(), 0, g + r);
        System.out.println(max);
    }

    static void comb(List<Integer> selected, int idx, int total) {
        if (selected.size() == total) {
            combineGreen(selected, new ArrayList<>(), 0, g);
            return;
        }
        if (idx == list.size()) return;

        selected.add(idx);
        comb(selected, idx + 1, total);
        selected.remove(selected.size() - 1);
        comb(selected, idx + 1, total);
    }

    static void combineGreen(List<Integer> selected, List<Integer> greenList, int idx, int cnt) {
        if (greenList.size() == cnt) {
            List<Point> green = new ArrayList<>();
            List<Point> red = new ArrayList<>();
            for (int i = 0; i < selected.size(); i++) {
                Point p = list.get(selected.get(i));
                if (greenList.contains(i)) {
                    green.add(p);
                } else {
                    red.add(p);
                }
            }
            bfs(green, red);
            return;
        }
        if (idx == selected.size()) return;

        greenList.add(idx);
        combineGreen(selected, greenList, idx + 1, cnt);
        greenList.remove(greenList.size() - 1);
        combineGreen(selected, greenList, idx + 1, cnt);
    }

    static void bfs(List<Point> green, List<Point> red) {
        int[][] color = new int[n][m];
        int[][] time = new int[n][m];
        Queue<Garden> q = new LinkedList<>();
        boolean[][] flower = new boolean[n][m];
        int flowers = 0;

        for (Point p : green) {
            q.offer(new Garden(p.x, p.y, 0, true));
            color[p.x][p.y] = 1; // green
            time[p.x][p.y] = 0;
        }

        for (Point p : red) {
            q.offer(new Garden(p.x, p.y, 0, false));
            color[p.x][p.y] = 2; // red
            time[p.x][p.y] = 0;
        }

        while (!q.isEmpty()) {
            int size = q.size();

            Queue<Garden> next = new LinkedList<>();
            while (size-- > 0) {
                Garden cur = q.poll();

                if (flower[cur.x][cur.y]) continue;

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    if (map[nx][ny] == 0) continue;
                    if (flower[nx][ny]) continue;

                    if (color[nx][ny] == 0) {
                        color[nx][ny] = cur.isGreen ? 1 : 2;
                        time[nx][ny] = cur.time + 1;
                        next.offer(new Garden(nx, ny, cur.time + 1, cur.isGreen));
                    } else if (color[nx][ny] == 1 && !cur.isGreen && time[nx][ny] == cur.time + 1) {
                        flower[nx][ny] = true;
                        flowers++;
                    } else if (color[nx][ny] == 2 && cur.isGreen && time[nx][ny] == cur.time + 1) {
                        flower[nx][ny] = true;
                        flowers++;
                    }
                }
            }

            q = next;
        }

        max = Math.max(max, flowers);
    }
}

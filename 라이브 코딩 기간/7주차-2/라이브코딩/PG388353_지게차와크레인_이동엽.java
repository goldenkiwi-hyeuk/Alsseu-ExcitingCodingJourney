package programmers.지게차와_크레인;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    int n, m, total;
    int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    char[][] logi;
    boolean[][] visited;

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        total = n * m;
        logi = new char[n][m];

        for (int i = 0; i < n; i++) {
            logi[i] = storage[i].toCharArray();
        }

        for (String s : requests) {
            visited = new boolean[n][m];
            if (s.length() == 2) {
                crane(s.charAt(0));
            } else {
                lifter(s.charAt(0));
            }
        }

        return total;
    }

    private void crane(char c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (logi[i][j] == c) {
                    total--;
                    logi[i][j] = 0;
                }
            }
        }
    }

    private void lifter(char c) {
        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (logi[i][j] == c) {
                    visited = new boolean[n][m];
                    if (isOuter(i, j)) {
                        list.add(new int[]{i, j});
                    }
                }
            }
        }

        for (int[] l : list) {
            logi[l[0]][l[1]] = 0;
            total--;
        }
    }

    private boolean isOuter(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];

            if (cx == 0 || cx == n - 1 || cy == 0 || cy == m - 1) return true;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (!isIn(nx, ny)) continue;
                if (logi[nx][ny] != 0) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        return false;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}


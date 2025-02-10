package programmers.수레움직이기;

class Solution {

    int n, m, rx, ry, bx, by, answer, map[][];
    int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1}; // 상 하 좌 우
    boolean rVisited[][], bVisited[][];

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        map = new int[n][m];
        rVisited = new boolean[n][m];
        bVisited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = maze[i].clone();
        }

        rx = -1;
        ry = -1;
        bx = -1;
        by = -1;
        out:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    rVisited[i][j] = true;
                    rx = i;
                    ry = j;
                    continue;
                }
                if (maze[i][j] == 2) {
                    bVisited[i][j] = true;
                    bx = i;
                    by = j;
                    continue;
                }
                if (rx != -1 && ry != -1 && bx != -1 && by != -1) break out;
            }
        }

        answer = 987654321;
        carrier(0, rx, ry, bx, by);

        return answer == 987654321 ? 0 : answer;
    }

    void carrier(int cnt, int rx, int ry, int bx, int by) {
        if (map[rx][ry] == 3 && map[bx][by] == 4) {
            answer = Math.min(answer, cnt);
            return;
        } else if (map[rx][ry] == 3) { // 빨강만 도착 = 파랑 이동
            for (int d = 0; d < 4; d++) {
                int nx = bx + dx[d];
                int ny = by + dy[d];
                if (!isIn(nx, ny)) continue;
                if (bVisited[nx][ny]) continue;
                if (map[nx][ny] == 3 || map[nx][ny] == 5) continue;
                bVisited[nx][ny] = true;
                carrier(cnt + 1, rx, ry, nx, ny);
                bVisited[nx][ny] = false;
            }
        } else if (map[bx][by] == 4) { // 파랑만 도착 = 빨강 이동
            for (int d = 0; d < 4; d++) {
                int nx = rx + dx[d];
                int ny = ry + dy[d];
                if (!isIn(nx, ny)) continue;
                if (rVisited[nx][ny]) continue;
                if (map[nx][ny] == 4 || map[nx][ny] == 5) continue;
                rVisited[nx][ny] = true;
                carrier(cnt + 1, nx, ny, bx, by);
                rVisited[nx][ny] = false;
            }
        } else { // 둘 다 x
            // 빨강이동
            for (int d1 = 0; d1 < 4; d1++) {
                int nx1 = rx + dx[d1];
                int ny1 = ry + dy[d1];
                if (!isIn(nx1, ny1)) continue;
                if (rVisited[nx1][ny1]) continue;
                if (map[nx1][ny1] == 5) continue;

                // 파랑이동
                for (int d2 = 0; d2 < 4; d2++) {
                    int nx2 = bx + dx[d2];
                    int ny2 = by + dy[d2];
                    if (!isIn(nx2, ny2)) continue;
                    if (bVisited[nx2][ny2]) continue;
                    if (map[nx2][ny2] == 5) continue;
                    if ((nx2 == rx && ny2 == ry) && (nx1 == bx && ny1 == by)) continue;
                    if (nx1 == nx2 && ny1 == ny2) continue;

                    rVisited[nx1][ny1] = true;
                    bVisited[nx2][ny2] = true;
                    carrier(cnt + 1, nx1, ny1, nx2, ny2);
                    rVisited[nx1][ny1] = false;
                    bVisited[nx2][ny2] = false;

                }
            }
        }
    }

    boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

}
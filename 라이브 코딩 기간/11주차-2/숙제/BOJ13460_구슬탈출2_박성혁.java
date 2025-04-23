import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 기본아이디어 : DFS + 구현

    static char[][] map;
    static boolean[][][][] visited;
    static int N, M, ans;
    static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M]; // 방문처리
        ans = 987654321;
        int Rrow = -1;
        int Rcol = -1;
        int Brow = -1;
        int Bcol = -1;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'R') {
                    Rrow = i;
                    Rcol = j;
                } else if (map[i][j] == 'B') {
                    Brow = i;
                    Bcol = j;
                }
            }
        }
        visited[Rrow][Rcol][Brow][Bcol] = true;
        go(Rrow, Rcol, Brow, Bcol, 0);
        if (ans <= 10) {
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }


    }

    private static void go(int Rrow, int Rcol, int Brow, int Bcol, int cnt) {
        if (cnt > 10) {
            return;
        }
        if (inRange(Brow, Bcol) && map[Brow][Bcol] == 'O') {
            return;
        }
        if (inRange(Rrow, Rcol) && map[Rrow][Rcol] == 'O') {
            if (ans > cnt) {
                ans = cnt;
            }
            return;
        }
        for (int dir = 0; dir < 4; dir++) { // 기울이기 방향 정하기
            int[] moveR = new int[2];
            int[] moveB = new int[2];
            if (dir == 0) {
                if (Rrow < Brow) {
                    moveR = checkMap(Rrow, Rcol, dir);
                    moveB = checkMap(Brow, Bcol, dir, moveR[0], moveR[1]);
                } else {
                    moveB = checkMap(Brow, Bcol, dir);
                    moveR = checkMap(Rrow, Rcol, dir, moveB[0], moveB[1]);
                }
            } else if (dir == 1) {
                if (Rrow > Brow) {
                    moveR = checkMap(Rrow, Rcol, dir);
                    moveB = checkMap(Brow, Bcol, dir, moveR[0], moveR[1]);
                } else {
                    moveB = checkMap(Brow, Bcol, dir);
                    moveR = checkMap(Rrow, Rcol, dir, moveB[0], moveB[1]);
                }
            } else if (dir == 2) {
                if (Rcol < Bcol) {
                    moveR = checkMap(Rrow, Rcol, dir);
                    moveB = checkMap(Brow, Bcol, dir, moveR[0], moveR[1]);
                } else {
                    moveB = checkMap(Brow, Bcol, dir);
                    moveR = checkMap(Rrow, Rcol, dir, moveB[0], moveB[1]);
                }
            } else if (dir == 3) {
                if (Rcol > Bcol) {
                    moveR = checkMap(Rrow, Rcol, dir);
                    moveB = checkMap(Brow, Bcol, dir, moveR[0], moveR[1]);
                } else {
                    moveB = checkMap(Brow, Bcol, dir);
                    moveR = checkMap(Rrow, Rcol, dir, moveB[0], moveB[1]);
                }
            }

            if (!visited[moveR[0]][moveR[1]][moveB[0]][moveB[1]]) { // 반복이 아니라 새로운 움직임의 수일때만 이동
                // dfs가고
                visited[moveR[0]][moveR[1]][moveB[0]][moveB[1]] = true;
                go(moveR[0], moveR[1], moveB[0], moveB[1], cnt + 1);
                visited[moveR[0]][moveR[1]][moveB[0]][moveB[1]] = false;
            }
        }
    }

    private static int[] checkMap(int row, int col, int dir, int otherRow, int otherCol) {
        int beforeRow = row;
        int beforeCol = col;
        int nextrow = row;
        int nextcol = col;
        for (int i = 1; i <= Math.max(N, M); i++) {
            int nr = row + delta[0][dir] * i;
            int nc = col + delta[1][dir] * i;
            if (!inRange(nr, nc)) {
                break;
            } else {
                if (map[nr][nc] == '#') { // 벽을 만나서 더이상 움직이지 않는 케이스
                    nextrow = beforeRow;
                    nextcol = beforeCol;
                    break;
                }
                if (map[nr][nc] == 'O') { // 구멍에 들어간 케이스
                    nextrow = nr;
                    nextcol = nc;
                    break;
                }
                if (nr == otherRow && nc == otherCol) { // 다른 공을 만나서 더이상 움직이지 않는 케이스
                    nextrow = beforeRow;
                    nextcol = beforeCol;
                    break;
                }
            }
            beforeRow = nr;
            beforeCol = nc;
        }
        return new int[]{nextrow, nextcol};
    }


    private static int[] checkMap(int row, int col, int dir) {
        int beforeRow = row;
        int beforeCol = col;
        int nextrow = row;
        int nextcol = col;
        for (int i = 1; i <= Math.max(N, M); i++) {
            int nr = row + delta[0][dir] * i;
            int nc = col + delta[1][dir] * i;
            if (!inRange(nr, nc)) {
                break;
            } else {
                if (map[nr][nc] == '#') { // 벽을 만나서 더이상 움직이지 않는 케이스
                    nextrow = beforeRow;
                    nextcol = beforeCol;
                    break;
                }
                if (map[nr][nc] == 'O') { // 구멍에 들어간 케이스
                    nextrow = nr;
                    nextcol = nc;
                    break;
                }
                beforeRow = nr;
                beforeCol = nc;
            }
        }
        return new int[]{nextrow, nextcol};
    }

    private static boolean inRange(int row, int col) {
        if (row >= 0 && row < N && col >= 0 && col < M) {
            return true;
        }
        return false;
    }
}

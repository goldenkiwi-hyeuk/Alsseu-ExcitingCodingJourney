import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ13460_구슬탈출2_정해준 {
    static class Dama {
        int x;
        int y;
        int cnt;
        public Dama(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    static int N, M;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M][N][M]; // 빨강 : 파랑 방문 확인
        map = new char[N][M];
        Dama[] balls = new Dama[2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for(int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'R') {
                    balls[0] = new Dama(i, j, 0);
                    map[i][j] = '.';
                }
                if(map[i][j] == 'B') {
                    balls[1] = new Dama(i, j, 0);
                    map[i][j] = '.';
                }
            }
        }
        int result = bfs(balls);
        System.out.println(result);
    }

    public static int bfs(Dama[] balls) {
        Queue<Dama[]> queue = new LinkedList<>();
        queue.offer(balls);
        visited[balls[0].x][balls[0].y][balls[1].x][balls[1].y] = true;

        while (!queue.isEmpty()) {
            Dama[] cur = queue.poll();
            Dama r = cur[0];
            Dama b = cur[1];

            if (r.cnt >= 10) return -1; // 지금이 열번째 이상이면 움직이기 전에

            for (int d = 0; d < 4; d++) {
                // 현재 상태를 복사해서 rolling
                Dama red = new Dama(r.x,r.y, r.cnt);
                Dama blue = new Dama(b.x,b.y, b.cnt);

                int result = rolling(red, blue, d); // 방향 d로 굴리기

                if (result == 1)
                    return r.cnt + 1; // 빨간 구슬만 성공

                if (result == -1)
                    continue; // 파란 구슬 실패

                if (!visited[red.x][red.y][blue.x][blue.y]) {
                    visited[red.x][red.y][blue.x][blue.y] = true;
                    queue.offer(new Dama[]{new Dama(red.x, red.y, r.cnt + 1), new Dama(blue.x, blue.y, b.cnt + 1)});
                }
            }
        }

        return -1; // 끝까지 못 빠짐
    }


    public static int rolling(Dama red, Dama blue, int d) {
        // 방향: 상 하 좌 우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int rx = red.x, ry = red.y;
        int bx = blue.x, by = blue.y;

        // 구슬 이동
        int[] rPos = move(rx, ry, d);
        int[] bPos = move(bx, by, d);

        // 파란 구슬이 구멍에 들어간 경우 실패
        if (map[bPos[0]][bPos[1]] == 'O') {
            return -1; // 실패 처리
        }

        // 빨간 구슬이 구멍에 들어간 경우
        if (map[rPos[0]][rPos[1]] == 'O') {
            // 성공 처리
            return 1;
        }

        // 두 구슬 위치가 같으면 나중에 움직인 구슬을 한 칸 뒤로
        if (rPos[0] == bPos[0] && rPos[1] == bPos[1]) {
            if (compare(rx, ry, bx, by, d)) { //true면 빨강이 먼저
                bPos[0] -= dr[d];
                bPos[1] -= dc[d];
            } else {
                rPos[0] -= dr[d];
                rPos[1] -= dc[d];
            }
        }

        red.x = rPos[0];
        red.y = rPos[1];
        blue.x = bPos[0];
        blue.y = bPos[1];
        return 0;
    }

    static int[] move(int x, int y, int d) {
        while(true) {
            int nx = x + dr[d];
            int ny = y + dc[d];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M)
                break;
            if(map[nx][ny] == '#') // 벽일 경우
                break;
            x = nx;
            y = ny;
            if(map[x][y] == 'O') //들어갔을 경우
                break;
        }
        return new int[]{x, y};
    }

    static boolean compare(int rx, int ry, int bx, int by, int d) {
        if(d == 0) { //상
            return rx < bx;
        } else if (d == 1){ // 하
            return rx > bx;
        } else if (d == 2) { //좌
            return ry < by;
        } else { // 우
            return ry > by;
        }
    }
}


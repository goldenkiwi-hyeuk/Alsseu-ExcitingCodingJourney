import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ16993_벽부수고이동하기3_정해준 { // (0,0) -> (N-1, M-1) 까지
    static class State {
        int x, y, breaker, time, day;
        State(int x, int y, int breaker, int time, int day) {
            this.x = x;
            this.y = y;
            this.breaker = breaker;
            this.time = time;
            this.day = day;
        }
    }

    static int[] dr = {-1, 1, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0};
    static boolean[][][][] visited; // x좌표, y좌표, 낮,밤, 벽 부순갯수
    static int N, M, K;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M][K + 1][2]; // 낮은 0, 밤음 1
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }


    public static int bfs() {
        Deque<State> q = new ArrayDeque<>();
        q.offer(new State(0,0,0,1,0)); // (0,0) 위치에 낮이고 부슨 벽은 없음
        visited[0][0][0][0] = true;
        while(!q.isEmpty()) {
            State now = q.poll();
            if(now.x == N -1 && now.y == M -1) {
                return now.time;
            }

            for(int i = 0; i < 5; i++) {
                int nx = now.x + dr[i];
                int ny = now.y + dc[i];
                int nextDay = 1 - now.day;
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) // 범위 확인
                    continue;
                // 제자리 이동
                if(i == 4) {
                    // 이미 방문했으면 스킵
                    if(visited[now.x][now.y][now.breaker][nextDay])
                        continue;
                    visited[now.x][now.y][now.breaker][nextDay] = true;
                    q.offer(new State(now.x, now.y, now.breaker, now.time + 1, nextDay));
                    continue;
                }
                
                if(map[nx][ny] == 0 && !visited[nx][ny][now.breaker][nextDay]) { // 빈칸일 경우
                    visited[nx][ny][now.breaker][nextDay] = true;
                    q.offer(new State(nx, ny, now.breaker, now.time + 1, nextDay));
                } else if (map[nx][ny] == 1 && now.day == 0 && now.breaker < K && !visited[nx][ny][now.breaker + 1][nextDay]) { // 낮이어서 벽을 부술 수 있을 경우
                    visited[nx][ny][now.breaker + 1][nextDay] = true;
                    q.offer(new State(nx, ny, now.breaker + 1, now.time + 1, nextDay));
                }

            }
        }
        return -1;
    }

}

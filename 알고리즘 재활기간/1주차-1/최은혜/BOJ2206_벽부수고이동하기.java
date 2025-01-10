import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206_벽부수고이동하기 {
    static int N, M;
    static int[][] map;
    static int result;
    static boolean possible;

    public static class Point {
        int x;
        int y;
        int broken;

        public Point(int x, int y, int broken) {
            this.x = x;
            this.y = y;
            this.broken = broken;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        int[][][] visit = new int[N][M][2];

        for (int r = 0; r < N; r++) {
            String line = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = line.charAt(c) - '0';
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<Point> que = new ArrayDeque<>();
        que.add(new Point(0, 0,0));
        visit[0][0][0] = 1;

        while (!que.isEmpty()) {
            Point now = que.poll();

            if(now.x==N-1 && now.y==M-1){
                System.out.println(visit[now.x][now.y][now.broken]);
                return;
            }

            int nr, nc;
            for (int dir = 0; dir < 4; dir++) {
                nr = now.x + dr[dir];
                nc = now.y + dc[dir];


                if (nr < 0 || nc < 0 || nr > N - 1 || nc > M - 1) continue;
                if (map[nr][nc] == 1) {
                    if(now.broken==0 && visit[nr][nc][1] == 0){
                        visit[nr][nc][1] = visit[now.x][now.y][0] + 1;
                        que.add(new Point(nr,nc,1));
                    }
                } else {
                    if(visit[nr][nc][now.broken] == 0){
                        visit[nr][nc][now.broken] = visit[now.x][now.y][now.broken] + 1;
                        que.add(new Point(nr,nc,now.broken));
                    }
                }
            }

        }

        System.out.println(-1);

    }
}

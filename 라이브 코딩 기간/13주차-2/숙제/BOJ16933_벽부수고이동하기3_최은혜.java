import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Point{
        int x;
        int y;
        int broken;
        int day;

        public Point(int x, int y, int broken, int day){
            this.x = x;
            this.y = y;
            this.broken = broken;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j)-'0';
            }
        }

        int [][][][] visited = new int[N][M][K+1][2]; // x, y, 벽부순횟수, 낮/밤

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        Queue<Point> que = new ArrayDeque<>();
        que.add(new Point(0, 0, 0, 0));
        visited[0][0][0][0] = 1;

        while(!que.isEmpty()){
            Point now = que.poll();

            if(now.x==N-1 && now.y==M-1){
                System.out.println(visited[now.x][now.y][now.broken][now.day]);
                return;
            }

            int nr, nc;
            for(int dir =0; dir<4; dir++){
                nr = now.x + dr[dir];
                nc = now.y + dc[dir];

                if(nr<0 || nc<0 || nr>N-1 || nc>M-1) continue;
                // 벽일때
                if(map[nr][nc]==1){
                    if(now.broken<K){
                        // 낮이라면 벽부수고 이동 가능
                        if(now.day==0 && visited[nr][nc][now.broken][1] == 0){
                            visited[nr][nc][now.broken + 1][1] = visited[now.x][now.y][now.broken][now.day] + 1;
                            que.add(new Point(nr, nc, now.broken + 1, 1));
                            // 밤이라면 기다림
                        } else if(now.day==1 && visited[now.x][now.y][now.broken][0]==0){
                            visited[now.x][now.y][now.broken][0] = visited[now.x][now.y][now.broken][1] + 1;
                            que.add(new Point(now.x, now.y, now.broken, 0));
                        }
                    }
                    // 빈 칸이면 바로 이동 가능
                } else {
                    if(visited[nr][nc][now.broken][1 - now.day] == 0) {
                        visited[nr][nc][now.broken][1 - now.day] = visited[now.x][now.y][now.broken][now.day] + 1;
                        que.add(new Point(nr, nc, now.broken, 1 - now.day));
                    }
                }
            }
        }

        System.out.println(-1);

    }
}

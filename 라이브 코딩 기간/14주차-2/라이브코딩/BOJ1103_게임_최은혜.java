import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dp;
    static int result;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static boolean isPossible = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j);
            }
        }

        visited = new boolean[N][M];
        dp = new int[N][M];

        visited[0][0] = true;;
        dfs(0,0,1);

        if(isPossible){
            System.out.println(-1);
        } else{
            System.out.println(result);
        }
    }

    public static void dfs(int x, int y, int cnt){

        dp[x][y] = cnt;
        result = Integer.max(cnt, result);

        int val = map[x][y] - '0';
        for(int dir=0; dir<4; dir++){
            int nr = x + (val*dr[dir]);
            int nc = y + (val*dc[dir]);

            if(nr<0 || nc<0 || nr>N-1 || nc>M-1 || map[nr][nc]=='H') continue;

            if(cnt < dp[nr][nc]) continue;

            if(visited[nr][nc]) {
                isPossible = true;
                return;
            }

            visited[nr][nc] = true;
            dfs(nr,nc,cnt+1);
            visited[nr][nc] = false;

        }
    }




}
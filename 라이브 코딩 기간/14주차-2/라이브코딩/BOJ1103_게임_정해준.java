// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;


class BOJ1103_게임_정해준 { // 이동한 값과 도착한 값이 같을 경우 무한
    static int N, M;
    static int[][] map;
    static int[][] memo;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,-1,1};
    static int score = 0;
    static boolean visited[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        memo = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String str = sc.next();
            for(int j = 0; j < M; j++) {
                if(str.charAt(j) == 'H')
                    map[i][j] = -1;
                else
                    map[i][j] = str.charAt(j) - '0';
            }
        }
        visited[0][0] = true;
        dfs(0,0,1);
        System.out.println(score);

    }

    static void dfs(int x, int y, int t){
        // System.out.println(x + "  " + y + " " + t);
        if(score == -1)
            return;
        score = Math.max(score, t);
        for(int i = 0; i < 4; i++) {
            int nx = x + dr[i] * map[x][y];
            int ny = y + dc[i] * map[x][y];
            if(overCheck(nx,ny) && map[nx][ny] != -1 && t + 1 > memo[nx][ny]) {
                if(visited[nx][ny]){
                    score = -1;
                    return;
                }
                visited[nx][ny] = true;
                memo[nx][ny] = t + 1;// 여기서 스코어를 갱신할 경우 문제가 있음, 전역변수 문제 
                dfs(nx, ny, t + 1);
                visited[nx][ny] = false;
            }
        }
    }

    static boolean overCheck(int x, int y) {
        return !(x < 0 || y < 0 || x >= N || y >= M);
    }
}
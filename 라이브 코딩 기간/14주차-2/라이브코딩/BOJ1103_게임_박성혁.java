import java.util.*;
import java.io.*;

class Main {
    
    // 기본아이디어 : dfs 구현
    
    static int[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
    static int N, M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i<N;++i){
            String str = br.readLine();
            for(int j = 0; j<M ; ++j){
                if(str.charAt(j)=='H'){
                    board[i][j] = -1;
                } else {
                    board[i][j] = str.charAt(j) - 48;
                }
            }
        }
        
        int ans = checkBoard(0,0,1);
        if(ans == 987654321){
            System.out.println(-1);
        } else {
            System.out.println(ans);    
        }
    }
    
    private static int checkBoard(int row, int col, int cnt){
        // 이미방문한적이 있다면 사이클이 존재한다는 의미
        if(visited[row][col]){ 
            return 987654321;
        }
        if(cnt<=dp[row][col]){
            return cnt;
        }
        int ans = cnt;
        visited[row][col] = true;
        dp[row][col] = cnt;
        for(int dir = 0 ; dir < 4 ; ++dir){
            int nr = row + delta[0][dir]*board[row][col];
            int nc = col + delta[1][dir]*board[row][col];
            if(inRange(nr,nc) && board[nr][nc] != -1){
                ans = Math.max(ans,checkBoard(nr, nc, cnt+1));
                if(ans == 987654321){
                    break;
                }
            }
        }
        visited[row][col] = false;
        return ans;
    }
    
    private static boolean inRange(int row, int col){
        if((row >=0)&&(row<N)&&(col>=0)&&(col<M)){
            return true;
        } else {
            return false;
        }
    }
}
import java.util.*;

class Solution {
    static int answer, N;
    static int[][] map;
    static int[][] dp;
    static int[][] delta = {{-1,0,1,0},{0,1,0,-1}};
    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        N = board.length;
        map = new int[N][N];
        dp = new int[N][N];
        for(int i = 0; i<N; ++i){
            for(int j = 0; j<N; ++j){
                map[i][j] = board[i][j];
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        findRoute(0, 0, 1, 1, 0);
        findRoute(0, 0, 2, 2, 0);
        
        return answer;
    }
    
    private static void findRoute(int row, int col, int dir, int bdir, int cost){
        if(dp[row][col] < cost){
            return;
        } else {
            dp[row][col] = cost;
        }
        
        if(row == N-1 && col == N-1){
            answer = Math.min(answer, cost);
            return;
        }
        
        if(cost >= answer){
            return;
        }
                
        for(int ndir = 0; ndir<4; ++ndir){
            int nr = row + delta[0][ndir];
            int nc = col + delta[1][ndir];
            if(inRange(nr,nc) && map[nr][nc] == 0){
                if(dir == ndir){
                    map[row][col] = 2;    
                    findRoute(nr,nc, ndir, dir, cost+100);
                } else {
                    map[row][col] = 3;
                    findRoute(nr,nc, ndir, dir, cost+600);
                }
                map[row][col] = 0;        
            }
        }
        
          
    }
    
    private static boolean inRange(int nr, int nc){
        if((nr>=0)&&(nr<N)&&(nc>=0)&&(nc<N)){
            return true;
        }
        return false;
    }
}
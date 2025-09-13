import java.util.*;

class Solution {
    // 기본 아이디어 : 플로이드-워셜
    // 갱신되는 시점 기록하여 계산
    static int[][] result;
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        result = new int[n+1][n+1];
        for(int i = 0; i<n+1; ++i){
            for(int j = 0; j<n+1; ++j){
                if(i != j){
                    result[i][j] = 987654321;
                }
            }    
        }
        for(int[] fare : fares){
            result[fare[0]][fare[1]] = fare[2];
            result[fare[1]][fare[0]] = fare[2];
        }
        
        Floyd(n);
        int answer = 987654321;
        
        for(int i =1; i<n+1; ++i){
            if(result[s][i]!=987654321 && result[i][a] != 987654321 && result[i][b]!= 987654321){
                answer = Math.min(answer, result[s][i]+result[i][a]+result[i][b]);    
            }
        }
        return answer;
    }
    
    private static void Floyd(int N){
        for(int k = 1; k<N+1; ++k){
            for(int i = 1; i<N+1; ++i){
                for(int j = 1; j<N+1; ++j){
                    result[i][j] = Math.min(result[i][j], result[i][k]+result[k][j]);
                }    
            }    
        }
    }
}
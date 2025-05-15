import java.util.*;

class Solution {
                 // 0,1,2,3,4,5,6,7,8,9
    int[][] map = {{1,7,6,7,5,4,5,3,2,3},
                   {7,1,2,4,2,3,5,4,5,6},
                   {6,2,1,2,3,2,3,5,4,5},
                   {7,4,2,1,5,3,2,6,5,4},
                   {5,2,3,5,1,2,4,2,3,5},
                   {4,3,2,3,2,1,2,3,2,3},
                   {5,5,3,2,4,2,1,5,3,2},
                   {3,4,5,6,2,3,5,1,2,4},
                   {2,5,4,5,3,2,3,2,1,2},
                   {3,6,5,4,5,3,2,4,2,1}};
    public int solution(String numbers) {
        // 3차원 배열 선언 현재의 숫자 idx / 왼손 / 오른손
        int[][][] dp = new int[numbers.length()+1][10][10];
        int INF = 987654321;
        for(int i = 0; i<=numbers.length(); ++i){
            for(int left = 0 ; left<10; ++left){
                for(int right = 0 ; right<10; ++right){
                    dp[i][left][right] = INF;
                }
            }
        }
        dp[0][4][6] = 0;
        int idx = 1;
        for(int i = 0; i<numbers.length(); ++i){
            int num = numbers.charAt(i) - '0';
            for(int left = 0 ; left<10; ++left){
                for(int right = 0 ; right<10; ++right){
                    if(dp[idx-1][left][right] != INF){
                        if(num == left){
                            dp[idx][left][right] = Math.min(dp[idx][left][right], dp[i][left][right]+1);
                        } else if(num == right){
                            dp[idx][left][right] = Math.min(dp[idx][left][right], dp[i][left][right]+1);
                        } else {
                            dp[idx][num][right] = Math.min(dp[idx][num][right], dp[i][left][right] + map[num][left]);
                            dp[idx][left][num] = Math.min(dp[idx][left][num], dp[i][left][right] + map[num][right]);
                        }
                    }
                }
            }
            ++idx;
        }
        int answer = INF;
        for(int left = 0 ; left<10; ++left){
            for(int right = 0 ; right<10; ++right){
                answer = Math.min(answer, dp[idx-1][left][right]);
            }
        }
        return answer;
    }
    
}
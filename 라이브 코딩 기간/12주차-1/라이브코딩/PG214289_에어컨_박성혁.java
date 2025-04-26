import java.util.*;

class Solution {
    
    // 기본아이디어 dp
    // 특정 온도를 유지하는 데 필요한 가장 적은 비용을 dp에 저장


    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int[][] dp = new int[onboard.length][51]; // 행은 시간 열은 온도 -10~40도의 범위를 갖는 문제임으로 51개의 열을 줌
        int INF = 987654321; // 기본적으로 INF로 셋팅
        for(int i = 0; i<onboard.length ; ++i ){
            Arrays.fill(dp[i], INF);
        }
        dp[0][temperature+10] = 0; // 0분 기준 실외 온도를 맞추기위한 비용은 0
        for(int i = 0; i<onboard.length-1 ; ++i){
            for(int j = 0; j<51; ++j){
                if(dp[i][j] != INF){
                    if(j == temperature+10){ // 실외온도랑 동일
                        dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                        if(onboard[i+1] == 1){ // 만약 손님이 있어야 하는 시간인데 적정온도 범위 밖이라면 비용을 다시 987654321로 초기화
                            if((j<t1+10)||(j>t2+10)){ 
                                dp[i+1][j] = INF;
                            }
                        }
                        if(j != 50) {
                            dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+a);
                            if(onboard[i+1] == 1){
                                if((j+1<t1+10)||(j+1>t2+10)){
                                    dp[i+1][j+1] = INF;
                                }
                            }
                        }
                        if(j != 0){
                            dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+a);
                            if(onboard[i+1] == 1){
                                if((j-1<t1+10)||(j-1>t2+10)){
                                    dp[i+1][j-1] = INF;
                                }
                            }
                        }
                    } else if (j > temperature+10){ // 실외온도보다 높을때
                        dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+b);
                        if(onboard[i+1] == 1){
                            if((j<t1+10)||(j>t2+10)){
                                dp[i+1][j] = INF;
                            }
                        }
                        if(j != 50) {
                            dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]+a);
                            if(onboard[i+1] == 1){
                                if((j+1<t1+10)||(j+1>t2+10)){
                                    dp[i+1][j+1] = INF;
                                }
                            }
                        }
                        if(j != 0){
                            dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
                            if(onboard[i+1] == 1){
                                if((j-1<t1+10)||(j-1>t2+10)){
                                    dp[i+1][j-1] = INF;
                                }
                            }
                        }    
                    } else { // 실외온도보다 낮을때
                        dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+b);
                        if(onboard[i+1] == 1){
                            if((j<t1+10)||(j>t2+10)){
                                dp[i+1][j] = INF;
                            }
                        }
                        if(j != 50) {
                            dp[i+1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
                            if(onboard[i+1] == 1){
                                if((j+1<t1+10)||(j+1>t2+10)){
                                    dp[i+1][j+1] = INF;
                                }
                            }
                        }
                        if(j != 0){
                            dp[i+1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]+a);
                            if(onboard[i+1] == 1){
                                if((j-1<t1+10)||(j-1>t2+10)){
                                    dp[i+1][j-1] = INF;
                                }
                            }
                        } 
                    }
                }
            }
        }
        int ans = 987654321;
        for(int i = 0; i<51 ; ++i){ // 최종 시각에서 가장 적은 비용 찾기
            ans = Math.min(ans,dp[onboard.length-1][i]);
        }
        return ans;
    }
}
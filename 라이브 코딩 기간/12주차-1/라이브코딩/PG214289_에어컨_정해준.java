public class PG214289_에어컨_정해준 {
    class Solution {
        public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {

            int N = onboard.length;
            //DP[시간][온도] = 최소비용
            int[][] dp = new int[N][51]; // -10 ~ 40에서 0 ~ 50으로 치환
            t1 += 10;
            t2 += 10;
            int max = 100 * 1000 + 1;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < 51; j++) {
                    dp[i][j] = max;
                }
            }
            temperature += 10;
            dp[0][temperature] = 0; // 처음은 실외온도
            int answer = max;
            for(int i = 0; i < N - 1; i++) {
                for(int j = 0; j < 51; j++) {
                    if((onboard[i] == 1 && j < t1) || (onboard[i] == 1 && j > t2))
                        continue; // 사람이 있는데 적정온도를 벗어남
                    //에어컨을 틀 경우
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + b);
                    if(j > 0) // 온도가 다를 경우 증가 혹은 감소
                        dp[i + 1][j-1] = Math.min(dp[i + 1][j - 1], dp[i][j] + a);
                    if(j < 50)
                        dp[i + 1][j+1] = Math.min(dp[i + 1][j + 1], dp[i][j] + a);


                    // 에어컨을 끌 경우
                    if(j == temperature) { // 실외 온도와 온도가 같은 경우
                        dp[i + 1][j] = Math.min(dp[i+1][j], dp[i][j]);
                    }
                    else if(j < 50 && j < temperature) { // 실외온도가 더 더울 경우
                        dp[i + 1][j+1] = Math.min(dp[i+1][j+1], dp[i][j]);
                    } else if(j > 0 && j > temperature) { // 실외온도가 더 추울 경우
                        dp[i + 1][j-1] = Math.min(dp[i+1][j-1], dp[i][j]);
                    }

                }
            }
            for(int i = 0; i < 51; i++) {
                if((onboard[N-1] == 1 && i < t1) || (onboard[N-1] == 1 && i > t2))
                    continue; // 사람이 있는데 적정온도를 벗어남
                answer = Math.min(answer, dp[N-1][i]);
            }
            return answer;
        }

    }
}

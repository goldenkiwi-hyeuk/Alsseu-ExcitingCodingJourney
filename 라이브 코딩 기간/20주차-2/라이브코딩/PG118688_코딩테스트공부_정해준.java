public class PG118688_코딩테스트공부_정해준 {
    class Solution {
        public int solution(int alp, int cop, int[][] problems) {
            int answer = 0;
            int endA = 0;
            int endC = 0;
            for(int i = 0; i < problems.length; i++) {
                endA = Math.max(problems[i][0], endA);
                endC = Math.max(problems[i][1], endC);
            }

            if(endA <= alp && endC <= cop)
                return 0; // 초기상태에서 이미 통과


            if(endA < alp)
                alp = endA;
            if(endC < cop)
                cop = endC;
            int dp[][] = new int[endA + 1][endC + 1];

            for(int i = alp; i <= endA; i++) {
                for(int j = cop; j <= endC; j++) {
                    dp[i][j] = 987654321;
                }
            }
            dp[alp][cop] = 0;
            for(int i = alp; i <= endA; i++) {
                for(int j = cop; j <= endC; j++) {
                    // 알고력 증가
                    if(i < endA)
                        dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                    // 코딩력 증가
                    if(j < endC)
                        dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                    // 문제 풀기
                    for(int[] p : problems) {
                        //풀 수 있을 경우
                        if(i >= p[0] && j >= p[1]) {
                            //이 문제를 풀 경우 목표치를 다 채울 경우
                            if(i + p[2] > endA && j + p[3] > endC) {
                                dp[endA][endC] = Math.min(dp[endA][endC],dp[i][j] + p[4]);
                            } else if(i + p[2] > endA) {
                                dp[endA][j + p[3]] = Math.min(dp[endA][j + p[3]],dp[i][j] + p[4]);
                            } else if(j + p[3] > endC) {
                                dp[i + p[2]][endC] = Math.min(dp[i + p[2]][endC],dp[i][j] + p[4]);
                            } else if(i + p[2] <= endA && j + p[3] <= endC) {
                                dp[i + p[2]][j+p[3]] = Math.min(dp[i + p[2]][j+p[3]], dp[i][j] + p[4]);
                            }
                        }
                    }



                }
            }
            return dp[endA][endC];
        }
    }
}

package programmers.코딩테스트공부;

import java.util.*;

class Solution {

    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;

        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 2][maxCop + 2];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                for (int[] problem : problems) {
                    int reqAlp = problem[0];
                    int reqCop = problem[1];
                    int rewardAlp = problem[2];
                    int rewardCop = problem[3];
                    int cost = problem[4];

                    if (i >= reqAlp && j >= reqCop) {
                        int nextAlp = Math.min(maxAlp, i + rewardAlp);
                        int nextCop = Math.min(maxCop, j + rewardCop);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}

package programmers.에어컨;

import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int n = onboard.length;
        final int range = 50;
        final int max = 100;
        final int INF = 987_654_321;

        int[][] dp = new int[n + 1][max + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][temperature + range] = 0;

        for (int t = 0; t < n; t++) {
            for (int temp = 0; temp <= max; temp++) {
                if (dp[t][temp] == INF) continue;

                int now = temp - range;

                int next = now;
                if (now < temperature) {
                    next++;
                } else if (now > temperature) {
                    next--;
                }

                if (onboard[t] == 0 || (t1 <= next && next <= t2)) {
                    dp[t + 1][next + range] = Math.min(dp[t + 1][next + range], dp[t][temp]);
                }

                for (int i = t1; i <= t2; i++) {
                    int nextTempOn = now;
                    if (now < i) {
                        nextTempOn++;
                    } else if (now > i) {
                        nextTempOn--;
                    }

                    int cost = (now == i) ? b : a;

                    if (onboard[t] == 0 || (t1 <= nextTempOn && nextTempOn <= t2)) {
                        dp[t + 1][nextTempOn + range] = Math.min(dp[t + 1][nextTempOn + range], dp[t][temp] + cost);
                    }
                }
            }
        }

        int answer = INF;
        for (int temp = 0; temp <= max; temp++) {
            int realTemp = temp - range;
            if (onboard[n - 1] == 0 || (t1 <= realTemp && realTemp <= t2)) {
                answer = Math.min(answer, dp[n][temp]);
            }
        }

        return answer;
    }
}

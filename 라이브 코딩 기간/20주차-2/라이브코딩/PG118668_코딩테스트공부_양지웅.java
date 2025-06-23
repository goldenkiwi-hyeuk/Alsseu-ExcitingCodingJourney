import java.util.*;

class Solution {
    private static final int INF = 1_000_000_000;

    public int solution(int alp, int cop, int[][] problems) {
        // 1. 필요한 최댓값 계산
        int maxAlp = alp, maxCop = cop;
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        // 2. dp[a][c] = 최소 시간, 기본값 INF
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[alp][cop] = 0;

        // 3. 모든 상태 탐색
        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                int curTime = dp[a][c];
                if (curTime == INF) continue;

                // 3-1) 알고력 공부
                if (a + 1 <= maxAlp)
                    dp[a + 1][c] = Math.min(dp[a + 1][c], curTime + 1);

                // 3-2) 코딩력 공부
                if (c + 1 <= maxCop)
                    dp[a][c + 1] = Math.min(dp[a][c + 1], curTime + 1);

                // 3-3) 풀 수 있는 문제
                for (int[] p : problems) {
                    int reqA = p[0], reqC = p[1], rwdA = p[2], rwdC = p[3], cost = p[4];

                    if (a >= reqA && c >= reqC) {
                        int na = Math.min(maxAlp, a + rwdA);
                        int nc = Math.min(maxCop, c + rwdC);
                        dp[na][nc] = Math.min(dp[na][nc], curTime + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
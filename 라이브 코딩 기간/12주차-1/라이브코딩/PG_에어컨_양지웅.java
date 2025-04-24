import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int n = onboard.length;
        final int INF = Integer.MAX_VALUE;
        // 온도 범위를 양수 범위로 조정
        int minT = Math.min(temperature, t1);
        int maxT = Math.max(temperature, t2);
        int rangeT = maxT - minT + 1;

        // 현재 시간, 온도에서의 소비전력
        int[][] dp = new int[n + 1][rangeT];
        for (int[] row : dp) Arrays.fill(row, INF);

        dp[0][temperature - minT] = 0;

        for (int time = 0; time < n; time++) {
            for (int tempIdx = 0; tempIdx < rangeT; tempIdx++) {
                int curPower = dp[time][tempIdx];
                if (curPower >= INF) continue;

                int curTemp = tempIdx + minT;

                // 1. 에어컨 OFF: 실외온도 방향으로 이동 (free)
                int offTemp = curTemp + Integer.signum(temperature - curTemp); // 부호 함수 활용
                if (isInRange(offTemp, minT, maxT) && isComfortable(onboard[time], offTemp, t1, t2)) {
                    dp[time + 1][offTemp - minT] = Math.min(dp[time + 1][offTemp - minT], curPower);
                }

                // 2. 에어컨 ON: 온도 변경 (a 비용)
                for (int nextTemp : new int[]{curTemp - 1, curTemp + 1}) {
                    if (isInRange(nextTemp, minT, maxT) && isComfortable(onboard[time], nextTemp, t1, t2)) {
                        dp[time + 1][nextTemp - minT] = Math.min(dp[time + 1][nextTemp - minT], curPower + a);
                    }
                }

                // 3. 에어컨 ON: 온도 유지 (b 비용)
                if (isComfortable(onboard[time], curTemp, t1, t2)) {
                    dp[time + 1][tempIdx] = Math.min(dp[time + 1][tempIdx], curPower + b);
                }
            }
        }

        // 최소 소비전력 찾기
        int answer = INF;
        for (int tempIdx = 0; tempIdx < rangeT; tempIdx++) {
            answer = Math.min(answer, dp[n][tempIdx]);
        }

        return answer;
    }

    // 전체 온도 범위 안인지 확인
    private boolean isInRange(int temp, int min, int max) {
        return min <= temp && temp <= max;
    }

    // 최적 온도인지 확인
    private boolean isComfortable(int onboard, int temp, int t1, int t2) {
        return onboard == 0 || (t1 <= temp && temp <= t2);
    }
}

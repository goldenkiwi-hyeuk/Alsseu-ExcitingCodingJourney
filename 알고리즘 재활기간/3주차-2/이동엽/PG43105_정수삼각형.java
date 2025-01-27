package programmers.정수삼각형;

class Solution {
    public int solution(int[][] triangle) {
        int len = triangle.length;

        if (len == 1) return triangle[0][0];
        if (len == 2) return triangle[0][0] + Math.max(triangle[1][0], triangle[1][1]);


        int[][] dp = new int[len][len];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }

        for (int i = 2; i < len; i++) {
            for (int j = 1; j < i; j++) {
                dp[i][j] = triangle[i][j] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }

        int max = -1;
        for (int i = 0; i < len; i++) {
            max = Math.max(dp[len - 1][i], max);
        }

        return max;
    }
}
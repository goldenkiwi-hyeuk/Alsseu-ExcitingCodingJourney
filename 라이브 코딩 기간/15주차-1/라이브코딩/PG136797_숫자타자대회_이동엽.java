package programmers.숫자타자대회;

import java.util.*;

class Solution {
    int[][] cost = new int[10][10];
    int[][][] dp;
    String nums;
    int len;

    public int solution(String numbers) {
        nums = numbers;
        len = numbers.length();
        dp = new int[len][10][10];
        for (int[][] matrix : dp)
            for (int[] row : matrix)
                Arrays.fill(row, -1);

        init();
        return dfs(0, 4, 6);
    }

    private int dfs(int idx, int left, int right) {
        if (idx == len) return 0;
        if (dp[idx][left][right] != -1) return dp[idx][left][right];

        int num = nums.charAt(idx) - '0';
        int moveLeft = (num != right) ? dfs(idx + 1, num, right) + cost[left][num] : Integer.MAX_VALUE;
        int moveRight = (num != left) ? dfs(idx + 1, left, num) + cost[right][num] : Integer.MAX_VALUE;

        return dp[idx][left][right] = Math.min(moveLeft, moveRight);
    }

    private void init() {
        int[][] pos = {
                {3, 1}, // 0
                {0, 0}, {0, 1}, {0, 2}, // 1 2 3
                {1, 0}, {1, 1}, {1, 2}, // 4 5 6
                {2, 0}, {2, 1}, {2, 2}  // 7 8 9
        };

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == j) {
                    cost[i][j] = 1;
                    continue;
                }
                int dx = Math.abs(pos[i][0] - pos[j][0]);
                int dy = Math.abs(pos[i][1] - pos[j][1]);
                int min = Math.min(dx, dy);
                int max = Math.max(dx, dy);
                int diff = dx + dy;
                cost[i][j] = (diff == 1) ? 2 : min * 3 + (max - min) * 2;
            }
        }
    }
}

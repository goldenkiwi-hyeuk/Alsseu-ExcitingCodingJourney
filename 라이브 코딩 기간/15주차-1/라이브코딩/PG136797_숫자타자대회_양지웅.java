import java.util.*;

class Solution {
    static int[][] numberpad = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {-1, 0, -1}
    };

    static Map<Integer, int[]> pos = new HashMap<>();
    static int[][] distance = new int[10][10];

    static {
        // 숫자 키패드 위치 매핑
        for (int r = 0; r < numberpad.length; r++) {
            for (int c = 0; c < numberpad[0].length; c++) {
                int num = numberpad[r][c];
                if (num != -1) {
                    pos.put(num, new int[]{r, c});
                }
            }
        }

        // 모든 숫자 쌍의 거리 미리 계산
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                distance[i][j] = calculate(i, j);
            }
        }
    }

    // 거리 계산
    static int calculate(int from, int to) {
        if (from == to) return 1;

        int[] p1 = pos.get(from);
        int[] p2 = pos.get(to);
        int rowDist = Math.abs(p1[0] - p2[0]);
        int colDist = Math.abs(p1[1] - p2[1]);

        if (rowDist != 0 && colDist != 0) {
            return 3 * Math.min(rowDist, colDist) + 2 * (Math.max(rowDist, colDist) - Math.min(rowDist, colDist));
        } else {
            return 2 * (rowDist + colDist);
        }
    }

    public int solution(String numbers) {
        int n = numbers.length();
        int[][][] dp = new int[n + 1][10][10];

        // dp 배열 초기화
        for (int[][] d1 : dp)
            for (int[] d2 : d1)
                Arrays.fill(d2, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[3]));
        pq.offer(new int[]{4, 6, 0, 0});  // left, right, index, cost
        dp[0][4][6] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int left = current[0];
            int right = current[1];
            int idx = current[2];
            int cost = current[3];

            // 모든 숫자 탐색 완료
            if (idx == n) return cost;

            int target = numbers.charAt(idx) - '0';

            // 왼손으로 target 누르기
            int newCost = cost + distance[left][target];
            if (target != right && newCost < dp[idx + 1][target][right]) {
                dp[idx + 1][target][right] = newCost;
                pq.offer(new int[]{target, right, idx + 1, newCost});
            }

            // 오른손으로 target 누르기
            newCost = cost + distance[right][target];
            if (target != left && newCost < dp[idx + 1][left][target]) {
                dp[idx + 1][left][target] = newCost;
                pq.offer(new int[]{left, target, idx + 1, newCost});
            }
        }

        return -1;
    }
}
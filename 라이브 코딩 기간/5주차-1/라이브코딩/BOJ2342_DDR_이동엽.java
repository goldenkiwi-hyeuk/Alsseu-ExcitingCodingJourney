package boj;

import java.util.*;

public class Main2342 {
    static int dp[][][], steps[], len;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();

        while (true) {
            int num = sc.nextInt();
            if (num == 0) break;
            list.add(num);
        }

        len = list.size();
        steps = new int[len];
        for (int i = 0; i < len; i++) {
            steps[i] = list.get(i);
        }

        dp = new int[len + 1][5][5];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        System.out.println(DDR(0, 0, 0));
    }

    static int DDR(int idx, int left, int right) {
        if (idx == len) return 0;
        if (dp[idx][left][right] != -1) return dp[idx][left][right];

        int next = steps[idx];
        int moveL = energy(left, next) + DDR(idx + 1, next, right);
        int moveR = energy(right, next) + DDR(idx + 1, left, next);

        return dp[idx][left][right] = Math.min(moveL, moveR);
    }

    static int energy(int from, int to) {
        if (from == 0) return 2;
        if (from == to) return 1;
        if (Math.abs(from - to) == 2) return 4;
        return 3;
    }
}
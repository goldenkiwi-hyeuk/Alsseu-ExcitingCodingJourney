package boj;

import java.util.*;

public class Main1943 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int tc = 3;
        while (tc-- > 0) {
            int n = sc.nextInt();
            int[] coin = new int[n];
            int[] count = new int[n];
            int sum = 0;

            for (int i = 0; i < n; i++) {
                coin[i] = sc.nextInt();
                count[i] = sc.nextInt();
                sum += coin[i] * count[i];
            }

            if (sum % 2 != 0) {
                System.out.println(0);
                continue;
            }

            int target = sum / 2;
            boolean[] dp = new boolean[100_001];
            dp[0] = true;

            for (int i = 0; i < n; i++) {
                int value = coin[i];
                int cnt = count[i];

                for (int j = target; j >= 0; j--) {
                    if (dp[j]) {
                        for (int k = 1; k <= cnt; k++) {
                            int next = j + k * value;
                            if (next > target) break;
                            dp[next] = true;
                        }
                    }
                }
            }

            System.out.println(dp[target] ? 1 : 0);
        }

        sc.close();
    }
}
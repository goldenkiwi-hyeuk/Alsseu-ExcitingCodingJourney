package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main3687 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        long[] dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);

        /*
        0: 6
        1: 2
        2: 5
        3: 5
        4: 4
        5: 5
        6: 6
        7: 3
        8: 7
        9: 6
        최솟값 -> dp
        최댓값 -> 1 붙여서 자릿수 늘리는게 가장 큼
        i) 홀수일 때, 711111...
        ii) 짝수일 때, 111111...
         */
        // 성냥개비로 만들 수 있는 최솟값
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;
        dp[7] = 8;
        dp[8] = 10;
        // 2 3 4 5 6 7 개로 만들 수 있는 숫자 최솟값
        String[] nums = {"", "", "1", "7", "4", "2", "0", "8"};

        for (int i = 9; i <= 100; i++) {
            for (int j = 2; j <= 7; j++) {
                if (dp[i - j] == Long.MAX_VALUE) continue;
                // j개를 써서 만들 수 있는 숫자 조합
                String str = String.valueOf(dp[i - j]) + nums[j];
                long num = Long.parseLong(str);
                dp[i] = Math.min(dp[i], num);
            }
        }

        while (t-- > 0) {
            int n = sc.nextInt();

            String smallest = String.valueOf(dp[n]);
            StringBuilder biggest = new StringBuilder();
            if (n % 2 == 1) {
                biggest.append("7");
                n -= 3;
            }
            while (n > 0) {
                biggest.append("1");
                n -= 2;
            }

            sb.append(smallest).append(" ").append(biggest).append("\n");
        }
        sc.close();
        System.out.println(sb);
    }
}

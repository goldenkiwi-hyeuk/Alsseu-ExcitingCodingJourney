//DP

import java.util.Scanner;

public class BOJ2240_자두나무 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int W = sc.nextInt();

        int[][] dp = new int[T+1][W+1];

        int answer = 0;

        for(int i = 1; i <= T; i++) {
            int now = sc.nextInt();

            for (int j = 0; j <= W; j++) {

                if (j == 0) {
                    if (now == 1) {  //위치가 같을 때
                        dp[i][j] = dp[i - 1][j] + 1;
                    } else { //다를 때
                        dp[i][j] = dp[i-1][j];
                    }
                    continue;
                }

                if (j % 2 == 0) { // 짝수번 이동하면 원래 위치로
                    if (now == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1]);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    }
                } else {
                    if (now == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
                    }
                }
            }
        }
        for(int i = 0; i <= W; i++){
            answer = Math.max(answer, dp[T][i]);
        }
        System.out.println(answer);
    }

}

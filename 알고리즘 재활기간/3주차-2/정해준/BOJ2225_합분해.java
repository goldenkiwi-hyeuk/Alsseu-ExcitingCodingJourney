import java.util.Scanner;

public class BOJ2225_합분해 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int p = 1000000000;

        int[][] dp = new int[K+1][N+1];
        for(int k = 1; k <= K; k++) {
            dp[k][0] = 1; // N이 0일 경우 0 을 k 번 더한 경우는 1번이 나온다
        }

        for(int k = 1; k <= K; k++) {
            for(int n = 1; n <= N; n++) {
                dp[k][n] = (dp[k-1][n] + dp[k][n-1]) % p;
            }
        }

        System.out.println(dp[K][N]);
    }
}

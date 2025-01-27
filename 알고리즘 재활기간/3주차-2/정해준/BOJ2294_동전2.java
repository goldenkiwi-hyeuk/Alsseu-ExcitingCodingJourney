import java.util.Scanner;

public class BOJ2294_동전2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] coins = new int[N+1];
        for(int i = 1; i <= N; i++) {
            coins[i] = sc.nextInt();
        }
        int[] dp = new int[K+1];
        for(int i = 1; i <= K; i++) {
            dp[i] = 987654321;
        }
        for(int i = 1; i <= N; i++) {
            for(int j = coins[i]; j <= K; j++) {
                dp[j] = Math.min(dp[j], dp[j-coins[i]] + 1);
            }
        }

        if(dp[K] == 987654321)
            System.out.println(-1);
        else
            System.out.println(dp[K]);

    }
}

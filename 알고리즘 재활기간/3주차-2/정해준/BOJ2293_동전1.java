import java.util.Scanner;

public class BOJ2293_동전1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] coins = new int[N+1]; // 코인의 종류
        for(int i = 1; i <= N; i++) {
            coins[i] = sc.nextInt();
        }
        int[] dp = new int[K+1]; // 각 금액별로 갯수 세기
        dp[0] = 1;
        for(int i = 1; i <= N; i++) {
            for(int j = coins[i]; j <= K; j++){
                dp[j] += dp[j-coins[i]];
            }
        }
        System.out.println(dp[K]);

    }
}

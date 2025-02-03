import java.util.Scanner;

public class BOJ1309_동물원 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] dp = new int[N+1][3];
        dp[1][0] = 1; // 사자가 없을 때
        dp[1][1] = 1;// 왼쪽
        dp[1][2] = 1; // 오른쪽

        for(int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
            dp[i][1] = (dp[i-1][2] + dp[i-1][0]) % 9901;
            dp[i][2] = (dp[i-1][1] + dp[i-1][0]) % 9901;
        }
        int answer = 0;
        for(int i : dp[N])
            answer += i;
        System.out.println(answer % 9901);
    }
}

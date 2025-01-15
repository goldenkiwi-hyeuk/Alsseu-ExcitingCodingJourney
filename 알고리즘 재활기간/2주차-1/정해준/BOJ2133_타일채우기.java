import java.util.Scanner;

public class BOJ2133_타일채우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 3*N의 타일 채우기

        int[] dp = new int[N + 1];

        dp[0] = 1; // base
        for(int i = 2; i <= N; i+=2){
            dp[i] = 3 * dp[i-2]; // 남은 두칸의 경우에 수만 생각할 경우, 기존 타일 문제

            for(int j = i - 4; j >= 0; j-=2){ // 연결 부분의 경우의 수를 곱해야 함.
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}

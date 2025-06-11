import java.util.Scanner;

public class BOJ2749_피보나치수3_정해준 {
    static int MOD = 1000000;
    static int Pisano = 15 * 100000;
    static long[] fibo;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();

        N %= Pisano; // 파시노 주기
        fibo = new long[(int) N + 1];

        fibo[0] = 0;
        fibo[1] = 1;

        for(int i = 2; i <= N; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % MOD;
        }

        System.out.println(fibo[(int) N]);

    }
}

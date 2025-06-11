package boj;

import java.util.Scanner;

/*
https://deepdata.tistory.com/759
k(10^n) = 15 * 10^(n-1)
 */

public class Main2749 {

    static final int MOD = 1_000_000;
    static final int PISANO = 1_500_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        sc.close();

        int idx = (int) (n % PISANO);
        int[] fibo = new int[PISANO];
        fibo[0] = 0;
        fibo[1] = 1;

        for (int i = 2; i <= idx; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % MOD;
        }

        System.out.println(fibo[idx]);
    }
}

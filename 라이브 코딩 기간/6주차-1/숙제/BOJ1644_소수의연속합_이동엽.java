package boj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main1644 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 1 <= n <= 4_000_000
        sc.close();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        boolean[] isPrime = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }

        int size = primes.size();
        int[] sum = new int[size + 1];

        for (int i = 1; i <= size; i++) {
            sum[i] = sum[i - 1] + primes.get(i - 1);
        }

        int cnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (sum[j + 1] - sum[i] == n) {
                    cnt++;
                } else if (sum[j + 1] - sum[i] > n) {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
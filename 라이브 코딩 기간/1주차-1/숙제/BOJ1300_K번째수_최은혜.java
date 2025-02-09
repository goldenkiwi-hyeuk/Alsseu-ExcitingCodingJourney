package boj_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // B[k] -> k번째로 작은 수 구하는 것.
        // 2차원 배열을 만들어서 정렬하면 시초 발생 -> 최대 (10^10)*log10^10의 연산 -> 이분탐색 필요

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long left = 1;
        long right = K; // 'K번째로 작은 수'는 K보다 클 수 없다.

        while (left < right) {
            long mid = (left + right) / 2;
            long count = 0;

            for (int i = 1; i < N + 1; i++) {
                count += Math.min(mid / i, N); // mid/i는 각 i행에 mid 이하의 수가 몇개 있는지 찾는것.
            }

            if (K <= count) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);

    }
}

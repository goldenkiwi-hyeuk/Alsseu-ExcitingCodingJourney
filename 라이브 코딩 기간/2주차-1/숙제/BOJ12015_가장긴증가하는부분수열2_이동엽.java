package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main12015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        /*
         (1 <= N <= 1,000,000)
         dp : N^2 = 1,000,000,000,000 이므로 X
         */
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        List<Integer> list = new ArrayList<>();
        for (int a : arr) {
            int idx = Collections.binarySearch(list, a); // 없으면 -삽입위치 -1 반환
            if (idx < 0) idx = -idx - 1;

            if (idx == list.size()) { // 맨 끝에 삽입 = 제일 큰 값
                list.add(a);
            } else {
                list.set(idx, a); // 더 작은 값으로 교체
            }
        }
        
        System.out.println(list.size());
    }
}

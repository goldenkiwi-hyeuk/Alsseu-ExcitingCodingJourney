package boj;

import java.util.*;

class Main7570 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 아이 3명 이상 이동시켜서 최소로 정렬
        int n = sc.nextInt(); // 1 <= n <= 1_000_000
        int[] line = new int[n];
        for (int i = 0; i < n; i++) {
            line[i] = sc.nextInt();
        }
        sc.close();

        // 1씩 증가하는 최대 길이 n에서 빼기
        int[] where = new int[n + 1];
        for (int i = 0; i < n; i++) {
            where[line[i]] = i;
        }

        int max = 1;
        int len = 1;
        for (int i = 2; i <= n ; i++) {
            if (where[i - 1] < where[i]) {
                len++;
                max = Math.max(max, len);
            } else {
                len = 1;
            }
        }

        System.out.println(n - max);
    }
}
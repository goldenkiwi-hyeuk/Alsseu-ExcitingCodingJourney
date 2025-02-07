package boj;

import java.util.Scanner;

public class Main1300 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*
        n = 3   n = 4
        1 2 3   1 2 3 4
        4 5 6   2 4 6 8
        7 8 9   3 6 9 12
                4 8 12 16
        B배열에 넣고 정렬 -> O(N^2*LogN) -> 펑
        각 행간, 열간은 배수관계
         */

        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.close();

        int ans = 0;
        int l = 1, r = n * n;
        while (l <= r) {
            int m = (l + r) / 2;
            long cnt = 0; // 갯수 새기

            // 갯수를 어떻게 셀지 ......

            if (cnt >= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        System.out.println(ans);
    }
}

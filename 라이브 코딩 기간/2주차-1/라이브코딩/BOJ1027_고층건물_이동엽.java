package boj;

import java.util.Scanner;

class Main1027 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // (1 <= n <= 50)
        int[] buildings = new int[n];
        for (int i = 0; i < n; i++) buildings[i] = sc.nextInt();
        sc.close();

        int max = 0;

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            // 왼쪽
            double beforeL = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                double degree = (double) (buildings[j] - buildings[i]) / (j - i);
                if (degree < beforeL) {
                    cnt++;
                    beforeL = degree;
                }
            }

            // 오른쪽
            double beforeR = Integer.MIN_VALUE;
            for (int j = i + 1; j < n; j++) {
                double degree = (double) (buildings[j] - buildings[i]) / (j - i);
                if (degree > beforeR) {
                    cnt++;
                    beforeR = degree;
                }
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }
}
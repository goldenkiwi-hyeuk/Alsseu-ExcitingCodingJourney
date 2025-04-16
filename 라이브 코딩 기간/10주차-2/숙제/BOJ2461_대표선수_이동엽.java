package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main2461 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] school = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                school[i][j] = sc.nextInt();
            }
            Arrays.sort(school[i]);
        }
        sc.close();

        int[] index = new int[n];
        int diff = Integer.MAX_VALUE;

        while (true) {
            int min = school[0][index[0]], max = school[0][index[0]];
            int minIdx = 0;

            for (int i = 1; i < n; i++) {
                if (min > school[i][index[i]]) {
                    min = school[i][index[i]];
                    minIdx = i;
                }
                max = Math.max(max, school[i][index[i]]);
            }
            
            diff = Math.min(diff, max - min);
            // 가장 작은 점수 가진 학급 포인터 이동
            if (++index[minIdx] == m) break;
        }

        System.out.println(diff);
    }
}

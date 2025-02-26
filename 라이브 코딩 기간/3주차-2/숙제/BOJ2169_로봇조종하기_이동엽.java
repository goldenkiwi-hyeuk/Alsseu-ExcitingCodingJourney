package boj;

import java.util.Scanner;

public class Main2169 {

    static int n, m, mars[][], dp[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*
        다시 방문 X => dp + dfs 사용했었는데 틀림.
        메모이제이션 (bottom up) 사용
        dp 배열 첫줄 : 무조건 -> 방향
        왼쪽/오른쪽 or 위에서 오는 경우를 배열에 저장 후 max
        */
        n = sc.nextInt();
        m = sc.nextInt();
        mars = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mars[i][j] = sc.nextInt();
            }
        }
        sc.close();

        marsian();
        System.out.println(dp[n - 1][m - 1]);
    }

    static void marsian() {
        dp[0][0] = mars[0][0];

        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + mars[0][j];
        }

        for (int i = 1; i < n; i++) {
            int[] left = new int[m];
            int[] right = new int[m];

            left[0] = dp[i - 1][0] + mars[i][0];
            for (int j = 1; j < m; j++) {
                left[j] = Math.max(left[j - 1], dp[i - 1][j]) + mars[i][j];
            }

            right[m - 1] = dp[i - 1][m - 1] + mars[i][m - 1];
            for (int j = m - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1], dp[i - 1][j]) + mars[i][j];
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }
    }
}

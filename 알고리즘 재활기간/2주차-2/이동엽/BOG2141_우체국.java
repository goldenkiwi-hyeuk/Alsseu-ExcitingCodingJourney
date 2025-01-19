package boj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main2141 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long[][] vills = new long[n][2];
        for (int i = 0; i < n; i++) {
            vills[i][0] = sc.nextLong();
            vills[i][1] = sc.nextLong();
        }
        sc.close();

        Arrays.sort(vills, Comparator.comparingLong(o -> o[0]));

        long total = 0;
        for (int i = 0; i < n; i++) total += vills[i][1];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += vills[i][1];
            if (2 * sum >= total) {
                System.out.println(vills[i][0]);
                break;
            }
        }
    }
}

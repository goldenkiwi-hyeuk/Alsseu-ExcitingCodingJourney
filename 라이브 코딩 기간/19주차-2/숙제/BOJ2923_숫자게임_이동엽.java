package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main2923 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[100];
        int[] brr = new int[100];

        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a]++;
            brr[b]++;

            int[] cArr = arr.clone();
            int[] cBrr = brr.clone();
            int i = 1;
            int j = 99;
            int max = 0;

            while (i <= 99 && j >= 1) {
                if (cArr[i] == 0) {
                    i++;
                    continue;
                }

                if (cBrr[j] == 0) {
                    j--;
                    continue;
                }
                max = Math.max(max, i + j);

                int min = Math.min(cArr[i], cBrr[j]);
                cArr[i] -= min;
                cBrr[j] -= min;

                if (cArr[i] == 0) i++;
                if (cBrr[j] == 0) j--;

            }
            sb.append(max).append("\n");
        }
        sc.close();
        System.out.println(sb);
    }
}
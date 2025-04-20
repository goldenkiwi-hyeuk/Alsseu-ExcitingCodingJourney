package boj;

import java.io.*;
import java.util.*;

public class Main14003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int[] d = new int[n]; // 마지막 값 저장
        int[] idx = new int[n]; // d 인덱스 저장
        int[] prev = new int[n]; // 복원용

        int len = 0;
        for (int i = 0; i < n; i++) {
            int x = a[i];
            int bs = Arrays.binarySearch(d, 0, len, x);
            if (bs < 0) bs = -(bs + 1);

            d[bs] = x;
            idx[bs] = i;
            prev[i] = (bs > 0) ? idx[bs - 1] : -1;

            if (bs == len) len++;
        }

        int[] lis = new int[len];
        int k = idx[len - 1];
        for (int i = len - 1; i >= 0; i--) {
            lis[i] = a[k];
            k = prev[k];
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len).append("\n");
        for (int l : lis) sb.append(l).append(" ");

        System.out.println(sb);
    }
}

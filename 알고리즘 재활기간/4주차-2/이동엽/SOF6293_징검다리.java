package softeer.징검다리;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] stones = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) stones[i] = Integer.parseInt(st.nextToken());

         int[] dp = new int[n];
         for (int i = 0; i < n; i++) dp[i] = 1;

         int cnt = 1;
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < i; j++) {
                 if (stones[j] < stones[i]) {
                     dp[i] = Math.max(dp[i], dp[j] + 1);
                 }
             }
             cnt = Math.max(cnt, dp[i]);
         }
         System.out.println(cnt);

    }
}
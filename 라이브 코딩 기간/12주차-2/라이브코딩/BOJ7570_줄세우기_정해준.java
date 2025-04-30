// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.io.*;

public class BOJ7570_줄세우기_정해준 {// Online Java Compiler
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] dp = new int[N + 1];
            int max = 0;
            st = st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                dp[n] = dp[n - 1] + 1;
                max = Math.max(dp[n], max);
            }
            System.out.println(N - max);
        }

}

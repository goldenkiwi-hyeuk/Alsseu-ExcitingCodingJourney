import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        dp[0] = 1;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        for(int i=1; i<n; i++){
            dp[i] = 1;
            for(int j=0; j<i; j++){
                if(arr[i]>arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }


        int result = 0;
        for(int i=0; i<n; i++){
            result = Math.max(result, dp[i]);
        }



        System.out.println(result);
    }

}
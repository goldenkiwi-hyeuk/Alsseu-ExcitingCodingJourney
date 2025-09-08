import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int N = str.length();
        int[][] dp = new int[N][N];
        int mod = 10007;
        // 한자리 글자는 무조건 팰린드롬
        for(int i = 0; i<N; ++i){
            dp[i][i] = 1;
        }
        
        // 두자리 글자는 서로 같다면 팰린드롬 3
        // 다르다면 2
        for(int left = 0; left<N-1 ; ++left){
            int right = left + 1;
            if(str.charAt(left) == str.charAt(right)){
                dp[left][right] = 3;    
            } else {
                dp[left][right] = 2;    
            }
        }
        
        // 이후부터는 같다면
        // dp[left][right] = 오른쪽 포함하는 팰린드롬 + 왼쪽 포함하는 팰린드롬 + 1
        // 다르다면
        // dp[left][right] = 오른쪽 포함하는 팰린드롬 + 왼쪽 포함하는 팰린드롬 - 왼쪽오른쪽 모두 포함하는 팰린드롬
        for(int len = 3; len<=N; ++len){
            for(int left = 0; left+len<=N ; ++left){
                int right = left+len -1;
                if(str.charAt(left) == str.charAt(right)){
                    dp[left][right] = (dp[left+1][right] + dp[left][right-1] + 1)%mod;    
                } else {
                    dp[left][right] = (dp[left+1][right] + dp[left][right-1] - dp[left+1][right-1])%mod;
                    // 마이너스 모듈러 연산은 마이너스시 mod 더하기
                    if(dp[left][right]<0){
                        dp[left][right] += mod;
                    }
                }
            }
        }
               
        System.out.println(dp[0][N-1]);
    }
}
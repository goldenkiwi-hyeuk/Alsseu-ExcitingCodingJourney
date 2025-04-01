import java.io.*;
import java.util.*;

class Main {
    
    // 기본 아이디어 : 2중 for문을 활용한 1차원 dp 
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N;++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+1];
        
        for(int i = 1; i<=N;++i){
            for(int j = arr[i]; j<=N;++j){ // 연결가능한 지점부터 뒤로 돌면서
                if(j == arr[i]){ // 연결 가능한 지점이라면 그 지점 전값 + 1
                    dp[j] = dp[j-1]+1; 
                } else { // 연결 가능한 지점의 뒤라면 앞과 현재 값중 큰 값 저장
                    dp[j] = Math.max(dp[j-1], dp[j]);
                }
            }
        }
        System.out.println(dp[N]);
    }
}
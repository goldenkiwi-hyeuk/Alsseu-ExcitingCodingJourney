import java.io.*;
import java.util.*;

class Main {
    // 기본아이디어 : dp
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N ; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[N+1]; // dp의 인덱스는 해당 수를 포함한 연속하는 증가하는 부분수열의 길이를 의미한다.
        int max = 0;
        for(int i = 0; i < N ; ++i){
            dp[arr[i]] = dp[arr[i]-1]+1; // dp[arr[i]]는 이전수의 +1이다.
            max = Math.max(dp[arr[i]],max);
        }
        System.out.println(N-max); // 연속한 수를 제외한 나머지 수를 움직이면 정렬가능하다.
    }
}
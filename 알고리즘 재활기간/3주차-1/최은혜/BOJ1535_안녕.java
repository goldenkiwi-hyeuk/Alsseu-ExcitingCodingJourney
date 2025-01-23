import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] L = new int[N+1]; // 체력
        int[] J = new int[N+1]; // 기쁨

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            L[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++){
            J[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][100]; // dp[i][j]는 i번째까지 고려하고 남은 체력이 j일때, 얻을 수 있는 최대 기쁨값

        for(int i=1; i<N+1; i++){
            for(int j=1; j<100; j++){

                dp[i][j] = dp[i-1][j];

                // i번째 사람과 인사한다면
                if(L[i]<= j){
                    dp[i][j] = Math.max(dp[i-1][j-L[i]]+J[i], dp[i][j]); // i-1까지의 결과에서 현재 체력 빼고, 현재 기쁨 더함.
                }
            }
        }

        System.out.println(dp[N][99]);



    }

}

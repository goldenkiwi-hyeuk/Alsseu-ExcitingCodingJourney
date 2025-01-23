import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            int N = Integer.parseInt(br.readLine()); // 동전의 개수

            int[] coin = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for(int i=1; i<N+1; i++){
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int target = Integer.parseInt(br.readLine()); // 목표 금액

            int[][]dp = new int[N+1][target+1]; // dp[i][j] 는, i번째 인덱스까지 탐색했을때, j원을 만들 수 있는 경우의 수
            for(int i=0; i<N+1; i++){
                dp[i][0] = 1;
            }



            for(int i=1; i<N+1; i++){
                for(int j=1; j<target+1; j++){
                    dp[i][j] = dp[i-1][j];
                    if(j==coin[i]){
                        dp[i][j]++;
                    } else if(j>coin[i]){
                        dp[i][j] += dp[i][j-coin[i]];
                    }
                }
            }

            System.out.println(dp[N][target]);





        }

    }
}

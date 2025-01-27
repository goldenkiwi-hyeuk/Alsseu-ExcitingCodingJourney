import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 단원 개수
        int T = Integer.parseInt(st.nextToken()); // 시험 시간

        int[] subject = new int[N+1];
        int[] score = new int[N+1];

        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            subject[i] = Integer.parseInt(st.nextToken());
            score[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N+1][T+1];

        for(int i=1; i<N+1; i++){
            for(int j=1; j<T+1; j++){
                dp[i][j] = dp[i-1][j];
                if(subject[i]<=j){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-subject[i]]+score[i]);
                }
            }
        }

        System.out.println(dp[N][T]);

    }
}

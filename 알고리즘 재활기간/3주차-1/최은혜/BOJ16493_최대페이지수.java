import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] day;
    static int[] page;
    static Integer[][] dp;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 남은 N일
        M = Integer.parseInt(st.nextToken()); // 챕터의 수

        day = new int[M+1];
        page = new int[M+1];

        for(int i=1; i<M+1; i++){
            st = new StringTokenizer(br.readLine());

            day[i] = Integer.parseInt(st.nextToken());
            page[i] = Integer.parseInt(st.nextToken());
        }

        dp = new Integer[M+1][N+1]; // dp[i][j]는 i번까지 고려했을때 소요한 j일동안 읽을 수 있는 페이지의 최대값


        System.out.println(solve(M,N));


    }

    public static int solve(int idx, int remainDays){
        if(idx==0 || remainDays==0){
            return 0;
        }

        if(dp[idx][remainDays] != null){
            return dp[idx][remainDays];
        }

        dp[idx][remainDays] = solve(idx-1,remainDays);


        if(day[idx]<=remainDays){
            dp[idx][remainDays] = Math.max(solve(idx-1,remainDays-day[idx])+page[idx],dp[idx][remainDays]);
        }

        return dp[idx][remainDays];
    }
}

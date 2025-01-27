import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 주문의 수
        int M = Integer.parseInt(st.nextToken()); // 치즈버거 개수.
        int K = Integer.parseInt(st.nextToken()); // 감자튀김 개수

        int[][] order = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            order[i][0] = Integer.parseInt(st.nextToken()); // 치즈버거
            order[i][1] = Integer.parseInt(st.nextToken()); // 감자튀김
        }

        int[][][] dp = new int[N + 1][M + 1][K + 1]; // i번째까지 고려했을때, 치즈버거 j개 감자튀김 k개를 사용해서 받을 수 있는 최대 주문의 수

        for (int i = 1; i < N + 1; i++) {

            for (int j = 0; j < M+1; j++) {
                for (int k = 0; k < K+1; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];


                    if (order[i-1][0] <= j && order[i-1][1] <= k) {
                        int burger = order[i-1][0];
                        int fries = order[i-1][1];

                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j-burger][k-fries] + 1);
                    }
                }
            }
        }

        System.out.println(dp[N][M][K]);


    }

}


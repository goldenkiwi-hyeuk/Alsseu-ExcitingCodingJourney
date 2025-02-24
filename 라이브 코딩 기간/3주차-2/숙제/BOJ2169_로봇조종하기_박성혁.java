import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 2차원 dp
    // 첫번째 행은 0번 인덱스부터 계속 더해나감 (Why? 1,1에서 시작하므로 첫번째 행의 선택지는 오른쪽으로 가는것 밖에 없음)
    // 두번째 행부터 마지막 행까지 두번의 탐색을 한다
    // 1. 왼쪽부터 오른쪽으로 가면서 위와 왼쪽중 큰값에 현재값을 더하는 것
    // 2. 오른쪽부터 왼쪽으로 가면서 위와 오른쪽중 큰값에 현재값을 더하는 것
    // 3. 두 탐색값중 큰 값을 dp에 넣는다.

    static int N,M;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0]; // 첫째줄 탐색
        for (int i = 1; i < M; i++) { // 첫번째 줄에서의 탐색은 오른쪽으로 가는것 밖에 없음
            dp[0][i] = map[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < N; i++) { // 두번째 줄부터는 탐색을 두번 시행
            int[] arr1 = new int[M]; // 왼쪽에서 오른쪽으로 가는 탐색
            arr1[0] = dp[i-1][0] + map[i][0]; 
            for (int j = 1; j < M; j++) {
                arr1[j] = map[i][j] + Math.max(arr1[j - 1], dp[i-1][j]);
            }
            int[] arr2 = new int[M]; // 오른쪽에서 왼쪽으로 가는 탐색
            arr2[M-1] = dp[i-1][M-1] + map[i][M-1];
            for (int j = M-2; j >=0 ; j--) {
                arr2[j] = map[i][j] + Math.max(arr2[j + 1], dp[i-1][j]);
            }
            for (int j = 0; j<M; ++j){ // 최종 dp에는 arr1과 arr2중 큰값 대입
                dp[i][j] = Math.max(arr2[j], arr1[j]);
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}

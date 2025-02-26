import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2169_로봇조정하기_정해준 {
    static int[] dx = {1,0,0};
    static int[] dy = {0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];

        // 화성의 정보를 등록
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = sc.nextInt();
            }
        }
        dp[0][0] = map[0][0];
        for(int i = 1; i < M; i++){ // 첫째줄 계산
            dp[0][i] = dp[0][i-1] + map[0][i];
        }

        int[][] dir = new int[2][M]; //왼쪽에서 시작, 오른쪽에서 시작 두 종류의 합을 구함

        for(int i = 1; i < N; i++) {
            dir[0][0] = dp[i-1][0] + map[i][0];
            dir[1][M-1] = dp[i-1][M-1] + map[i][M-1];

            for(int j = 1; j < M; j++) { // 왼쪽과 윗쪽을 탐색
                dir[0][j] = Math.max(dir[0][j-1], dp[i-1][j]) + map[i][j];
            }
            for(int j = M-2; j >= 0; j--) { // 오른쪽과 윗쪽을 탐색
                dir[1][j] = Math.max(dir[1][j+1], dp[i-1][j]) + map[i][j];
            }

            //두 갈래에서 더한 값의 더 큰값을 dp에 저장
            for(int j = 0; j < M; j++) {
                dp[i][j] = Math.max(dir[0][j], dir[1][j]);
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}

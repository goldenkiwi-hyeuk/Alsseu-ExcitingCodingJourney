import java.util.Scanner;

public class BOJ2098_외판원순회_정해준 {
    // 외판원 알고리즘 참고
    static int N;
    static int[][] map;
    static int[][] dp;
    static int max = 987654321;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        dp = new int[1 << N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < (1<<N); i++) {
            for(int j = 0; j < N; j++) {
                dp[i][j] = -1; // 초기화
            }
        }

        System.out.println(dfs(1,0));
    }

    public static int dfs(int idx, int now) {
        if(idx == (1 << N) - 1) {
            if(map[now][0] == 0) {
                return max;
            }
            return map[now][0];
        }
        if(dp[idx][now] != -1) { // 이미 방문했을 경우
            return dp[idx][now];
        }

        int min = max;
        for(int i = 0; i < N; i++) {
            if((idx & (1 << i)) == 0 && map[now][i] != 0) {
                int next = idx | (1 << i);
                int cost = dfs(next, i) + map[now][i];
                min = Math.min(min, cost);
            }
        }

        return dp[idx][now] = min;
    }
}

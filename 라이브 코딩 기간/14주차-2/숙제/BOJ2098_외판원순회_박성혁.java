import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 987654321;
    static int N;
    static int[][] dist;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int maxMask = 1 << N;
        dp = new int[maxMask][N];
        // 모든 상태를 무한대로 초기화
        for (int mask = 0; mask < maxMask; mask++) {
            Arrays.fill(dp[mask], INF);
        }
        // 시작 상태: 도시 0만 방문
        dp[1][0] = 0;
        // DP 전이
        for (int mask = 1; mask < maxMask; mask++) {
            for (int last = 0; last < N; last++) {
                // u가 mask에 포함되어 있지 않거나, 도달 불가 상태면 skip
                if ((mask & (1 << last)) == 0 || dp[mask][last] == INF) continue;
                // 다음 방문 도시 v 선택
                for (int v = 0; v < N; v++) {
                    if ((mask & (1 << v)) != 0) continue;    // 이미 방문한 도시
                    if (dist[last][v] == 0) continue;              // 경로가 없는 경우
                    int nextMask = mask | (1 << v);
                    dp[nextMask][v] = Math.min(dp[nextMask][v], dp[mask][last] + dist[last][v]);
                }
            }
        }
        // 모든 도시를 방문한 후 다시 0으로 돌아오는 비용 계산
        int fullMask = maxMask - 1;
        int answer = INF;
        for (int u = 1; u < N; u++) {
            if (dist[u][0] == 0) continue;  // 돌아가는 길이 없으면 skip
            answer = Math.min(answer, dp[fullMask][u] + dist[u][0]);
        }
        System.out.println(answer);
    }
}

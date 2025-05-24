// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.io.*;

class BOJ2533_사회망서비스(SNS)_정해준 {
    static int N;
    static int[][] dp;
    static ArrayList<Integer>[] edgeList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        visited = new boolean[N + 1];
        edgeList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            edgeList[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edgeList[start].add(to);
            edgeList[to].add(start);
        }
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    public static void dfs(int idx) {
        visited[idx] = true;
        dp[idx][0] = 1; // 자신이 얼리어 답터이라고 할 때 자신인 1 
        for(int next : edgeList[idx]) {
            if(visited[next])
                continue;
            dfs(next); // 자식 노드들을 다 확인해야 되기 때문 
            dp[idx][1] += dp[next][0]; //만약 자신이 얼리어 답터가 아니라면 자식이 얼리어 답터 여야 함 

            // 자신이 얼리어 답터라면 자식은 상관이 없기 때문에 더 작은 것을 선택 
            dp[idx][0] += Math.min(dp[next][0], dp[next][1]);

        }
    }
}
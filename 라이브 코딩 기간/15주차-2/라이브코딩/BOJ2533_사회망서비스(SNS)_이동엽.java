package boj;

import java.util.*;

class Main2533 {

    static int n, dp[][];
    static List<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) {
        // 얼리 어답터X -> 모든 친구가 O일때 수용
        // 가능한 최소 -> DP?
        // 친구 관계 그래프 트리
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            tree[u].add(v);
            tree[v].add(u);
        }
        sc.close();

        dfs(1);
        int ans = Math.min(dp[1][0], dp[1][1]);

        System.out.println(ans);
    }

    static void dfs(int r) {
        visited[r] = true;
        dp[r][0] = 0;
        dp[r][1] = 1;

        for (int t : tree[r]) {
            if (!visited[t]) {
                dfs(t);
                dp[r][0] += dp[t][1]; // 부모 eaX -> 자식 무조건 ea
                dp[r][1] += Math.min(dp[t][0], dp[t][1]);
            }
        }

    }

}
import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.*;

public class BOJ20303_할로윈의양아치_정해준 {
    static int N, M, K;
    static int[] candies;
    static int[] parent;
    static class Node {
        int cost;
        long value;

        Node(int cost, long value) {
            this.cost = cost;
            this.value = value;
        }
    }
    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b)
            return false;
        if(a <= b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return true;
    }

    static int find(int x) {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        candies = new int[N + 1];
        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            candies[i] = sc.nextInt();
            parent[i] = i;
        }

        for(int i = 1; i <= M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            union(a, b);
        }

        long[][] group = new long[N + 1][2];
        // 한명을 뽑았을 때 총 몰리게 되는 갯수와 사람을 정리
        for(int i = 1; i <= N; i++) {
            int groupId = find(parent[i]);
            group[groupId][0]++; // 그룹의 인원수 증가
            group[groupId][1] += candies[i]; // 그룹의 사람수 증가
        }


        List<Node> list = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(parent[i] == i) { // 그 그룹의 우두머리만 저장 (나머지 딸려오는 값은 무시)
                list.add(new Node((int)group[i][0], group[i][1]));
            }
        }

        Collections.sort(list, ((o1,o2)-> o1.cost - o2.cost)); // 사람을 적게 소모하는 사람들로 정렬
        long[][] dp = new long[list.size() + 1][K];

        for (int i = 1; i <= list.size(); i++) {
            for (int j = 0; j < K; j++) {
                if (list.get(i - 1).cost > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - list.get(i - 1).cost] + list.get(i - 1).value, dp[i - 1][j]);
                }
            }
        }
        System.out.println(dp[list.size()][K - 1]);
    }
}

package boj;

import java.util.*;

public class Main2623 {
    public static void main(String[] args) {
        /*
        방향O, 순서O -? 위상정렬,,?
         */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 1 <= N <= 1000
        int m = sc.nextInt(); // 1 <= M <= 100

        List<Integer>[] singer = new ArrayList[n + 1];
        int[] ins = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            singer[i] = new ArrayList<>();
        }

        while (m-- > 0) {
            int assigned = sc.nextInt();
            int before = sc.nextInt();

            for (int j = 1; j < assigned; j++) {
                int next = sc.nextInt();
                singer[before].add(next);
                ins[next]++;
                before = next;
            }
        }
        sc.close();

        List<Integer> ans = getSinger(n, singer, ins);
        StringBuilder sb = new StringBuilder();
        if (ans.isEmpty()) sb.append("0").append("\n");
        else {
            for (int a : ans) {
                sb.append(a).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static List<Integer> getSinger(int n, List<Integer>[] singer, int[] ins) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (ins[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);

            for (int g : singer[now]) {
                ins[g]--;
                if (ins[g] == 0) q.add(g);
            }
        }
        if (result.size() != n) return new ArrayList<>();

        return result;
    }

}

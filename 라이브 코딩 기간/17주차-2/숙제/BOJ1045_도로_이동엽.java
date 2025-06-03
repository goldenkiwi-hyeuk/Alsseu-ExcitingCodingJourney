package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main1045 {

    static int[] parent;
    static List<int[]> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        parent = new int[50];
        list = new ArrayList<>();
        int[] ans = new int[50];
        Arrays.fill(ans, 0);
        int cnt = 0;

        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            String str = sc.next();

            for (int j = i + 1; j < n; j++) {
                if (str.charAt(j) == 'Y') {
                    if (find(i) != find(j)) {
                        union(i, j);
                        cnt++;
                        ans[i]++;
                        ans[j]++;
                    } else {
                        list.add(new int[]{i, j});
                    }
                }
            }
        }

        if (cnt != n - 1) {
            System.out.println(-1);
            return;
        }

        for (int i = n - 1; i < m; i++) {
            if (list.isEmpty()) {
                System.out.println(-1);
                return;
            }

            int[] l = list.remove(0);
            ans[l[0]]++;
            ans[l[1]]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(ans[i] + " ");
        }

        System.out.println(sb);
    }

    public static int find(int node) {
        if (node == parent[node]) return node;
        return parent[node] = find(parent[node]);
    }

    public static void union(int a, int b) {
        parent[find(b)] = find(a);
    }
}
package boj;

import java.util.Scanner;

public class Main11505 {

    static int n, m, k;
    static final int mod = 1_000_000_007;
    static long[] arr, tree;
    /*
       일반적인 배열 구간 곱: O(N)
       세그먼트 트리 O(logN)
       이진 완전트리의 형태로 2의 거듭제곱 꼴이 편리함
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        arr = new long[n + 1];
        int treeSize = 1;
        while (treeSize < n) treeSize *= 2;
        treeSize *= 2;
        tree = new long[treeSize];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextLong();
        }

        init(1, 1, n);

        int queries = m + k;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < queries; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            if (a == 1) {
                update(1, 1, n, b, c);
            } else if (a == 2) {
                sb.append(query(1, 1, n, b, (int) c)).append("\n");
            }
        }
        sc.close();
        System.out.print(sb);
    }

    // 초기화: O(N)
    static long init(int node, int start, int end) {
        // 리프 노드
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = (init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end)) % mod;
    }

    // 값 업데이트 O(logN)
    static long update(int node, int start, int end, int index, long newValue) {
        // 범위 밖 무시
        if (index < start || index > end) return tree[node];
        // 리프 노드 업데이트
        if (start == end) return tree[node] = newValue;
        int mid = (start + end) / 2;
        return tree[node] = (update(node * 2, start, mid, index, newValue) * update(node * 2 + 1, mid + 1, end, index, newValue)) % mod;
    }

    // 구간 곱 O(logN)
    static long query(int node, int start, int end, int left, int right) {
        // 범위 벗어나면 1
        if (left > end || right < start) return 1;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return (query(node * 2, start, mid, left, right) * query(node * 2 + 1, mid + 1, end, left, right)) % mod;
    }
}

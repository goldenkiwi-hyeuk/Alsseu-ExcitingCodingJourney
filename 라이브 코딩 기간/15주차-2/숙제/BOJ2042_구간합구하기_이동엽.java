package boj;

import java.util.Scanner;

public class Main2042 {

    static int n, m, k;
    static long[] arr, tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        int size = n;
        arr = new long[size + 1];
        tree = new long[size * 4];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextLong();
        }

        init(1, 1, n);

        int total = m + k;
        for (int i = 0; i < total; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();

            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, 1, n, b, diff);
            } else if (a == 2) {
                System.out.println(sum(1, 1, n, b, (int) c));
            }
        }

        sc.close();
    }

    static long init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid)
                + init(node * 2 + 1, mid + 1, end);
    }

    static void update(int node, int start, int end, int index, long diff) {
        if (index < start || index > end) return;

        tree[node] += diff;

        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, index, diff);
            update(node * 2 + 1, mid + 1, end, index, diff);
        }
    }

    static long sum(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0;

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right)
                + sum(node * 2 + 1, mid + 1, end, left, right);
    }
}

package boj;

import java.util.Scanner;
import java.util.Arrays;

public class Main2357 {
    static int[] arr, minArr, maxArr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int size = 1;
        while (size < n) {
            size *= 2;
        }

        minArr = new int[size * 2];
        maxArr = new int[size * 2];

        Arrays.fill(minArr, Integer.MAX_VALUE);
        Arrays.fill(maxArr, Integer.MIN_VALUE);

        initMin(1, 0, n - 1);
        initMax(1, 0, n - 1);

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;

            int min = findMin(1, 0, n - 1, a, b);
            int max = findMax(1, 0, n - 1, a, b);

            System.out.println(min + " " + max);
        }
    }

    public static void initMin(int node, int start, int end) {
        if (start == end) {
            minArr[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            initMin(node * 2, start, mid);
            initMin(node * 2 + 1, mid + 1, end);
            minArr[node] = Math.min(minArr[node * 2], minArr[node * 2 + 1]);
        }
    }

    public static void initMax(int node, int start, int end) {
        if (start == end) {
            maxArr[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            initMax(node * 2, start, mid);
            initMax(node * 2 + 1, mid + 1, end);
            maxArr[node] = Math.max(maxArr[node * 2], maxArr[node * 2 + 1]);
        }
    }

    public static int findMin(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Integer.MAX_VALUE;
        if (left <= start && end <= right) return minArr[node];

        int mid = (start + end) / 2;
        int lMin = findMin(node * 2, start, mid, left, right);
        int rMin = findMin(node * 2 + 1, mid + 1, end, left, right);

        return Math.min(lMin, rMin);
    }

    public static int findMax(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Integer.MIN_VALUE;
        if (left <= start && end <= right) return maxArr[node];

        int mid = (start + end) / 2;
        int lMax = findMax(node * 2, start, mid, left, right);
        int rMax = findMax(node * 2 + 1, mid + 1, end, left, right);

        return Math.max(lMax, rMax);
    }
}

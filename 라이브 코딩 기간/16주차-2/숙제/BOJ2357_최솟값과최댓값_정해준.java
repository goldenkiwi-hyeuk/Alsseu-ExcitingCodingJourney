import java.util.Scanner;

public class BOJ2357_최솟값과최댓값_정해준 {
    static int[] arr, min, max;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        min = new int[N * 4];
        max = new int[N * 4];
        maxInit(1, N, 1);
        minInit(1, N, 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int left = sc.nextInt();
            int right = sc.nextInt();

            sb.append(minFind(1,N,1,left,right) + " " + maxFind(1,N,1,left,right)).append("\n");
        }
        System.out.println(sb);
    }

    public static int maxInit(int start, int end, int node) {
        if(start == end) {
            return max[node] = arr[start];
        }
        int mid = (start + end) / 2;

        return max[node] = Math.max(maxInit(start, mid, node * 2),maxInit(mid + 1, end, node * 2 + 1));
    }

    public static int minInit(int start, int end, int node) {
        if(start == end) {
            return min[node] = arr[start];
        }
        int mid = (start + end) / 2;

        return min[node] = Math.min(minInit(start, mid, node * 2),minInit(mid + 1, end, node * 2 + 1));
    }

    public static int maxFind(int start, int end, int node, int left, int right) {
        if(left > end || right < start) { //범위 밖일 경우
            return Integer.MIN_VALUE;
        }

        if(left <= start && right >= end) {
            return max[node];
        }

        int mid = (start + end) / 2;

        return Math.max(maxFind(start, mid, node * 2, left, right),maxFind(mid + 1, end, node * 2 + 1, left, right));
    }

    public static int minFind(int start, int end, int node, int left, int right) {
        if(left > end || right < start) {
            return Integer.MAX_VALUE;
        }

        if(left <= start && right >= end) {
            return min[node];
        }

        int mid = (start + end) / 2;

        return Math.min(minFind(start, mid, node * 2, left, right),minFind(mid + 1, end, node * 2 + 1, left, right));
    }
}

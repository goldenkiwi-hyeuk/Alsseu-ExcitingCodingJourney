import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] arr;
    static long[] tree;
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // N개의 수
        M = Integer.parseInt(st.nextToken()); // 수의 변경 횟수
        K = Integer.parseInt(st.nextToken()); // 곱을 구하는 횟수

        arr = new long[N + 1];
        tree = new long[N * 4];


        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(1, 1, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (type == 1) {
                // 값 변경
                update(1, 1, N, a, b);
            } else {
                // 구간 곱 계산
                long result = query(1, 1, N, a, (int) b);
                System.out.println(result);
            }
        }
    }


    // 탑-다운으로 세그먼트 트리 생성
    static long init(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start] % MOD;

        int mid = (start + end) / 2;
        return tree[node] = (init(node * 2, start, mid) * init(node * 2 + 1, mid + 1, end)) % MOD;
    }

    // 탑-다운으로 세그먼트 트리 값 변경
    static long update(int node, int start, int end, int idx, long newVal) {
        if (idx < start || idx > end) return tree[node];

        if (start == end) return tree[node] = newVal % MOD;

        int mid = (start + end) / 2;
        return tree[node] = (update(node * 2, start, mid, idx, newVal) * update(node * 2 + 1, mid + 1, end, idx, newVal)) % MOD;
    }

    static long query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) return 1;

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return (query(node * 2, start, mid, left, right) * query(node * 2 + 1, mid + 1, end, left, right)) % MOD;
    }

}

import javax.xml.stream.events.StartDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2042_구간합구하기_정해준 {
    static long[] tree;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        tree = new long[N * 4];
        arr = new long[N + 1];
        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        init(1, N, 1);// 트리 초기화

        for(int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            if(idx == 1) {
                int target = Integer.parseInt(st.nextToken());
                long change = Long.parseLong(st.nextToken());
                update(1, N, 1, target, change - arr[target]);
                arr[target] = change;
            } else if(idx == 2) {
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                sb.append(sum(1, N, 1, left,right)).append("\n");

            }
        }

        System.out.println(sb);
    }

    public static long init(int start, int end, int node) {
        if(start == end) {
           return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, 2*node) + init(mid + 1, end, 2*node+1);
    }

    public static long sum(int start, int end, int node, int left, int right) {
        if(left > end || right < start) {
            return 0;
        }

        if(left <= start && right >= end) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, 2*node + 1, left, right);
    }

    public static void update(int start, int end, int node, int target, long change) {
        if(target < start || target > end) {
            return;
        }

        tree[node] += change;
        if(start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, target, change);
        update(mid + 1, end, 2*node + 1, target, change);
    }

}

import java.util.Scanner;

public class BOJ11505_구간곱구하기_정해준 {

    static int N;
    static int M;
    static int K;
    static int MOD = 1000000007;
    static long[] arr;
    static long[] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        arr = new long[N + 1];
        tree = new long[N * 4];
        for(int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }
        init(1,N,1);
        StringBuilder sb = new StringBuilder();
        // a 가 1인 경우 b르 c 로 a가 2인 경우 b 부터 c 를 곱하기
        for(int i = 0; i < M+K; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if(a == 1) {
                arr[b] = c;
                update(1, N, 1, b, c);
            } else if(a == 2) {
                sb.append(mul(1,N,1,b,c)).append("\n");
            }
        }
        System.out.println(sb);


    }

    static long init(int start, int end, int node) {
        if(start == end) {
            return tree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[node] = (init(start,mid,node*2) * init(mid + 1, end, node * 2 + 1)) % MOD;
    }

    static long update(int start, int end, int node, int target, int num) {
        if(target < start || target > end)
            return tree[node];

        if(start == end)
            return tree[node] = num;
        int mid = (start + end) / 2;
        return tree[node] = (update(start,mid,node*2,target, num) * update(mid + 1, end, node * 2 + 1, target, num)) % MOD;
    }

    static long mul(int start, int end, int node, int left, int right) {
        if(right < start || left > end)
            return 1;

        if(left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;

        return  (mul(start,mid,node*2,left,right) * mul(mid + 1, end,node * 2 + 1, left, right)) % MOD;
    }


}

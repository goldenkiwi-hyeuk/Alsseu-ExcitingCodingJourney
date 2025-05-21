import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // 기본아이디어 : 세그먼트 트리
    static int N;
    static long[] arr;
    static List<Node> nodeList;

    private static class Node {
        int start;
        int end;
        long sum;
        Node left;
        Node right;

        Node() {
        }

        Node(int start, int end, long sum, Node left, Node right) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Init();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                updateNode(1, b, c);
            } else if (a == 2) {
                long num = checkNode(1, b, c);
                sb.append(num).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    // 값을 체크하는 함수
    private static long checkNode(int idx, int b, long c) {
        if (b < nodeList.get(idx).start) {
            return 0;
        }
        if (c > nodeList.get(idx).end) {
            return 0;
        }
        if (nodeList.get(idx).start == b && nodeList.get(idx).end == c) {
            return nodeList.get(idx).sum;
        }
        int mid = (nodeList.get(idx).start + nodeList.get(idx).end) / 2;
        if (b > mid) {
            return checkNode(idx * 2 + 1, b, c);
        } else if (c <= mid) {
            return checkNode(idx * 2, b, c);
        } else {
            long num1 = checkNode(idx * 2, b, mid);
            long num2 = checkNode(idx * 2 + 1, mid + 1, c);
            return num1 + num2;
        }
    }

    // 값을 업데이트하는 함수
    private static void updateNode(int idx, int b, long l) {
        if (nodeList.get(idx).start == b && nodeList.get(idx).end == b) {
            nodeList.get(idx).sum = l;
            return;
        }

        if ((nodeList.get(idx).start <= b) && (nodeList.get(idx).end >= b)) {
            updateNode(idx * 2, b, l);
            updateNode(idx * 2 + 1, b, l);
            nodeList.get(idx).sum = nodeList.get(2*idx).sum + nodeList.get(2*idx+1).sum;
        }
    }

    // 초기화 1 노드 생성
    private static void Init() {
        nodeList = new ArrayList<Node>();
        for (int i = 0; i < 4 * N; i++) {
            nodeList.add(new Node());
        }
        Init2(1, 1, N);
    }

    // 초기화2 노드들에 값 부여
    private static void Init2(int idx, int start, int end) {
        if (start == end) {
            nodeList.get(idx).start = start;
            nodeList.get(idx).end = end;
            nodeList.get(idx).sum = arr[start];
            return;
        }

        nodeList.get(idx).start = start;
        nodeList.get(idx).end = end;
        int mid = (start + end) / 2;
        nodeList.get(idx).left = nodeList.get(idx * 2);
        Init2(idx * 2, start, mid);
        nodeList.get(idx).right = nodeList.get(idx * 2 + 1);
        Init2(idx * 2 + 1, mid + 1, end);
        nodeList.get(idx).sum = nodeList.get(idx * 2).sum + nodeList.get(idx * 2 + 1).sum;
    }
}

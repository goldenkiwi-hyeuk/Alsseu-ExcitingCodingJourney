import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // 기본아이디어 : 세그먼트 트리 

    static int N, M;
    static int[] arr;
    
    // 세그먼트 트리의 노드에 범위의 합을 저장하는 것이 아니라 범위내에서 가장 큰값과 작은 값을 저장
    static class Node {
        int start;
        int end;
        int min;
        int max;
        Node leftChild;
        Node rightChild;

        Node(){}

        Node(int start, int end, int min, int max, Node leftChild, Node rightChild) {
            this.start = start;
            this.end = end;
            this.min = min;
            this.max = max;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }
    static List<Node> nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Init();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st =  new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min = checkMin(1,a,b);
            int max = checkMax(1,a,b);
            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int checkMin(int idx, int a, int b) {
        if ((nodeList.get(idx).start == a)&&(nodeList.get(idx).end == b)) {
            return nodeList.get(idx).min;
        } else {
            int mid = (nodeList.get(idx).start+nodeList.get(idx).end)/2;
            if (b <= mid) {
                return checkMin(idx*2,a,b);
            } else if (a > mid) {
                return checkMin(idx*2+1,a,b);
            } else {
                int min1 = checkMin(idx*2,a,mid);
                int min2 = checkMin(idx*2+1,mid+1,b);
                return Math.min(min1,min2);
            }
        }
    }

    private static int checkMax(int idx, int a, int b) {
        if ((nodeList.get(idx).start == a)&&(nodeList.get(idx).end == b)) {
            return nodeList.get(idx).max;
        } else {
            int mid = (nodeList.get(idx).start+nodeList.get(idx).end)/2;
            if (b <= mid) {
                return checkMax(idx*2,a,b);
            } else if (a > mid) {
                return checkMax(idx*2+1,a,b);
            } else {
                int max1 = checkMax(idx*2,a,mid);
                int max2 = checkMax(idx*2+1,mid+1,b);
                return Math.max(max1,max2);
            }
        }
    }


    private static void Init() {
        nodeList = new ArrayList<Node>();
        for (int i = 0; i < 4*N; i++) {
             nodeList.add(new Node());
        }
        Init2(1,1,N);
    }

    private static void Init2(int idx, int start, int end) {
        nodeList.get(idx).start = start;
        nodeList.get(idx).end = end;
        if (start == end){
            nodeList.get(idx).min = arr[start];
            nodeList.get(idx).max = arr[start];
        } else {
            int mid = (start+end)/2;
            Init2(idx*2, start, mid);
            Init2(idx*2+1, mid+1, end);
            nodeList.get(idx).leftChild = nodeList.get(idx*2);
            nodeList.get(idx).rightChild = nodeList.get(idx*2+1);
            nodeList.get(idx).min = Math.min(nodeList.get(idx).leftChild.min, nodeList.get(idx).rightChild.min);
            nodeList.get(idx).max = Math.max(nodeList.get(idx).leftChild.max, nodeList.get(idx).rightChild.max);
        }
    }
}

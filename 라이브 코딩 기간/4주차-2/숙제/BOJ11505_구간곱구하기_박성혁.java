import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 기본아이디어 : 세그먼트트리
    private static class Node { // 각 노드는 시작 인덱스, 종료 인덱스, 숫자, 왼쪽자식, 오른쪽 자식, 부모를 가지고 있음
        int start, end, num;
        Node left, right, parent;

        public Node(int start, int end, int num, Node left, Node right, Node parent) {
            this.start = start;
            this.end = end;
            this.num = num;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(){}
    }

    static List<Node> nodeList;
    static int[] arr;
    static int mod = 1000000007;
    public static void main(String[] args) throws IOException { 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList<Node>();
        for (int i = 0; i <= 4*N; i++) { // nodeList에 노드 넣기 이유를 잘모르겠는데 4*N개가 필요하다고 함 공부할 예정
            nodeList.add(new Node());
        }
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        nodeInit(nodeList.get(1),null,1,1, N); // 세그먼트 트리 초기화 함수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a==1){
                nodeupdate(1,b,c);
            } else {
                sb.append(getNum(1,b,c)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int getNum(int idx,int b, int c) {
        if (nodeList.get(idx).start ==b && nodeList.get(idx).end ==c) { // 주어진 범위가 동일하다면 노드 값 제출
            return nodeList.get(idx).num;
        } else {
            int start = nodeList.get(idx).start;
            int end = nodeList.get(idx).end;
            int mid = (start+end)/2;
            if (c<=mid){ // 끝수가 왼쪽 세그먼트 안에 들어간다면
                return getNum(idx*2,b,c); // 왼쪽 세그먼트 탐색
            } else if(b>mid) { // 시작수가 오른쪽 세그먼트 안에 들어간다면
                return getNum(idx*2+1,b,c); // 오른쪽 세그먼트 탐색
            } else { // 시작수는 왼쪽, 끝수는 오른쪽이라면?
                int num1 = getNum(idx*2+1,mid+1,c); // 시작수부터 중간까지 범위 탐색
                int num2 = getNum(idx*2,b,mid); // 중간수부터 끝수까지 범위 탐색
                return (int)(((long)num1*num2)%mod); // 두수의 곱을 계산
            }
        }
    }

    private static void nodeInit(Node node, Node parent,int idx,int start, int end) {
        if (start == end) { // 시작과 끝의 인덱스가 동일하다 = 리프 노드, 리프노드는 arr[start]의 값을 가짐
            node.start = start; 
            node.end = end;
            node.num = arr[start]%mod;
            node.parent = parent;
            return;
        }
        node.parent = parent; 
        node.start = start;
        node.end = end;
        nodeInit(nodeList.get(idx*2), node, idx*2,start, (start+end)/2); // 왼쪽 자식에 대한 세그먼트 트리 초기화
        node.left = nodeList.get(idx*2);
        nodeInit(nodeList.get(idx*2+1), node, idx*2+1, (start+end)/2+1, end); // 오른쪽 자식에 대한 세그먼트 트리 초기화
        node.right = nodeList.get(idx*2+1);
        node.num = (int) (((long)(node.left.num%mod)*(node.right.num%mod))%mod); // 왼쪽 자식과 오른쪽 자식의 숫자를 합함
    }

    private static void nodeupdate(int idx, int targetIdx, int num) {
        if (nodeList.get(idx).start == targetIdx && nodeList.get(idx).end == targetIdx) { // 타겟 인덱스가 시작이자 끝이라면 리프 노드를 찾은것 리프노드 갱신
            nodeList.get(idx).num = num;
            return;
        }
        int start = nodeList.get(idx).start;
        int end = nodeList.get(idx).end;
        int mid = (start+end)/2;
        if (targetIdx>=start&&targetIdx<=mid) { // 타겟 인덱스가 왼쪽에 있다면 왼쪽 세그먼트 트리 갱신
            nodeupdate(idx*2,targetIdx,num);
            nodeList.get(idx).num = (int)(((long)nodeList.get(idx*2).num * nodeList.get(idx*2+1).num)%mod);
        } else if (targetIdx>mid&&targetIdx<=end) { // 타겟 인덱스가 오른쪽에 있다면 오른쪽 세그먼트 트리 갱신
            nodeupdate(idx*2+1,targetIdx,num);
            nodeList.get(idx).num = (int)(((long)nodeList.get(idx*2).num * nodeList.get(idx*2+1).num)%mod);
        }
    }
}

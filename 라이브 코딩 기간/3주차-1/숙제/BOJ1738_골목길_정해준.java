import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


//벨만 포드 알고리즘을 이용해서 사이클을 찾는다
// 사이클이 목적지와 관련이 있는지 파악한다.
// 관련이 있을 경우 -1를 출력

public class BOJ1738_골목길_정해준 {
    static class Node {
        int next;
        int weight;

        Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }

    static long[] dist;
    static int N, M;
    static int MAX = Integer.MIN_VALUE;
    static List<Node>[] list;
    static boolean[] visited;
    static int[] root;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        list = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) { // 간선의 가중치와 경로를 저장
            int now = sc.nextInt();
            int next = sc.nextInt();
            int weight = sc.nextInt();
            list[now].add(new Node(next, weight));
        }

        if(belman()) {
            System.out.println(-1);
        } else { //사이클이 목적지에 없을 경우
            // 최적 경로를 출력
            visited = new boolean[N + 1];
            visited[1] = true;
            root = new int[N + 1];
            root[0] = 1; // 시작 지점은 1
            output(1, 1);
        }


    }
    public static void output(int start, int idx) {
        if(start == N) { // 끝에 다달았을 경우 출력
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < idx; i++) {
                sb.append(root[i]).append(" ");
            }
            System.out.println(sb);
            return;
        }

        for(Node node : list[start]) {
            if(!visited[node.next] && dist[node.next] == dist[start] + node.weight) { // 최적화 한것과 결과가 같아야 함
                visited[node.next] = true;
                root[idx] = node.next;
                output(node.next, idx + 1);
            }

        }
    }
    public static boolean belman() {
        // 간선간의 가중치를 초기화
        dist = new long[N + 1];
        for(int i = 2; i <= N; i++) {
            dist[i] = MAX;
        }
        for(int round = 0; round < N ; round++) {
            for(int i = 1; i <= N; i++) {
                for(Node node : list[i]) { // 한 라운드에 모든 간선을 확인
                    if(dist[node.next] < dist[i] + node.weight) { // 가중치가 더 클 경우
                        dist[node.next] = dist[i] + node.weight;
                        if(round == N - 1){ // 사이클 발생, 마지막 라운드에서도 값이 커졌다 -> 양의 사이클
                            //이 사이클이 목적지에 연결이 되있는지 확인
                            visited = new boolean[N + 1];
                            visited[i] = true;
                            if(connected(i))
                                return true; // 사이클이 목적지와 이어짐
                        }
                    }
                }
            }
        }


        return false;
    }

    public static boolean connected(int i) {
        if(i == N) { // 목적지가 이어짐
            return true;
        }
        boolean flag = false;
        for(Node node : list[i]) {
            if(visited[node.next])
                continue;

            visited[node.next] = true;
            flag = flag || connected(node.next); // 이어진 노드의 끝이 결국 목적지에 도달했는지 ||로 확인
        }
        return flag;
    }
}

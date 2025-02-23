import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) { // 간선의 가중치와 경로를 저장
            int now = sc.nextInt();
            int next = sc.nextInt();
            int weight = sc.nextInt();
            list[now].add(new Node(next, weight));
        }


        boolean cycle = bellman();
        if(cycle) {
            System.out.println(-1);
        } else {
            visited = new boolean[N + 1];
            visited[1] = true;
            root = new int[N + 1];
            root[0] = 1;
            output(1,1);
        }
    }

    static boolean bellman() { // 벨만포드 알고리즘
        dist = new long[N + 1];
        for(int i = 2; i <= N; i++) {
            dist[i] = MAX;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 1; j <= N; j++) {
                for(Node now : list[j]) {
                    if(dist[now.next] < dist[j] + now.weight) {
                        dist[now.next] = dist[j] + now.weight;
                        if(i == N - 1) {
                            visited = new boolean[N+1];
                            visited[j] = true;
                            if(checkCycle(j)) { // 도착점과 서클이 연결되어 있을 경우
                                return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean checkCycle(int now) { // 노드를 dfs를 이용해서 연결되어 있는지 확인
        if(now == N) {
            return true;
        }
        boolean flag = false;
        for(Node node : list[now]) {
            if(!visited[node.next]) {
                visited[node.next] = true;
                flag = flag || checkCycle(node.next);
            }
        }

        return flag;
    }

    static void output(int prev, int order) {
        if(prev == N) {
            for(int i = 0; i < order; i++) {
                System.out.print(root[i] + " ");
            }
            return;
        }

        for(Node node : list[prev]) {
            if(!visited[node.next] && (node.weight + dist[prev]) == dist[node.next]) {
                visited[node.next] = true;
                root[order] = node.next;
                output(node.next, order + 1);
            }
        }
    }
}

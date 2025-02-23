import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본아이디어 : 벨만포드 알고리즘(Why? 음의 가중치 존재)
    // 벨만포드 : 정점의 갯수반복을 하는 과정에서 Edge의 갯수만큼 반복(2중 for문)
    // distance 배열이 갱신여부를 계속 확인

    private static class Edge{
        int start, end, cost;

        public Edge(){}

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    private static class Route{
        List<Integer> route;

        public Route(){}

        public Route(List route) {
            this.route = route;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int n = Integer.parseInt(st.nextToken());
        int[] distance = new int[n+1];
        int[] predecessor = new int[n+1];
        Arrays.fill(distance, -987654321); // 무한대로 저장
        int m = Integer.parseInt(st.nextToken());
        List<Edge> edgeList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.add(new Edge(start, end, cost));
        }

        // 1. 기본 설정: distance[], predecessor[] 배열 초기화
        distance[1] = 0;
        for (int i = 2; i <= n; i++) {
            distance[i] = -987654321;
            predecessor[i] = -1;
        }

        // 2. n-1번 반복하여 최대 금액 갱신
        for (int i = 1; i < n; i++) {
            for (Edge edge : edgeList) {
                if (distance[edge.start] != -987654321 && distance[edge.end] < distance[edge.start] + edge.cost) {
                    distance[edge.end] = distance[edge.start] + edge.cost;
                    predecessor[edge.end] = edge.start;
                }
            }
        }

        // 3. n번째 반복: 갱신 가능한 정점 표시
        boolean[] cycle = new boolean[n+1];
        for (Edge edge : edgeList) {
            if (distance[edge.start] != -987654321 && distance[edge.end] < distance[edge.start] + edge.cost) {
                cycle[edge.end] = true;
            }
        }

        // 4. 갱신된 정점에서 DFS/BFS를 수행하여 n번에 도달 가능한지 확인
        for (int i = 1; i <= n; i++) {
            if (cycle[i] && reachableToN(i,n,edgeList)) {  // reachableToN(i): i에서 n까지 갈 수 있는지 여부
                System.out.println(-1);
                return;
            }
        }
        // 5. 경로가 존재하면, predecessor 배열을 이용해 1부터 n까지 역추적
        List<Integer> path = new ArrayList<>();
        for (int cur = n; cur != -1; cur = predecessor[cur]) {
            path.add(cur);
            if (cur == 1){
                break;
            }
        }
        Collections.reverse(path);
        if (path.get(0)!=1){
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int num : path) {
                sb.append(num).append(" ");
            }
            System.out.println(sb);
        }
    }

    private static boolean reachableToN(int start , int n, List<Edge> edgeList) {
        boolean[] visited = new boolean[n + 1];
        Deque<Integer> deq = new ArrayDeque<>();

        deq.offer(start);
        visited[start] = true;

        while (!deq.isEmpty()) {
            int current = deq.poll();
            if (current == n) {
                return true; // n번 정점에 도달함
            }
            // 현재 정점과 연결된 모든 간선을 확인
            for (Edge edge : edgeList) {
                if (edge.start == current && !visited[edge.end]) {
                    visited[edge.end] = true;
                    deq.offer(edge.end);
                }
            }
        }
        return false; // n번 정점에 도달할 수 없음
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본아이디어 : 다익스트라 역추적
    static int N,M,K;
    static List<List<Edge>> edgeList;

    private static class Edge implements Comparable<Edge> {
        int end;
        long cost;

        Edge(){}

        Edge(int end, long cost) {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost - o.cost > 0) return 1;
            if (this.cost - o.cost < 0) return -1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList.get(end).add(new Edge(start, cost)); // 역추적 다익스트라를 할 예정이기에 반대로 코스트를 입력한다.
        }
        long[] dist = new long[N+1];
        boolean[] visited = new boolean[N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i<K; ++i){ // 모든 면접장의 거리는 0으로 입력 Why? 어느 면접장을 가던지 상관이 없기 때문
            int num = Integer.parseInt(st.nextToken());
            dist[num] = 0;
            pq.add(new Edge(num, 0));
        }
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (visited[e.end]) continue;
            visited[e.end] = true;
            for (Edge edge : edgeList.get(e.end)) {
                if (dist[edge.end]> dist[e.end] + edge.cost) {
                    dist[edge.end] = dist[e.end] + edge.cost;
                    pq.add(new Edge(edge.end, dist[edge.end]));
                }
            }
        }

        int maxIdx = -1;
        long max = -987654321;
        for (int i = 1; i <= N; i++) {
            if (dist[i]>max){ // 정답 갱신
                max = dist[i];
                maxIdx = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxIdx).append("\n");
        sb.append(max);
        System.out.println(sb);
    }
}

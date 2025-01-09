import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Edge implements Comparable<Edge> {

        int end, cost;

        public Edge() {
        }

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static List<List<Edge>> edgelist;
    public static int N, E;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edgelist = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            edgelist.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgelist.get(start).add(new Edge(end, cost));
            edgelist.get(end).add(new Edge(start, cost));
        }
        str = br.readLine();
        st = new StringTokenizer(str);
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] distb = Dijkstra(b);
        int[] distc = Dijkstra(c);
        int abcd = distb[1] + distb[c] + distc[N];
        int acbd = distc[1] + distc[b] + distb[N];
        if ((distb[1] == 987654321 || distb[c] == 987654321 || distc[N] == 987654321) && (distc[1] == 987654321 || distc[b] == 987654321 || distb[N] == 987654321)) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(abcd, acbd));
        }
    }

    private static int[] Dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 987654321);
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (visited[e.end]) continue;
            visited[e.end] = true;
            for (Edge edge : edgelist.get(e.end)) {
                if (dist[edge.end] > dist[e.end] + edge.cost) {
                    dist[edge.end] = dist[e.end] + edge.cost;
                    pq.add(new Edge(edge.end, dist[edge.end]));
                }
            }
        }
        return dist;
    }
}
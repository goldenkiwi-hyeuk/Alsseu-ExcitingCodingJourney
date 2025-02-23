import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int u, v, w;
        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 교차점 개수
        int m = Integer.parseInt(st.nextToken()); // 골목길 개수


        List<Edge> edges = new ArrayList<>();
        List<List<Integer>> reverseGraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.add(new Edge(u, v, w));
            reverseGraph.get(v).add(u);
        }

        boolean[] reachable = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        reachable[n] = true;
        queue.add(n);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int prev : reverseGraph.get(cur)) {
                if (!reachable[prev]) {
                    reachable[prev] = true;
                    queue.add(prev);
                }
            }
        }

        long[] dist = new long[n + 1];
        int[] prev = new int[n + 1];
        Arrays.fill(dist, Long.MIN_VALUE);
        dist[1] = 0;

        boolean cycle = false;
        for (int i = 1; i <= n; i++) {
            for (Edge edge : edges) {
                if (dist[edge.u] != Long.MIN_VALUE && dist[edge.v] < dist[edge.u] + edge.w) {
                    dist[edge.v] = dist[edge.u] + edge.w;
                    prev[edge.v] = edge.u;
                    if (i == n && reachable[edge.v]) {
                        cycle = true;
                    }
                }
            }
        }

        if (cycle || dist[n] == Long.MIN_VALUE) {
            System.out.println(-1);
        } else {
            ArrayList<Integer> path = new ArrayList<>();
            int cur = n;
            while (cur != 0) {
                path.add(cur);
                cur = prev[cur];
            }
            Collections.reverse(path);
            for (int num : path) {
                sb.append(num).append(" ");
            }
            System.out.println(sb);
        }
    }
}

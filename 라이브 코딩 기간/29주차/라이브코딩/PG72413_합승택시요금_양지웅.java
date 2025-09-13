import java.util.*;

class Solution {
    static class Edge {
        int to; int w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i <= n; i++) g.add(new ArrayList<>());
        for (int[] f : fares) {
            int u = f[0], v = f[1], w = f[2];
            g.get(u).add(new Edge(v, w));
            g.get(v).add(new Edge(u, w));
        }

        int[] ds = dijkstra(n, g, s);
        int[] da = dijkstra(n, g, a);
        int[] db = dijkstra(n, g, b);

        int ans = Integer.MAX_VALUE;
        for (int k = 1; k <= n; k++) {
            if (ds[k] == Integer.MAX_VALUE || da[k] == Integer.MAX_VALUE || db[k] == Integer.MAX_VALUE) continue;
            ans = Math.min(ans, ds[k] + da[k] + db[k]);
        }
        return (int) ans;
    }

    private int[] dijkstra(int n, List<List<Edge>> g, int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean[] visited = new boolean[n + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge e : g.get(u)) {
                int v = e.to;
                int nd = d + e.w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new int[]{v, nd});
                }
            }
        }
        return dist;
    }
}
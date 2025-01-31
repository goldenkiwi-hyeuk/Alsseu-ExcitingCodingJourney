import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Edge implements Comparable<Edge> {
        int start, end;
        long cost;

        public Edge(int start, int end, long cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            if (this.cost < o.cost) {
                return -1;
            } else if (this.cost > o.cost) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    static int[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        root = new int[V+1];
        for (int i = 1; i <= V; i++) {
            root[i] = i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            pq.add(new Edge(start, end, cost));
        }

        long ans = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int start = e.start;
            int end = e.end;
            int startRoot = findRoot(start);
            int endRoot = findRoot(end);
            if (startRoot != endRoot) {
                int rootNum = Math.min(startRoot, endRoot);
                root[Math.max(startRoot, endRoot)] = rootNum;
                ans += e.cost;
            }
        }
        System.out.println(ans);
    }

    private static int findRoot(int num) {
        if (num == root[num]) return num;
        return root[num] = findRoot(root[num]);
    }
}

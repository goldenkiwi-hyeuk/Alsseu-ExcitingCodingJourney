import java.util.*;

public class BOJ2887_행성터널_정해준 {
    static class Planet {
        int idx, x, y, z;

        Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    static int[] parent;

    static int find(int a) {
        if (parent[a] != a)
            parent[a] = find(parent[a]);
        return parent[a];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Planet[] planets = new Planet[N];

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            planets[i] = new Planet(i, x, y, z);
        }

        List<Edge> edges = new ArrayList<>();

        // x, y, z 각각 기준으로 정렬 후 인접 노드 간 간선 생성
        for (int dim = 0; dim < 3; dim++) {
            int finalDim = dim;
            Arrays.sort(planets, Comparator.comparingInt(p ->
                    (finalDim == 0) ? p.x : (finalDim == 1) ? p.y : p.z
            ));
            for (int i = 0; i < N - 1; i++) {
                Planet a = planets[i];
                Planet b = planets[i + 1];
                int cost = Math.abs((finalDim == 0) ? a.x - b.x :
                        (finalDim == 1) ? a.y - b.y :
                                a.z - b.z);
                edges.add(new Edge(a.idx, b.idx, cost));
            }
        }

        // 크루스칼 알고리즘
        Collections.sort(edges);
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        int total = 0;
        for (Edge e : edges) {
            if (find(e.from) != find(e.to)) {
                union(e.from, e.to);
                total += e.cost;
            }
        }

        System.out.println(total);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 기본아이디어 : 유니온파인드

    static int[] parents;

    private static class Planet {
        int idx, x,y,z;

        public Planet() {
        }

        public Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Edge {
        int start, end, cost;

        public Edge() {
        }

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets.add(new Planet(i+1,x, y, z));
        }

        List<Edge> edges = new ArrayList<>();
        // x 기준으로 정렬하여 N-1개의 간선 뽑아내기
        Collections.sort(planets, (o1, o2) -> o1.x - o2.x);
        for (int i = 0; i < N-1; i++) {
            int start = planets.get(i).idx;
            int end = planets.get(i+1).idx;
            Edge edge = new Edge(start, end, Math.abs(planets.get(i).x - planets.get(i+1).x));
            edges.add(edge);
        }

        // y 기준으로 정렬하여 N-1개의 간선 뽑아내기
        Collections.sort(planets, (o1, o2) -> o1.y - o2.y);
        for (int i = 0; i < N-1; i++) {
            int start = planets.get(i).idx;
            int end = planets.get(i+1).idx;
            Edge edge = new Edge(start, end, Math.abs(planets.get(i).y - planets.get(i+1).y));
            edges.add(edge);
        }

        // z 기준으로 정렬하여 N-1개의 간선 뽑아내기
        Collections.sort(planets, (o1, o2) -> o1.z - o2.z);
        for (int i = 0; i < N-1; i++) {
            int start = planets.get(i).idx;
            int end = planets.get(i+1).idx;
            Edge edge = new Edge(start, end, Math.abs(planets.get(i).z - planets.get(i+1).z));
            edges.add(edge);
        }

        parents = new int[N+1];
        for (int i =1; i<N+1; i++) {
            parents[i] = i;
        }
        
        // 엣지의 값 기준으로 오름차순 정렬
        Collections.sort(edges, (o1,o2)->{
            return o1.cost - o2.cost;
        });

        int total = 0;
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int sP = findParent(edge.start);
            int eP = findParent(edge.end);
            if (sP != eP){
                total += edge.cost;
            }
            Union(edge.start, edge.end);
        }

        System.out.println(total);
    }

    private static void Union(int start, int end) {
        int sP = findParent(start);
        int eP = findParent(end);
        parents[Math.max(sP,eP)] = Math.min(sP,eP);
    }

    private static int findParent(int start) {
        if (parents[start] == start) return start;
        return parents[start] = findParent(parents[start]);
    }
}

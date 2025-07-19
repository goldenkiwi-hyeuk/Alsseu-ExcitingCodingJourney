// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class BOJ11657_타임머신_정해준 {
    static class Edge {
        int from;
        int to;
        int w;

        Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
    static ArrayList<Edge> graph;
    static int INF = 987654321;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        graph = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int w = sc.nextInt();
            graph.add(new Edge(from, to, w));
        }

        Bellman(N, M, 1);
    }

    static void Bellman(int N, int M, int start) {
        long[] dist = new long[N + 1];
        Arrays.fill(dist, INF); // 가중치 배열 초기화 
        dist[start] = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Edge edge = graph.get(j);

                //아직 방문한 적이 없고 이전 값보다 지금 루트가 더 작을 경우 
                if(dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.w) {
                    dist[edge.to] = dist[edge.from] + edge.w; //갱신 
                }
            }
        }

        for(int i = 0; i < M; i++) {
            Edge edge = graph.get(i);
            //이미 모든 경우의 수를 확인했는데 이번에 값이 줄을 경우 
            if(dist[edge.from] != INF && dist[edge.to] > dist[edge.from]+ edge.w) {
                System.out.println(-1); // 음수 사이클이 있음 
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i < dist.length; i++) {
            if(dist[i] == INF){
                sb.append(-1).append("\n");
            } else {
                sb.append(dist[i]).append("\n");
            }
        }
        System.out.println(sb);
    }
}
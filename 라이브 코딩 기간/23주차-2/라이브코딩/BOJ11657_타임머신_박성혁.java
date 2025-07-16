import java.util.*;
import java.io.*;

class Main {
    
    private static class Edge{
        int end, cost;
        
        Edge(){}
        
        Edge(int end, int cost){
            this.end = end;
            this.cost = cost;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<List<Edge>> edgeList = new ArrayList<>();
        for(int i = 0; i<= N; ++i){
            edgeList.add(new ArrayList<>());
        }
        
        for(int i = 0; i<M; ++i){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            edgeList.get(A).add(new Edge(B,C));
        }
        
        long[] dist = new long[N+1];
        long INF = 987654321987654321L;
        Arrays.fill(dist,INF);
        dist[1] = 0L;
        
        // 벨만-포드 알고리즘
        for(int i=0; i<N-1; ++i){
            for(int j = 1; j<=N;++j){
                if(dist[j] != INF){
                    for(Edge edge : edgeList.get(j)){
                        dist[edge.end] = Math.min(dist[edge.end], dist[j]+edge.cost);
                    }
                }
            }
        }
        
        // 음의 순환이 있는지 확인하기 위해 기존값 따로 빼두고
        long[] dist2 = new long[N+1];
        for(int i = 0; i<=N;++i){
            dist2[i] = dist[i]; 
        }
        
        // 벨만포드를 한번더 실행
        for(int j = 1; j<=N;++j){
            if(dist[j] != INF){
                for(Edge edge : edgeList.get(j)){
                    dist[edge.end] = Math.min(dist[edge.end], dist[j]+edge.cost);
                }
            }
        }
        
        // 기존값과 비교해서 변경값이 있다면 음의 순환이 존재한다는 의미!
        boolean flag = true;
        for(int i = 0; i<=N;++i){
            if(dist2[i] != dist[i]){
                flag = false;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if(flag){
            for(int i = 2; i<=N;++i){
                if(dist[i] == INF){
                    sb.append(-1);
                } else {
                    sb.append(dist[i]);
                }
                sb.append("\n");
            }
        } else {
            sb.append(-1);
        }
        
        System.out.println(sb.toString());
    }
}
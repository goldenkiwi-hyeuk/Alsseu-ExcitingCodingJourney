import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Street implements Comparable<Street>{
        int to;
        long dist;

        public Street(int to, long dist){
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Street o) {
            return Long.compare(this.dist, o.dist);
        }
    }

    static List<Street>[] list;
    static long[] minDist; // 각 도시별 최단거리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도시의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수
        int K = Integer.parseInt(st.nextToken()); // 면접장의 수

        list = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        minDist = new long[N+1];
        Arrays.fill(minDist,Long.MAX_VALUE);


        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            // 면접장에서 각 도시까지의 거리 구하기
            list[V].add(new Street(U,dist));
        }

        PriorityQueue<Street> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            int target = Integer.parseInt(st.nextToken());
            pq.offer(new Street(target,0));
            minDist[target] = 0;
        }

        while(!pq.isEmpty()){
            Street cur = pq.poll();

            if(cur.dist > minDist[cur.to]) continue;

            for(Street next : list[cur.to]){
                long newDist = cur.dist + next.dist;
                if(newDist < minDist[next.to]){
                    minDist[next.to] = newDist;
                    pq.offer(new Street(next.to, newDist));
                }
            }
        }


        int result = 0;
        long maxDist = 0;
        for(int i=1; i<N+1; i++){
            if(minDist[i] != Long.MAX_VALUE && minDist[i] > maxDist){
                maxDist = minDist[i];
                result = i;
            }
        }

        System.out.println(result);
        System.out.println(maxDist);



    }
}

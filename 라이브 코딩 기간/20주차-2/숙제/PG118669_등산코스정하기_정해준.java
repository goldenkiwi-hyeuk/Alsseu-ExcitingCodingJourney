import java.util.*;

public class PG118669_등산코스정하기_정해준 {


    class Node implements Comparable<Node>{
        int from;
        int to;
        int w;
        Node(int from, int to, int w){
            this.from = from;
            this.to = to;
            this.w = w;
        }

        public int compareTo(Node o){
            if(this.w == o.w)
                return this.to - o.to;
            return this.w - o.w;
        }
    }

    class Solution {
        List<Node>[] graph;
        Set<Integer> start, end;
        int[] dist;

        public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
            graph = new List[n + 1];
            dist = new int[n + 1];
            for(int i = 0; i <= n; ++i) {
                graph[i] = new ArrayList<>();
            }
            start = new HashSet<>();
            end = new HashSet<>();

            for(int i : gates) {
                start.add(i);
            }
            for(int i : summits) {
                end.add(i);
            }

            for(int[] node : paths){
                if(!start.contains(node[1]) && !end.contains(node[0])) {
                    graph[node[0]].add(new Node(node[0], node[1], node[2]));
                }


                if(!start.contains(node[0]) && !end.contains(node[1])) {
                    graph[node[1]].add(new Node(node[1], node[0], node[2]));
                }

            }


            int[] answer = bfs();

            return answer;
        }

        public int[] bfs(){
            int[] res = new int[2];

            Arrays.fill(dist, Integer.MAX_VALUE);
            PriorityQueue<Node> pq = new PriorityQueue();
            for(int i : start){
                for(Node node : graph[i]) {
                    pq.offer(node);
                }
            }

            res[0] = Integer.MAX_VALUE;
            int max = Integer.MAX_VALUE;
            while(!pq.isEmpty()){
                Node now = pq.poll();
                if(now.w > max)
                    break;

                dist[now.to] = now.w;

                if(end.contains(now.to)){
                    // 정상 도착
                    for(int v : dist){
                        if(v > max && v != Integer.MAX_VALUE || max == Integer.MAX_VALUE) {
                            max = v;
                        }
                    }

                    res[0] = res[0] < now.to ? res[0] : now.to;

                    res[1] = max;
                }

                for(Node next : graph[now.to]){

                    // 가중치가 변하지 않은 경우
                    if(dist[next.to] == Integer.MAX_VALUE) {
                        pq.offer(next);
                    }
                }
            }
            return res;
        }
    }
}

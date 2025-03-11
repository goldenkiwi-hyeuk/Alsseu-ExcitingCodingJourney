import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17835_면접보는승범이네_정해준 {
    static class Node implements Comparable<Node> {
        int idx;
        long cost;

        public Node(int idx, long cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception{
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int M = sc.nextInt();
//        int K = sc.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        //면접장에서 집까지의 최소 거리들을 구하기;
        List<List<Node>> list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>()); // 간선 정보를 저장
        }


        for(int i = 0; i < M; i++) {
            //위치를 뒤집어서 저장
//            int to = sc.nextInt();
//            int from = sc.nextInt();
//            int cost = sc.nextInt();
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(from).add(new Node(to, cost));
        }

        long  max = 0;
        int maxNode = 0;
        long[] dist = new long[N + 1];
        Arrays.fill(dist, 10000000000L);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
//            int start = sc.nextInt();
            int start = Integer.parseInt(st.nextToken());
            pq.offer(new Node(start, 0));
            dist[start] = 0;
        }

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(dist[node.idx] < node.cost)
                continue; // 이미 저장되어 있는 것보다 클 경우 무시
            for(Node next : list.get(node.idx)) {
                if(dist[next.idx] > dist[node.idx] + next.cost) {
                    dist[next.idx] = dist[node.idx] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        } // 가중치를 한번 돌리고 나서

        for(int i = 1; i <= N; i++) {
            if(max < dist[i]) {
                max = dist[i];
                maxNode = i;
            }
        }

        System.out.println(maxNode);
        System.out.println(max);

    }
}

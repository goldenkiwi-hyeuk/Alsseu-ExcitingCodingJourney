import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 : 스위핑 알고리즘

    private static class Route implements Comparable<Route> {
        int start, end, idx;

        public Route() {
        }

        public Route(int start, int end, int idx) {
            this.start = start;
            this.end = end;
            this.idx = idx;
        }

        public int compareTo(Route o) { // 시작점 기준 정렬 & 시작점이 같다면 끝점이 더 멀리 있는게 먼저 나오게 정렬
            if (this.start == o.start) {
                return o.end - this.end;
            } else {
                return this.start - o.start;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] check = new int[M+1]; // 0 한번도 체크되지 않은 노선, 1 체크된 노선, -1 다른 버스 노선이 커버하는 노선
        PriorityQueue<Route> pq = new PriorityQueue<>();
        for (int i = 1; i <= M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (start > end ){
                pq.add(new Route(start, end+N, i));
            } else {
                pq.add(new Route(start, end, i));
                pq.add(new Route(start+N, end+N, i));
            }
        }

        int right = -1;
        while(!pq.isEmpty()) {
            Route route = pq.poll();
            if (route.end > right) { // 새로운 범위의 노선일 경우
                right = route.end; 
                if (check[route.idx] == 0) { // 한번도 탐색하지 않았다면? 1
                    check[route.idx] = 1;
                }
            } else { // 범위 안에 있는 노선의 경우
                check[route.idx] = -1; // 다른 노선으로 커버 가능
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            if (check[i] == 1) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }
}

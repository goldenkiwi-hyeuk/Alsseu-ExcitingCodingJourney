import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ10165_버스노선_정해준 {
    // 검색함
    static class Station implements Comparable<Station>{
        int n;
        int a;
        int b;

        Station(int n, int a, int b) {
            this.n = n;
            this.a = a;
            this.b = b;
        }

        public int compareTo(Station o) {
            if(this.a == o.a)
                return o.b - this.b; // 내림차
            return this.a - o.a;
        }
    }
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        List<Integer> ans = new ArrayList<>();
        int max = 0;
        List<Station> stations = new ArrayList<Station>();
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a > b) { // 가장 도착 지점이 마지막인 부분을 확인
                max = Math.max(max, b);
                b += N; // 한바퀴 돌 경우 그만큼 증가 시켜서 저장
            }
            stations.add(new Station(i + 1, a, b));
        }

        Deque<Station> dq = new ArrayDeque<>();
        Collections.sort(stations); // 시작지점이 가깝고 더 멀리 가능 순으로 정렬
        for(Station station : stations) { // 도착지점이 더 짧은 경우는 제외
            if(dq.isEmpty() || dq.getLast().b < station.b) {
                dq.addLast(station);
            }
        }

        while(!dq.isEmpty() && dq.getFirst().b <= max) {
            dq.removeFirst();
        }
        while(!dq.isEmpty()) {
            int idx = dq.pollFirst().n;
            ans.add(idx);
        }
        Collections.sort(ans);
        for(int i : ans) {
            System.out.print(i + " ");
        }
    }
}

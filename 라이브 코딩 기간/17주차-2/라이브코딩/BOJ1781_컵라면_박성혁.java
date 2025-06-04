import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 기본 아이디어 : 그리디
    // 가장 늦은 데드라인부터 풀수 있는 문제의 보상값들을 pq에 저장
    // 가장 큰수를 뽑아서 ans에 저장
    // 데드라인을 앞으로 탐색하며 반복
    // 1일차까지 완료한 ans가 정답

    // 데드라인을 키로 가능한 컵의 갯수를 저장하는 map
    static Map<Integer, List<Integer>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new HashMap<Integer, List<Integer>>();
        int max = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            max = Math.max(max, deadLine);
            if (map.containsKey(deadLine)) {
                map.get(deadLine).add(cup);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(cup);
                map.put(deadLine, list);
            }
        }

        // 들어있는 수중 가장 큰 수를 뽑을 pq
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o2-o1;
        });

        int ans = 0;

        // 마지막 데드라인부터 앞으로 탐색
        for (int i = max; i > 0; i--) {
            if (map.containsKey(i)) {
                for (int cup : map.get(i)) {
                    pq.add(cup);
                }
            }
            
            // pq가 비어있지 않다면 풀수있는 문제가 존재함 그중 가장 컵라면을 많이 주는 문제를 뽑아 풀기
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }

        System.out.println(ans);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 : 그리디?
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<PriorityQueue<Integer>> list = new ArrayList<>(); // 각 반을 pq에 담고 전체 반을 담을 리스트 배열 생성
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(); // 각 반을 담을 pq
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                pq.add(Integer.parseInt(st.nextToken()));
            }
            list.add(pq);
        }
        // 능력치와 자신의 반을 배열로 만들어 pq에 넣기 해당 pq는 능력치 기준 정렬
        // 이 pq가 대표로 선발된 학생들을 담은 pq
        PriorityQueue<int[]> player = new PriorityQueue<>((o1,o2)->{
            return o1[0]-o2[0];
        });
        int max = Integer.MIN_VALUE; // 현재 가장 큰값
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int num = list.get(i).poll();
            if (num > max){
                max = num;
            }
            player.add(new int[] {num,i});
        }

        while (!player.isEmpty()) { // 하나씩 꺼내고
            int[] candidate = player.poll();
            ans = Math.min(ans, max - candidate[0]); // max-후보의 능력치가 갱신된다면 갱신
            if (list.get(candidate[1]).isEmpty()){ // 만약 해당 반에서 더이상 뽑을 사람이 없다면 while문 종료
                break;
            } else {
                int num = list.get(candidate[1]).poll(); // 새로운 인재 뽑기
                max = Math.max(max, num); // max갱신 여부 확인후 갱신
                player.add(new int[] {num, candidate[1]}); // 대표반 학생 목록에 넣기
            }
        }
        System.out.println(ans);
    }
}

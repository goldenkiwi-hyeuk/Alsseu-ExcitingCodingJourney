// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.io.*;

class BOJ1781_컵라면_정해준 {
    static class Ramen implements Comparable<Ramen>{
        int n;
        int end;
        int num;

        Ramen(int n,int end, int num) {
            this.n = n;
            this.end = end;
            this.num = num;
        }

        @Override
        public int compareTo(Ramen o){
            if(this.end == o.end)
                return o.num - this.num;
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws IOException{
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Ramen> list = new ArrayList<>();

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            list.add(new Ramen(i + 1, end, num));
        }
        Collections.sort(list);
        for(Ramen r : list) {
            if(pq.size() < r.end) // 아직 기한이 없어서 풀 수 있어서 무조건 추가
                pq.offer(r.num);
            else if(pq.size() == r.end) { // 푼 문제수가 마감일 경우는 하나를 빼야함
                if(pq.peek() < r.num) {
                    // pq는 오름차 순이기 때문에 앞에 있는게 적게 주는 문제
                    pq.poll(); // 그거 빼고 새로 넣음
                    pq.offer(r.num);
                    //이게 성립하는 이유는 애초에 list는 날짜를 오름차 순으로 정렬했기 때문에 마감 기한에 여유가 무조건 있음
                }
            }
        }
        long cnt = 0;
        while(!pq.isEmpty()) {
            cnt += pq.poll(); // 라면 개수 더하기
        }
        System.out.println(cnt);
    }
}

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ2109_순회강연 {
    static class Edu implements Comparable<Edu> {
        int p;
        int d;

        Edu(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Edu o) {
            if(o.p == this.p)
                return Integer.compare(this.d, o.d); // 날짜로는 오름차순
            return Integer.compare(o.p, this.p); // 페이는 오름차순
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Edu> pq = new PriorityQueue<Edu>();
        int max = 0;
        for(int i = 0; i < N; i++){
            int p = sc.nextInt();
            int d = sc.nextInt();
            pq.offer(new Edu(p, d));
            max = Math.max(max, d);
        }
        boolean[] day = new boolean[max+1];
        int answer = 0;
        while(!pq.isEmpty()){
            Edu e = pq.poll();

            // 가능한 날짜 탐색
            for(int i = e.d; i > 0; i--) {
                if(!day[i]) {
                    day[i] = true; // 날짜를 찾았을 경우 탈 출
                    answer += e.p;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}

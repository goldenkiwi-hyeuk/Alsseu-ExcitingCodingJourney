import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1202_보석도둑_정해준 {
    public static class Jwel implements Comparable<Jwel>{
        int m;
        int v;

        Jwel(int m, int v){
            this.m = m;
            this.v = v;
        }

        @Override //무게에 대해 오름차 가치에 대해 내림차
        public int compareTo(Jwel o) {
            if(this.m == o.m) {
                return o.v - this.v;
            }

            return this.m - o.m;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        long answer = 0;
        Jwel[] jwels = new Jwel[N];
        for(int i = 0; i < N; i++) {
            int m = sc.nextInt();
            int v = sc.nextInt();

            jwels[i] = new Jwel(m,v);
        } // 각 보석들을 저장
        Arrays.sort(jwels);

        int[] bags = new int[K];
        for(int i = 0; i < K; i++) {
            bags[i] = sc.nextInt();
        }
        Arrays.sort(bags); // 가방은 오름차 순으로 정렬

        // Integer로 한 이유는 넣을 수 있는 것 중에서는 무게가 관계없이 가장 가치가 높은 것만 넣으면 되기 때문
        // 만약 Jwel로 정렬하게 될 경우 가치 : 10 무게 : 1과 가치 : 12 무게 : 100 읽 경우 전자가 앞으로 오도록 정렬이 되기 때문
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int i = 0, j = 0; i < K; i++) {
            while(j < N && jwels[j].m <= bags[i]){
                pq.offer(jwels[j++].v);
            }
            if(!pq.isEmpty()) { // 가방에 다 넣은 다음에 하나를 뺀다
                answer += pq.poll();
            }

        }

        System.out.println(answer);




    }
}

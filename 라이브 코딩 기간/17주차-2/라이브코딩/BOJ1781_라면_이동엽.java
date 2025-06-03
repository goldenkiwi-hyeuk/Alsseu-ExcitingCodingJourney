package boj;

import java.util.*;

class Main1781 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<Ramen> ramens = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int d = sc.nextInt();
            long q = sc.nextLong();
            ramens.add(new Ramen(d, q));
        }
        sc.close();

        Collections.sort(ramens);
        // for (Ramen r : ramens) {
        //     System.out.println(r.d + " " + r.q);
        // }

        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Ramen ramen = ramens.get(i);
            int date = ramen.d;
            long quantity = ramen.q;

            /*
            데드라인에 상관 없이 그냥 제일 많이 주는 문제 풀면됨
            문제 푸는 시간 1
            pq에 있는 갯수가 데드라인보다 크면 풀 수 없음
            */
            pq.offer(quantity);
            if (pq.size() > date) pq.poll();

        }

        long ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.println(ans);
    }

    static class Ramen implements Comparable<Ramen>{
        int d;
        long q;

        Ramen(int d, long q) {
            this.d = d;
            this.q = q;
        }

        @Override
        public int compareTo(Ramen r) {
            return this.d - r.d;
        }

    }

}
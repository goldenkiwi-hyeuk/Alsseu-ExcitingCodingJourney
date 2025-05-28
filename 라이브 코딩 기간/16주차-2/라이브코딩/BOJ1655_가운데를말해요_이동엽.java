package boj;

import java.util.*;

class Main1655 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();

        while (n-- > 0) {
            int z = sc.nextInt();

            if (pq2.isEmpty() || z <= pq2.peek()) {
                pq2.offer(z);
            } else {
                pq1.offer(z);
            }

            if (pq2.size() > pq1.size() + 1) {
                pq1.offer(pq2.poll());
            } else if (pq2.size() < pq1.size()) {
                pq2.offer(pq1.poll());
            }

            sb.append(pq2.peek()).append("\n");
        }

        sc.close();
        System.out.println(sb.toString().trim());
    }
}
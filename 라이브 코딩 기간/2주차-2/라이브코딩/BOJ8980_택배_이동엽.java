package boj;

import java.util.*;

public class Main8980 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 2 <= N <= 2,000 마을 수
        int c = sc.nextInt(); // 1 <= C <= 10,000
        int m = sc.nextInt(); // 1 <= M <= 10,000

        PriorityQueue<parcel> pq = new PriorityQueue<>();
        // 보내는 마을 번호, 받는 마을 번호, 보낼 박스 수
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int boxes = sc.nextInt();
            pq.add(new parcel(from, to, boxes));
        }
        int[] vill = new int[n];

        int answer = 0;
        while (!pq.isEmpty()) {
            int min = c;

            parcel p = pq.poll();
            int from = p.from;
            int to = p.to;
            int boxes = p.boxes;

            for (int i = from; i < to; i++) {
                int move = Math.min(c - vill[i - 1], boxes);
                min = Math.min(move, min);
            }
            for (int i = from; i < to; i++) {
                vill[i - 1] += min;
            }
            answer += min;
        }

        System.out.println(answer);
    }
}

class parcel implements Comparable<parcel> {
    int from;
    int to;
    int boxes;

    public parcel(int from, int to, int boxes) {
        this.from = from;
        this.to = to;
        this.boxes = boxes;
    }

    @Override
    public int compareTo(parcel p) {
        /*
        빨리 털수록 많이 옮길 수 있음
        받는 순 정렬, 받는 곳 같으면 보내는 순 (오름차 순)
        */
        if (to == p.to) return from - p.from;
        return to - p.to;
    }
}
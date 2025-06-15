package programmers.무지의먹방라이브;

import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        long total = 0;
        int n = food_times.length;

        for (int f : food_times) total += f;
        if (total <= k) return -1;

        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Food(i + 1, food_times[i]));
        }

        long sum = 0;
        long prev = 0;
        long len = food_times.length;

        while (!pq.isEmpty()) {
            long now = pq.peek().time;
            long diff = now - prev;
            long spend = diff * len;

            if (spend <= k) {
                k -= spend;
                sum += diff;
                prev = now;
                pq.poll();
                len--;
            } else {
                List<Food> result = new ArrayList<>(pq);
                result.sort(Comparator.comparingInt(f -> f.idx));
                return result.get((int) (k % len)).idx;
            }
        }

        return -1;
    }

    class Food implements Comparable<Food> {
        int idx;
        int time;

        Food(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Food o) {
            return Integer.compare(this.time, o.time);
        }
    }
}
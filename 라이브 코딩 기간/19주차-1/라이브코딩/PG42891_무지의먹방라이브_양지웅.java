import java.util.*;

class Solution {

    static class Food {
        int idx, amount;

        Food(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }
    }

    public int solution(int[] food_times, long k) {
        int n = food_times.length;

        PriorityQueue<Food> pq = new PriorityQueue<>(Comparator.comparingInt(f -> f.amount));
        for (int i = 0; i < n; i++) {
            pq.offer(new Food(i + 1, food_times[i]));
        }

        long previous = 0;
        long length = n;

        while (!pq.isEmpty()) {
            long now = pq.peek().amount;
            long timeForRound = (now - previous) * length;

            if (k >= timeForRound) {
                k -= timeForRound;
                previous = now;
                pq.poll();
                length--;
            } else {
                List<Food> remain = new ArrayList<>(pq);
                remain.sort(Comparator.comparingInt(f -> f.idx));
                return remain.get((int) (k % length)).idx;
            }
        }

        return -1;
    }
}
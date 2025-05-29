import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        final int n = cards.length;
        final int target = n + 1;

        Set<Integer> free = new HashSet<>();
        int idx = 0;
        for (; idx < n / 3; idx++) free.add(cards[idx]);

        Set<Integer> paid = new HashSet<>();

        int round = 1;
        int coinLeft = coin;

        while (idx < n) {
            paid.add(cards[idx++]);
            paid.add(cards[idx++]);

            boolean passed = false;

            for (int x : free) {
                int y = target - x;
                if (y != x && free.contains(y)) {
                    free.remove(x);
                    free.remove(y);
                    passed = true;
                    break;
                }
            }

            if (!passed && coinLeft > 0) {
                for (int x : free) {
                    int y = target - x;
                    if (y != x && paid.contains(y)) {
                        paid.remove(y);
                        coinLeft--;
                        free.remove(x);
                        passed = true;
                        break;
                    }
                }
            }

            if (!passed && coinLeft > 1) {
                for (int x : paid) {
                    int y = target - x;
                    if (y != x && paid.contains(y)) {
                        paid.remove(x);
                        paid.remove(y);
                        coinLeft -= 2;
                        passed = true;
                        break;
                    }
                }
            }

            if (!passed) break;

            round++;
        }

        return round;
    }
}
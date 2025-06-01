package programmers.n더하기1카드게임;

import java.util.HashSet;
import java.util.Set;

class Solution {

    int n;

    public int solution(int coin, int[] cards) {
        int answer = 1;
        n = cards.length;
        Set<Integer> hands = new HashSet<>();
        Set<Integer> draw = new HashSet<>();

        for (int i = 0; i < n / 3; i++) {
            hands.add(cards[i]);
        }

        int idx = n / 3;

        while (idx < n) {
            for (int i = 0; i < 2; i++) {
                draw.add(cards[idx++]);
            }

            if (hands.size() >= 2 && isAble(hands, hands)) {
                answer++;
            } else if (hands.size() >= 1 && coin >= 1 && isAble(hands, draw)) {
                answer++;
                coin--;
            } else if (coin >= 2 && isAble(draw, draw)) {
                answer++;
                coin -= 2;
            } else {
                break;
            }
        }

        return answer;
    }

    boolean isAble(Set<Integer> set, Set<Integer> target) {
        Set<Integer> copy = new HashSet<>(set);
        for (int c : copy) {
            int tar = (n + 1) - c;

            if (target.contains(tar)) {
                set.remove(c);
                target.remove(tar);
                return true;
            }
        }
        return false;
    }

}
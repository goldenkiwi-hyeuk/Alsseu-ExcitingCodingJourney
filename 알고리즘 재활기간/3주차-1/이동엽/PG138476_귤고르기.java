package programmers.귤고르기;

import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int t : tangerine) {
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        list.sort(Collections.reverseOrder());

        int total = 0;
        int cnt = 0;

        for (int l : list) {
            total += l;
            cnt++;
            if (total >= k) break;
        }

        return cnt;
    }
}
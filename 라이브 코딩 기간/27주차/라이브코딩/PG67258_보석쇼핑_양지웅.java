import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;
        Map<String, Integer> cnt = new HashMap<>();
        for (String g : gems) {
            cnt.putIfAbsent(g, 0);
        }

        int total = cnt.size(), left = 0, bestStart = 0, bestEnd = n - 1;
        for (int right = 0; right < n; right++) {
            String gem = gems[right];
            cnt.put(gem, cnt.get(gem) + 1);
            if (cnt.get(gem) == 1) total--;
            while (total == 0) {
                if (right - left + 1 < bestEnd - bestStart + 1) {
                    bestStart = left;
                    bestEnd = right;
                }
                cnt.put(gems[left], cnt.get(gems[left]) - 1);
                if (cnt.get(gems[left]) == 0) total++;
                left++;
            }
        }

        return new int[]{bestStart + 1, bestEnd + 1};
    }
}
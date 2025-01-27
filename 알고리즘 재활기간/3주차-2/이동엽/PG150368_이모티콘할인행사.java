package programmers.이모티콘할인행사;

import java.util.*;

class Solution {

    static int[] percentages = { 10, 20, 30, 40 };

    public int[] solution(int[][] users, int[] emoticons) {
        List<int[]> results = new ArrayList<>();

        List<int[]> combinations = new ArrayList<>();
        comb(new int[emoticons.length], 0, combinations);

        for (int[] c : combinations) {
            int subs = 0;
            int sales = 0;

            for(int[] u : users) {
                int requiredDiscount = u[0];
                int requiredPrice = u[1];
                int userTotalCost = 0;

                for (int i = 0; i < emoticons.length; i++) {
                    if (c[i] >= requiredDiscount) {
                        userTotalCost += emoticons[i] * (100 - c[i]) / 100;
                    }
                }
                if (userTotalCost >= requiredPrice) {
                    subs++;
                } else {
                    sales += userTotalCost;
                }
            }
            results.add(new int[] {subs, sales});
        }

        Collections.sort(results, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o2[1] - o1[1];
                return o2[0] - o1[0];
            }
        });
        return results.get(0);
    }

    public void comb(int[] curr, int idx, List<int[]> combinations) {
        if (idx == curr.length) {
            combinations.add(curr.clone());
            return;
        }
        for (int p : percentages) {
            curr[idx] = p;
            comb(curr, idx + 1, combinations);
        }
    }
}
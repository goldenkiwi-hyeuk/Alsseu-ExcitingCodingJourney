class Solution {
    public int solution(int[] stones, int k) {
        int lo = 1, hi = 200_000_000;
        while (lo < hi) {
            int mid = lo + ((hi - lo + 1) >> 1);
            if (check(stones, k, mid)) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private boolean check(int[] stones, int k, int val) {
        int skip = 0;
        for (int stone : stones) {
            if (stone < val) {
                skip++;
                if (skip >= k) return false;
            } else {
                skip = 0;
            }
        }

        return true;
    }
}
package programmers.퍼즐게임챌린지;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = -1;
        for (int i = 0; i < diffs.length; i++) right = Math.max(diffs[i], right);
        int answer = right;

        while (left < right) {
            int mid = (left + right) / 2;
            if (canSolve(diffs, times, limit, mid)) {
                answer = mid;
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    boolean canSolve(int[] diffs, int[] times, long limit, int level) {
        long sum = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                sum += times[i];
            } else {
                sum += (times[i] + times[i-1]) * (diffs[i] - level) + times[i];
            }
            if (sum > limit) return false;
        }
        return sum <= limit;
    }

}
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        if (play_time.equals(adv_time)) return "00:00:00";

        int playLength = toSec(play_time);
        int advLength = toSec(adv_time);
        int[] cnt = new int[playLength + 2];

        for (String log : logs) {
            int start = toSec(log.substring(0, 8));
            int end = toSec(log.substring(9));
            cnt[start]++;
            cnt[end]--;
        }
        for (int i = 1; i <= playLength; i++) {
            cnt[i] += cnt[i - 1];
        }

        long cur = 0;
        int left = 0, bestL = 0;
        for (int right = 0; right < advLength; right++) {
            cur += cnt[right];
        }

        long ans = cur;
        for (int right = advLength; right < playLength; right++) {
            cur += cnt[right];
            cur -= cnt[left++];
            if (cur > ans) {
                ans = cur;
                bestL = left;
            }
        }

        return toStr(bestL);
    }

    private int toSec(String str) {
        char[] chars = str.toCharArray();
        int h = (chars[0] - '0') * 10 + (chars[1] - '0');
        int m = (chars[3] - '0') * 10 + (chars[4] - '0');
        int s = (chars[6] - '0') * 10 + (chars[7] - '0');
        return (h * 60 * 60) + (m * 60) + s;
    }

    private String toStr(int sec) {
        int h = sec / 3600;
        int m = (sec % 3600) / 60;
        int s = sec % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}
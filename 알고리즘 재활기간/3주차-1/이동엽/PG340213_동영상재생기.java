package programmers.동영상재생기;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // video_len 동영상 길이
        // pos 시작 위치
        // op_start 오프닝 시작 op_end 오프닝 끝
        // commands 사용자 입력
        int videoLen = intoMinutes(video_len);
        int now = intoMinutes(pos);
        int opStart = intoMinutes(op_start);
        int opEnd = intoMinutes(op_end);
        if (opStart <= now && now <= opEnd) now = opEnd;

        for (String command : commands) {
            if (command.equals("next")) {
                now = now + 10 > videoLen ? videoLen : now + 10;
            } else { // "prev"
                now = now - 10 < 0 ? 0 : now - 10;
            }
            if (opStart <= now && now <= opEnd) now = opEnd;
        }

        String hour = String.valueOf(now / 60).length() == 1 ? "0" + String.valueOf(now / 60) : String.valueOf(now / 60);
        String minute = String.valueOf(now % 60).length() == 1 ? "0" + String.valueOf(now % 60) : String.valueOf(now % 60);

        String answer = hour + ":" + minute;
        return answer;
    }

    int intoMinutes(String str) {
        String[] times = str.split(":");
        return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
    }

}
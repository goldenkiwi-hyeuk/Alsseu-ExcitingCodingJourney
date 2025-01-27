package programmers.조이스틱;

class Solution {
    public int solution(String name) {
        int n = name.length();
        int answer = 0;
        int move = n - 1;

        for (int i = 0; i < n; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);

            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }
            move = Math.min(move, i + n - next + Math.min(i, n - next));
        }

        return answer + move;
    }
}
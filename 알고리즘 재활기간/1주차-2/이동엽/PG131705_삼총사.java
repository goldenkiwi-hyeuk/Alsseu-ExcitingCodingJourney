class Solution {
    private boolean[] visited;
    private int answer;

    public int solution(int[] number) {
        answer = 0;
        visited = new boolean[number.length];
        comb(0, 0, 0, number);
        return answer;
    }

    private void comb(int idx, int depth, int sum, int[] number) {
        if (depth == 3) {
            if (sum == 0) answer++;
            return;
        }

        for (int i = idx; i < number.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                comb(i + 1, depth + 1, sum + number[i], number);
                visited[i] = false;
            }
        }
    }
}
package programmers.미로탈출명령어;

class Solution {

    int[] dx = {1, 0, 0, -1}, dy = {0, -1, 1, 0};
    char[] dr = {'d', 'l', 'r', 'u'}; // 사전 순으로 탐색
    String answer = "impossible";
    int a, b, sx, sy, gx, gy;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        a = n;
        b = m;
        sx = x - 1;
        sy = y - 1;
        gx = r - 1;
        gy = c - 1;

        dfs(sx, sy, k, "");
        // list에 모든 경우를 담아서 정렬 -> 메모리가 터졌음

        return answer;
    }


    private void dfs(int x, int y, int cnt, String str) {
        if (!answer.equals("impossible")) return; // answer이 변했으면 종료. -> 최초에 생성된 문자열이 사전순 첫번째.

        if (cnt == 0) {
            if (x == gx && y == gy) {
                answer = str;
                return;
            }
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (!isIn(nx, ny)) continue;
            if (!canReach(nx, ny, cnt - 1)) continue;

            dfs (nx, ny, cnt - 1, str + dr[d]);
        }

    }

    private boolean canReach(int x, int y, int k) {
        int dist = Math.abs(gx - x) + Math.abs(gy - y);
        return dist <= k && (k - dist) % 2 == 0; // 거리가 K보다 안쪽인지, k가 더 많으면 남은 k가 짝수인지.
    }

    private boolean isIn(int i, int j) {
        return 0 <= i && i < a && 0 <= j && j < b;
    }

}
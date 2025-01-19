package programmers.nQueen;

class Solution {

    int chess[], cnt, q;

    public int solution(int n) {
        cnt = 0;
        q = n;
        chess = new int[n];
        nQueen(cnt);
        return cnt;
    }

    void nQueen(int idx) {
        if (idx == q) {
            cnt++;
            return;
        }
        for (int i = 0; i < q; i++) {
            chess[idx] = i;
            if (isAble(idx)) nQueen(idx + 1);
        }
    }

    boolean isAble(int idx) {
        for (int i = 0; i < idx; i++) if (chess[idx] == chess[i] || Math.abs(idx - i) == Math.abs(chess[idx] - chess[i])) return false;
        return true;
    }

}
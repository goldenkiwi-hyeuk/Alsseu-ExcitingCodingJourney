import java.util.*;

class Solution {
    int N, answer;
    int[] info;
    int[] childMask;
    boolean[] checked;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        N = info.length;
        checked = new boolean[1 << N];
        // 현재 노드의 자식 노드를 1로 표시
        buildTree(edges);

        // 현재까지 방문한 노드 집합(방문 1, 미방문 0)
        // root : 0000 0000 0000 0000 0000 0000 0000 0001(0번 노드만 방문)
        int visitMask = 1 << 0;
        checked[visitMask] = true;
        // 다음으로 방문 가능한 노드 집합(1, 8)
        // 예제1 : 0000 0000 0000 0000 0000 0001 0000 0010(0번 노드의 자식 노드 방문 가능)
        int candMask = childMask[0];
        int sheep = 1, wolf = 0;
        answer = 1;

        dfs(visitMask, candMask, sheep, wolf);

        return answer;
    }

    private void buildTree(int[][] edges) {
        childMask = new int[N];
        for (int[] e : edges) {
            childMask[e[0]] |= 1 << e[1];
        }
    }

    private void dfs(int visitMask, int candMask, int sheep, int wolf) {
        // 최대 양 갱신
        answer = Math.max(answer, sheep);

        // 갈 수 있는 모든 후보 노드를 방문
        int mask = candMask;
        while (mask != 0) {
            // 후보 노드 중 가장 작은 번호의 노드 방문(가장 오른쪽 1의 위치)
            int next = Integer.numberOfTrailingZeros(mask);
            mask &= mask - 1;

            // 양 <= 늑대 가지치기
            int ns = sheep + (info[next] == 0 ? 1 : 0);
            int nw = wolf + (info[next] == 1 ? 1 : 0);
            if (ns <= nw) continue;

            // 동일한 visitMask 가지치기
            int nv = visitMask | (1 << next);
            if (checked[nv]) continue;
            checked[nv] = true;

            // 후보 집합에 현재 노드의 자식 노드를 추가하고 방문한 노드들은 제거
            int nc = (candMask | childMask[next]) & ~nv;
            dfs(nv, nc, ns, nw);
        }
    }
}
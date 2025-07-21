import java.util.*;

class Solution {

    static Map<Integer, List<Integer>> map;

    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length, m = edges.length;

        // 1. 인접 리스트 초기화
        map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(nodes[i], new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int n1 = edges[i][0], n2 = edges[i][1];
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        Set<Integer> checked = new HashSet<>();
        int t1 = 0, t2 = 0;
        for (int node : nodes) {
            if (checked.contains(node)) continue;

            Queue<Integer> q = new LinkedList<>();
            q.offer(node);
            checked.add(node);

            // 2. 각 노드의 상태 확인(홀짝 or 역홀짝)
            int equalCnt = 0, diffCnt = 0;
            while (!q.isEmpty()) {
                int cnt = q.poll();

                if (cnt % 2 == map.get(cnt).size() % 2) equalCnt++;
                else diffCnt++;

                for (int next : map.get(cnt)) {
                    if (checked.contains(next)) continue;

                    q.offer(next);
                    checked.add(next);
                }
            }

            // 3. 노드 상태가 1개만 다른 경우, 그 노드를 루트로 사용하면 홀짝 트리 or 역홀짝 트리가 됨
            if (equalCnt == 1) t1++;
            if (diffCnt == 1) t2++;
        }

        int[] answer = {t1, t2};
        return answer;
    }
}
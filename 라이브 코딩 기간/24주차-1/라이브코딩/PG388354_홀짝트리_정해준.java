import java.util.*;


public class PG388354_홀짝트리_정해준 {

    public class Solution {

        static Map<Integer, Integer> parent;

        public int[] solution(int[] nodes, int[][] edges) {
            int n = nodes.length;
            parent = new HashMap<>();
            for (int x : nodes) parent.put(x, x);

            // degree 세기
            Map<Integer, Integer> degree = new HashMap<>();
            for (int x : nodes) degree.put(x, 0);
            for (int[] e : edges) {
                int u = e[0], v = e[1];
                degree.put(u, degree.get(u) + 1);
                degree.put(v, degree.get(v) + 1);
                union(u, v);
            }

            Map<Integer, Integer> oddEvenCount = new HashMap<>();
            Map<Integer, Integer> revOddEvenCount = new HashMap<>();

            for (int x : nodes) {
                int root = find(x);
                int deg = degree.get(x);
                if ((x % 2) == (deg % 2)) {
                    oddEvenCount.put(root, oddEvenCount.getOrDefault(root, 0) + 1);
                } else {
                    revOddEvenCount.put(root, revOddEvenCount.getOrDefault(root, 0) + 1);
                }
            }

            int countOdd = 0;
            int countRev = 0;

            Set<Integer> allRoots = new HashSet<>();
            allRoots.addAll(oddEvenCount.keySet());
            allRoots.addAll(revOddEvenCount.keySet());

            for (int root : allRoots) {
                if (oddEvenCount.getOrDefault(root, 0) == 1) countOdd++;
                if (revOddEvenCount.getOrDefault(root, 0) == 1) countRev++;
            }

            return new int[]{countOdd, countRev};
        }

        static int find(int x) {
            if (parent.get(x) == x) return x;
            int root = find(parent.get(x));
            parent.put(x, root); // path compression
            return root;
        }

        static void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa != pb) parent.put(pb, pa);
        }
    }

}

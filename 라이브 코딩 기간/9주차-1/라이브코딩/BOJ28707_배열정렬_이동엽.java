package boj;

import java.util.*;

public class Main28707 {
    static class Arr implements Comparable<Arr> {
        List<Integer> arr;
        int cost;

        public Arr(List<Integer> arr, int cost) {
            this.arr = arr;
            this.cost = cost;
        }

        @Override
        public int compareTo(Arr o) {
            return this.cost - o.cost;
        }
    }

    static class Operator {
        int l, r, c;

        public Operator(int l, int r, int c) {
            this.l = l - 1;
            this.r = r - 1;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(sc.nextInt());

        int m = sc.nextInt();
        List<Operator> ops = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int c = sc.nextInt();
            ops.add(new Operator(l, r, c));
        }

        int result = dijkstra(list, ops);
        System.out.println(result);
    }

    static int dijkstra(List<Integer> list, List<Operator> ops) {
        PriorityQueue<Arr> pq = new PriorityQueue<>();
        Set<List<Integer>> visited = new HashSet<>();
        pq.offer(new Arr(list, 0));

        while (!pq.isEmpty()) {
            Arr cur = pq.poll();

            if (visited.contains(cur.arr)) continue;
            visited.add(cur.arr);

            if (isSorted(cur.arr)) return cur.cost;

            for (Operator op : ops) {
                List<Integer> next = new ArrayList<>(cur.arr);
                int tmp = next.get(op.l);
                next.set(op.l, next.get(op.r));
                next.set(op.r, tmp);

                if (!visited.contains(next)) {
                    pq.offer(new Arr(next, cur.cost + op.c));
                }
            }
        }

        return -1;
    }

    static boolean isSorted(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i - 1) > arr.get(i)) return false;
        }
        return true;
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] groupSize;

    static class Point {
        long x, y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Line {
        Point p1, p2;
        public Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Line[] lines = new Line[n];
        parent = new int[n];
        groupSize = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            groupSize[i] = 1;

            StringTokenizer st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());

            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
        }

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (isIntersect(lines[i], lines[j])) {
                    union(i, j);
                }
            }
        }

        Set<Integer> group = new HashSet<>();
        int maxGroupSize = 0;

        for (int i = 0; i < n; i++) {
            int root = find(i);
            group.add(root);
            maxGroupSize = Math.max(maxGroupSize, groupSize[root]);
        }

        System.out.println(group.size());
        System.out.println(maxGroupSize);
    }

    static int ccw(Point a, Point b, Point c) {
        long cross = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (cross > 0) return 1;
        if (cross < 0) return -1;
        return 0;
    }

    // 교차하는지 판단
    static boolean isIntersect(Line l1, Line l2) {
        Point a = l1.p1, b = l1.p2;
        Point c = l2.p1, d = l2.p2;

        int ab = ccw(a, b, c) * ccw(a, b, d);
        int cd = ccw(c, d, a) * ccw(c, d, b);

        if (ab == 0 && cd == 0) {
            return isOverlap(a, b, c, d);
        }
        return ab <= 0 && cd <= 0;
    }

    // 겹치는지 판단
    static boolean isOverlap(Point a, Point b, Point c, Point d) {
        if (Math.max(a.x, b.x) < Math.min(c.x, d.x) || Math.max(c.x, d.x) < Math.min(a.x, b.x)) return false;
        if (Math.max(a.y, b.y) < Math.min(c.y, d.y) || Math.max(c.y, d.y) < Math.min(a.y, b.y)) return false;
        return true;
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return;

        parent[rootB] = rootA;
        groupSize[rootA] += groupSize[rootB];
    }
}

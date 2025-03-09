import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        Point A = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point B = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        Point C = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        Point D = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

        if (isIntersect(A, B, C, D)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    public static int getCCW(Point a, Point b, Point c) {
        long result = (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
        if (result > 0) return 1;  // 반시계 방향
        else if (result < 0) return -1;  // 시계 방향
        else return 0;  // 일직선
    }

    // CCW 값이 0일때(일직선), 선분이 겹치는지 아닌지 판단.
    public static boolean isBetween(Point a, Point b, Point c) {
        return Math.min(a.x, b.x) <= c.x && c.x <= Math.max(a.x, b.x)
                && Math.min(a.y, b.y) <= c.y && c.y <= Math.max(a.y, b.y);
    }

    public static boolean isIntersect(Point a, Point b, Point c, Point d) {
        int ccw1 = getCCW(a, b, c);
        int ccw2 = getCCW(a, b, d);
        int ccw3 = getCCW(c, d, a);
        int ccw4 = getCCW(c, d, b);

        // 두 쌍의 CCW 값이 다르면 교차
        if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) return true;

        // 일직선에서 겹치는지 확인
        if (ccw1 == 0 && isBetween(a, b, c)) return true;
        if (ccw2 == 0 && isBetween(a, b, d)) return true;
        if (ccw3 == 0 && isBetween(c, d, a)) return true;
        if (ccw4 == 0 && isBetween(c, d, b)) return true;

        return false;

    }
}

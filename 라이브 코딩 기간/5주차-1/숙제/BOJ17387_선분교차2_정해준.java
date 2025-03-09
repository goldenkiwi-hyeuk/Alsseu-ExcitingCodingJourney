import java.util.Scanner;

public class BOJ17387_선분교차2_정해준 {
    static class Point {
        long x;
        long y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) {
        //CCW 알고리즘
        Scanner sc = new Scanner(System.in);
        Point p1 = new Point(sc.nextLong(), sc.nextLong());
        Point p2 = new Point(sc.nextLong(), sc.nextLong());
        Point p3 = new Point(sc.nextLong(), sc.nextLong());
        Point p4 = new Point(sc.nextLong(), sc.nextLong());

        long ccw1 = CCW(p1,p2,p3) * CCW(p1,p2,p4);
        long ccw2 = CCW(p3,p4,p1) * CCW(p3,p4,p2);

        if(ccw1 == 0 && ccw2 == 0) { // 둘다 직선일경우 사이사이에 배치되야 함
            if (Math.min(p1.x, p2.x) <= Math.max(p3.x, p4.x) && Math.max(p1.x, p2.x) >= Math.min(p3.x, p4.x) && Math.min(p1.y, p2.y) <= Math.max(p3.y, p4.y) && Math.max(p1.y, p2.y) >= Math.min(p3.y, p4.y))
                System.out.println(1);
            else
                System.out.println(0);
        } else if(ccw1 <= 0 && ccw2 <= 0) {
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }

    }

    public static long CCW(Point a, Point b, Point c) {
        long ans = (a.x * b.y + b.x * c.y + c.x * a.y) - ( a.x * c.y + c.x * b.y + b.x * a.y);
        if(ans == 0)
            return 0;
        if(ans > 0)
            return 1;
        return -1;
    }
}

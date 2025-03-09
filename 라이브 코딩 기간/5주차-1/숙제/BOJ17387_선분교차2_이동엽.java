package boj;

import java.util.Scanner;

public class Main17387 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long x1 = sc.nextLong();
        long y1 = sc.nextLong();
        long x2 = sc.nextLong();
        long y2 = sc.nextLong();

        long x3 = sc.nextLong();
        long y3 = sc.nextLong();
        long x4 = sc.nextLong();
        long y4 = sc.nextLong();

        System.out.println(isCross(x1, y1, x2, y2, x3, y3, x4, y4) ? 1 : 0);
        sc.close();
    }

    // 벡터 외적 (AB X AC)
    private static long outer(long x1, long y1, long x2, long y2, long x3, long y3) {
        /*
            C가 AB 왼쪽에 있으면 z > 0
            C가 오른쪽에 있으면 z < 0
            일직선 이면 z = 0
         */
        long value = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
        if (value > 0) {
            return 1;
        } else if (value < 0) {
            return -1;
        }
        return 0;
    }

    private static boolean isCross(long x1, long y1, long x2, long y2,
                                   long x3, long y3, long x4, long y4) {
        long cross1 = outer(x1, y1, x2, y2, x3, y3); // AB AC
        long cross2 = outer(x1, y1, x2, y2, x4, y4); // AB AD
        long cross3 = outer(x3, y3, x4, y4, x1, y1); // CD CA
        long cross4 = outer(x3, y3, x4, y4, x2, y2); // CD CB

        /*
        i) 교차할 때
        한 점은 선분의 왼쪽, 한 점은 오른쪽에 있어야함. : 곱이 음수
              C
        A---B |
              D 일때도 있으니 두 선분 모두에서 체크해주어야함.
         */
        boolean case1 = (cross1 * cross2 <= 0) && (cross3 * cross4 <= 0);

        // ii) 일직선 일 때
        boolean case2 = (cross1 == 0 && cross2 == 0);

        /*
        선분이 겹칠 수 있는지 체크
        AB의 X범위, CD의 X범위
        AB의 Y범위, CD의 Y범위
         */
        boolean pointCheck = (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2))
                && (Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2));

        return pointCheck && (case1 || case2);
    }
}
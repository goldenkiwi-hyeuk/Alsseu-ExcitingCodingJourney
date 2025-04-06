import java.util.HashMap;
import java.util.Scanner;

public class BOJ2162_선분그룹_정해준 {
    static int N;
    static class Line {
        int x1, x2, y1, y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }
    static Line[] lines;
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); //선분의 갯수
        lines = new Line[N];
        parent = new int[N];
        for(int i = 0; i < N; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            lines[i] = new Line(x1, y1, x2, y2);
            parent[i] = i;
        }


        for(int i = 0; i < N-1; i++) {
            for(int j = i+1; j < N; j++) {
                if(check(lines[i], lines[j])) {
                    int group1 = Find(i);
                    int group2 = Find(j);
                    parent[group1] = group2;
                }
            }
        }
        int max = 1;
        HashMap<Integer, Integer> map = new HashMap<>(); // 각 그룹별로 갯수 확인 및 그룹 종류 수 확인 용
        for(int i = 0; i < N; i++) {
            int key = Find(parent[i]);
            if(!map.containsKey(key)) {
//                System.out.println("접근");
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
                max = Math.max(max, map.get(key));
            }
        }
        System.out.println(map.size());
        System.out.println(max);


    }

    static boolean check(Line l1, Line l2) {
        int res1 = Ccw(l1, l2.x1, l2.y1) * Ccw(l1, l2.x2, l2.y2);
        int res2 = Ccw(l2, l1.x1, l1.y1) * Ccw(l2, l1.x2, l1.y2);

        if(res1 == 0 && res2 == 0) { // 일 직선일 경우
            if(Math.min(l1.x1, l1.x2) <= Math.max(l2.x1, l2.x2) && Math.min(l2.x1, l2.x2) <= Math.max(l1.x1, l1.x2) && Math.min(l1.y1, l1.y2) <= Math.max(l2.y1, l2.y2) && Math.min(l2.y1, l2.y2) <= Math.max(l1.y1, l1.y2))
                return true; // 범위 안에 있을 경우 겹침

            return false;
        }

        if(res1 <= 0 && res2 <= 0) { // 둘 다 0이거나 음수 일 경우
            return true;
        }
        // 양수는 교차 안함
        return false;
    }

    static int Ccw(Line line, int x3, int y3) {
        int x1 = line.x1, x2 = line.x2, y1 = line.y1, y2 = line.y2;
        int res = (x1*y2 + x2*y3 + x3*y1) - (x1*y3 + x2*y1 + x3*y2);

        if(res == 0)
            return 0;
        if(res > 0)
            return 1;
        return -1;
    }

    static int Find(int a) {
        if(a == parent[a])
            return a;

        return parent[a] = Find(parent[a]);
    }
}

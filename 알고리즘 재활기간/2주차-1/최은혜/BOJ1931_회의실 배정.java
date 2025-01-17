import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Point o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.y, o.y);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Point> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pq.add(new Point(x, y));
        }

        int count = 1;

        Point cur = pq.poll();
        for (int i = 1; i < N; i++) {
            Point next = pq.poll();

            if (next.y < cur.y) {
                cur = next;
            } else if (next.x >= cur.y) {
                count++;
                cur = next;
            }
        }

        System.out.println(count);
    }
}

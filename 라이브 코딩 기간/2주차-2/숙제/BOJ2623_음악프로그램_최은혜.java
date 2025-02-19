import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Box implements Comparable<Box> {
        int start, end, num;

        Box(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }

        @Override
        public int compareTo(Box o) {
            if (this.end == o.end) {
                if (this.start == o.start) {
                    return Integer.compare(o.num, this.num); // 박스 개수 내림차순
                }
                return Integer.compare(this.start, o.start); // 출발지 오름차순
            }
            return Integer.compare(this.end, o.end); // 도착지 오름차순
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 마을 수
        int C = Integer.parseInt(st.nextToken()); // 트럭 용량

        int M = Integer.parseInt(br.readLine());

        Box[] boxs = new Box[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            boxs[i] = new Box(a, b, c);
        }

        Arrays.sort(boxs);

        int result = 0;
        int[] city = new int[N + 1];

        Arrays.fill(city, C); // 처음에는 모든 마을이 트럭 최대 용량 C만큼 가능


        for (Box box : boxs) {
            int start = box.start;
            int end = box.end;
            int num = box.num;

            int maxBox = Integer.MAX_VALUE;


            for (int j = start; j < end; j++) {
                maxBox = Math.min(maxBox, city[j]);
            }


            int load = Math.min(num, maxBox);

            for (int j = start; j < end; j++) {
                city[j] -= load;
            }

            result += load;
        }

        System.out.println(result);
    }
}
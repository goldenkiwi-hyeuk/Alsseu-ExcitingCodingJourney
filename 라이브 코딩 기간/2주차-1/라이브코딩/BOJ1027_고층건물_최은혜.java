import java.io.*;
import java.util.*;

public class Main {


    static int[] buildings;
    static int result;
    static int N;

    public static void main(String args[]) throws IOException {

        // 기울기를 구한다.
        // 각 i별 빌딩 탐색하면서 -> 가장 큰 기울기를 초기화해줌. -> cnt++

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        buildings = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());

        }

        result = 0;

        for (int i = 1; i < N + 1; i++) {
            getCount(i);
        }

        System.out.println(result);


    }

    public static void getCount(int idx) {
        int cnt = 0;
        double highest = Double.NEGATIVE_INFINITY;


        // 왼쪽으로 줄여나가면서 기울기 비교
        for (int i = idx - 1; i > 0; i--) {
            double ref = (double) (buildings[i] - buildings[idx]) / (idx - i);

            // 최대 기울기보다 크다면
            if (highest < ref) {
                cnt++;
                highest = ref;
            }
        }


        highest = Double.NEGATIVE_INFINITY;

        // 오른쪽으로 늘려가면서 기울기 비교
        for (int i = idx + 1; i < N + 1; i++) {
            double ref = (double) (buildings[i] - buildings[idx]) / (i - idx);

            // 최대 기울기보다 크다면
            if (highest < ref) {
                cnt++;
                highest = ref;
            }
        }


        result = Math.max(result, cnt);
    }


}

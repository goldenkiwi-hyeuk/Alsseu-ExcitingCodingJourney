import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 기본아이디어 : DP
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        List<int[]> verticalEdge = new ArrayList<>();
        List<int[]> horizonEdge = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (arr[i][0] == arr[(i+1)%N][0]) {
                verticalEdge.add(new int[]{arr[i][1], arr[(i+1)%N][1]});
            } else {
                horizonEdge.add(new int[]{arr[i][0], arr[(i+1)%N][0]});
            }
        }

        // 특정 포인트를 지나는 선분의 갯수를 체크하는 배열
        // 0~1 선분이 있다면 event[0]++, event[1]-- 가 되는 형태
        int[] eventsV = new int[1000001];
        int[] eventsH = new int[1000001];
        for (int[] edge : verticalEdge) {
            // 음수좌표를 양수화
            int min = Math.min(edge[0], edge[1]) + 500000;
            int max = Math.max(edge[0], edge[1]) + 500000;
            eventsV[min]++;
            eventsV[max]--;
        }
        for (int[] edge : horizonEdge) {
            // 음수좌표를 양수화
            int min = Math.min(edge[0], edge[1]) + 500000;
            int max = Math.max(edge[0], edge[1]) + 500000;
            eventsH[min]++;
            eventsH[max]--;
        }
        int max_v = 0;
        int cur_v = 0;
        int max_h = 0;
        int cur_h = 0;
        for (int i = 0; i < 1000001; ++i){
            cur_v += eventsV[i];
            max_v = Math.max(max_v, cur_v);
            cur_h += eventsH[i];
            max_h = Math.max(max_h, cur_h);
        }
        System.out.println(Math.max(max_v, max_h));
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 기본아이디어 : 플로이드 와샬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int INF = 987654321;
        int[][] arr = new int[N+1][N+1];
        for (int i = 0; i<=N;++i){
            Arrays.fill(arr[i], INF);
        }

        // 연결되어있다면 1, 자기자신은 0으로 값 업데이트
        for (int i = 1; i<=N;++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j<=N;++j){
                int num = Integer.parseInt(st.nextToken());
                if (num == 1) {
                    arr[i][j] = 1;
                }
                if (i==j){
                    arr[i][j] = 0;
                }
            }
        }

        // 플로이드 와샬 진행
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                }
            }
        }

        // 여행 계획대로 움직이며 INF 이상 값이 나오는지 탐지 나온다면 해당 길은 연결되어있지 않으니 flag를 false로 업데이트
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean flag = true;
        int before = Integer.parseInt(st.nextToken());
        for (int i = 0; i<M-1;++i){
            int target = Integer.parseInt(st.nextToken());
            if (arr[before][target]<INF){
                before = target;
            } else {
                flag = false;
                break;
            }
        }

        // flag 값에 맞추어 출력
        if (flag){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

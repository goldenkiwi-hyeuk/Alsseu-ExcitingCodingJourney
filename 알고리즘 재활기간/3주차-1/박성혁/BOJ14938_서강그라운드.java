import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1];
        str = br.readLine();
        st = new StringTokenizer(str);
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[][] map = new int[N+1][N+1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(map[i],987654321);
        }
        for (int i = 0; i < R; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[start][end] = cost;
            map[end][start] = cost;
        }
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i==j){
                        map[i][j] = 0;
                    } else {
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (map[i][j] <= M) {
                    sum += arr[j];
                }
            }
            if (sum > max) {
                max = sum;
            }
        }
        System.out.println(max);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp1 = new int[N];
        int[] dp2 = new int[N];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; ++j) {
                if (arr[i] > arr[j] && dp1[i] <= dp1[j]) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }
        for (int i = N-1; i >= 0; i--) {
            for (int j = N-1; j > i; j--) {
                if (arr[i] > arr[j] && dp2[i] <= dp2[j]) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < N; i++) {
            if (max< dp1[i]+dp2[i]) {
                max = dp1[i]+dp2[i];
            }
        }
        System.out.println(max-1);
    }
}

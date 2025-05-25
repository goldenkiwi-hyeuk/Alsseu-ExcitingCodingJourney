import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A의 부분합을 모두 구해서 Map에 빈도 저장
        Map<Integer, Integer> mapA = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                mapA.put(sum, mapA.getOrDefault(sum, 0) + 1);
            }
        }

        // B의 부분합을 구하면서 동시에 A에서 보완해야 할 합의 빈도를 꺼내어 더해준다
        long ans = 0;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                ans += mapA.getOrDefault(T - sum, 0);
            }
        }
        System.out.println(ans);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N; // N일동안 N개의 운동키트
    static int K; // K만큼 감소
    static int[] kits;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        kits = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            kits[i] = Integer.parseInt(st.nextToken());
        }

        count = 0;

        checkWeight(0, 500);

        System.out.println(count);

    }


    public static void checkWeight(int day, int weight) {
        if (day == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            int nextWeight = weight + kits[i] - K;
            if (nextWeight >= 500) {
                visited[i] = true;
                checkWeight(day + 1, nextWeight);
                visited[i] = false;
            }
        }


    }

}

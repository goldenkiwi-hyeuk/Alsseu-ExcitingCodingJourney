import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 : 이분탐색

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] tails = new int[N];    // tails[len] = 종료 인덱스
        int[] prev  = new int[N];    // prev[i] = arr[i]가 이을 직전 인덱스
        int length = 0;              // 현재까지 찾은 LIS 길이
        Arrays.fill(prev, -1);
        for (int i = 0; i < N; i++) {
            // 이분 탐색: A[i]가 들어갈 위치(pos) 찾기
            int left = 0;
            int right = length - 1;
            int beforeMid = length;
            while (left <= right) { // 이분탐색
                int mid = (left + right) / 2;
                if (arr[tails[mid]] < arr[i]) {
                    left = mid + 1;
                } else {
                    beforeMid = mid;
                    right = mid - 1;
                }
            }
            if (beforeMid > 0) {
                prev[i] = tails[beforeMid - 1];
            }
            // tails 갱신
            tails[beforeMid] = i;
            // 새 최대 길이
            if (beforeMid == length) {
                length++;
            }
        }
        // 결과 역추적
        Deque<Integer> deq = new ArrayDeque<>();
        int k = tails[length - 1];
        while (k != -1) {
            deq.addLast(arr[k]);
            k = prev[k];
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(length).append('\n');
        while (!deq.isEmpty()) {
            sb.append(deq.pollLast()).append(' ');
        }
        System.out.println(sb);
    }
}

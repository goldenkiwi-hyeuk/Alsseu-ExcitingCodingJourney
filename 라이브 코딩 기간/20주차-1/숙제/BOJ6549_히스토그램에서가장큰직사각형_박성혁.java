import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 : dp + 자료구조(스택)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String s = br.readLine();
            if (s.equals("0")) {
                break;
            }
            long max = 0L;
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            // 마지막에 스택에 남아있는거 처리를 하기 위해서 마지막에 0추가
            int[] arr = new int[n+1];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Deque<Integer> deq = new ArrayDeque<>();
            for (int i = 0; i <= n; i++) {
                // 스택에 넣어야할 막대가 기존 스택 맨위보다 작다면?
                while (!deq.isEmpty() && arr[deq.peekLast()] > arr[i]) {
                    long height = arr[deq.pollLast()];     // 해당 막대의 높이
                    long width;
                    if (deq.isEmpty()) {
                        // 스택이 비어있으면, pop된 막대는 가장 왼쪽(0)부터 현재 i까지 확장 가능
                        width = i;
                    } else {
                        // 스택이 비어있지 않으면, pop된 막대의 왼쪽 경계는 스택의 top 인덱스 바로 다음
                        width = i - deq.peekLast() - 1;
                    }
                    max = Math.max(max, height * width);
                }
                deq.addLast(i);
            }
            sb.append(max).append("\n");
        }
        System.out.println(sb.toString());
    }
}

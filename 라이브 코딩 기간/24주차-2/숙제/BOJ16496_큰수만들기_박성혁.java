import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 기본아이디어 : 정렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 두문자를 앞으로 더한붙인값과 뒤로 붙인값의 크기를 비교해서 정렬하는 PQ 제작
        PriorityQueue<String> pq = new PriorityQueue<>((o1, o2) -> {
            return (o2+o1).compareTo(o1+o2);
        });

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            String str = pq.poll();
            sb.append(str);
        }

        if (sb.toString().charAt(0) == '0') {
            System.out.println(0);
        } else {
            System.out.println(sb.toString());
        }
    }
}

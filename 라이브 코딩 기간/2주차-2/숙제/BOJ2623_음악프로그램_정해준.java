import java.util.*;

public class BOJ2623_음악프로그램_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        List<Integer>[] graph = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        List<Integer> cnt = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int[] in = new int[N+1];

        for(int i = 1; i <= M; i++) {
            int n = sc.nextInt();
            int start = sc.nextInt();
            for(int j = 1; j < n; j++) { //start를 시작으로 지정
                int to = sc.nextInt();
                graph[start].add(to); //to 로 가는 간선 추가
                in[to] += 1;
                start = to;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if(in[i] == 0)
                q.offer(i); // 들어오는 간선이 없는 것을 큐에 추가
        }

        while(!q.isEmpty()) {
            int node = q.poll();
            cnt.add(node); // 총 숫자 만큼 돌았는지 확인하기 위한 cnt
            for(int next : graph[node]) {
                in[next] -= 1;
                if(in[next] == 0)
                    q.offer(next); // 만약 간선이 0이된 지점을 추가
            }
        }
        // 다 돌았을 때
        if(cnt.size() != N) {
            System.out.println("0");
        } else {
            for(int i : cnt)
                sb.append(i).append('\n');

            System.out.println(sb);
        }

    }
}

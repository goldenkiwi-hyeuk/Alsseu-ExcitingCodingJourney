// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ1766_문제집_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //문제집 수
        int M = sc.nextInt(); // 우선 풀어야 하는 문제들 수
        int[] in = new int[N + 1]; // 앞에 풀어야 하는 문제들이 있는지
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < M; i++) {
            int prev = sc.nextInt(); // 풀어야 되는 문제
            int next = sc.nextInt(); // 다음 문제
            list.get(prev).add(next);
            in[next] += 1; // 다음문제를 풀기전에 풀어야 되는 수 추가
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= N; i++) {
            if(in[i] == 0) {
                pq.offer(i); // 미리 풀어야 되는 수가 없는 경우 가장 작은 수를 먼저
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int now = pq.poll(); // 문제를 풀었다
            sb.append(now).append(" ");
            for(int next : list.get(now)) { // 이 문제가 핅수 문제인 문제의 수를 줄임
                in[next] -= 1;
                if(in[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        System.out.println(sb);
    }
}
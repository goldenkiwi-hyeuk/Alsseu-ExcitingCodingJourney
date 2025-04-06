// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ28707_배열정렬_정해준 {
    // 배열 상태를 확인 하면서 최소 가중치를 구함
    static class Arr implements Comparable<Arr>{
        int[] arr;
        int cost;

        public Arr(int[] arr, int cost){
            this.arr = arr;
            this.cost = cost;
        }

        @Override
        public int compareTo(Arr o) {
            return this.cost - o.cost;
        }
    }
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] start = new int[N+1];
        int[] answer = new int[N+1];
        for(int i = 1; i <= N; i++) {
            start[i] = sc.nextInt();
            answer[i] = start[i];
        }
        int M = sc.nextInt();
        int[][] command = new int[M][3];
        for(int i = 0; i < M; i++) {
            command[i][0] = sc.nextInt();
            command[i][1] = sc.nextInt();
            command[i][2] = sc.nextInt();
        }
        Arrays.sort(answer); // 정답 배열을 만들어 둠
        HashMap<String,Integer> hash = new HashMap<>(); // 배열과 그 배열까지의 값을 확인 
        hash.put(Arrays.toString(start), 0); // 초기값을 설정
        PriorityQueue<Arr> pq = new PriorityQueue<>();
        pq.offer(new Arr(start, 0));

        while(!pq.isEmpty()) {
            Arr node = pq.poll();

            if(Arrays.toString(node.arr).equals(Arrays.toString(answer))) {
                System.out.println(node.cost);
                return;
            }
            for(int i = 0; i < M; i++) {
                int from = command[i][0];
                int to = command[i][1];
                int cost = command[i][2];
                int[] temp = new int[N + 1];
                for(int j = 1; j <= N; j++){
                    temp[j] = node.arr[j];
                }
                temp[from] = node.arr[to];
                temp[to] = node.arr[from];
                String str = Arrays.toString(temp);

                if(hash.containsKey(str) && hash.get(str) <= node.cost + cost)
                    continue;

                // System.out.println(str);
                hash.put(str, node.cost + cost);
                pq.offer(new Arr(temp,node.cost + cost));
            }
        }
        System.out.println(-1);
    }

}
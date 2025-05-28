import java.util.*;
    class BOJ1655_가운데를말해요_정해준 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            // 배열의 중간값이 항상 앞으로 위치 할 수 있도록 2개의 우선순위 큐를 생성
            // 가운데 보다 작을 경우는 내림차 순으로 클 경우는 오름차 순으로
            PriorityQueue<Integer> q1 = new PriorityQueue<>((o1,o2) -> o2 - o1);
            PriorityQueue<Integer> q2 = new PriorityQueue<>((o1,o2) -> o1 - o2);
            StringBuilder sb = new StringBuilder();

            int N = sc.nextInt();
            for(int i = 0; i < N; i++) {
                int n = sc.nextInt();
                if(q1.size() == q2.size()) {
                    q1.offer(n);
                } else {
                    q2.offer(n);
                }
                if(!q1.isEmpty() && !q2.isEmpty()) {
                    if(q1.peek() > q2.peek()) { // 만약 중간값 보다 큰 쪽의 선두가 더 작을 경우
                        int tmp = q1.poll();
                        q1.offer(q2.poll());
                        q2.offer(tmp);
                    }
                }

                sb.append(q1.peek()).append("\n");

            }
            System.out.println(sb);
        }
}


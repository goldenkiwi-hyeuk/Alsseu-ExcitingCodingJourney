// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ4716_풍선_정해준 {
    static class Node implements Comparable<Node>{
        int n;
        int a;
        int b;
        int gap;
        Node(int n,int a, int b) {
            this.n = n;
            this.a = a;
            this.b = b;
            this.gap = Math.abs(this.a - this.b);
        }

        @Override
        public int compareTo(Node o1) {
            return o1.gap - this.gap;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true){
            int N = sc.nextInt();
            int A = sc.nextInt();
            int B = sc.nextInt();
            if(N + A + B == 0)
                break;
            PriorityQueue<Node> pq = new PriorityQueue<>();
            for(int i = 0; i < N; i++) {
                int n = sc.nextInt();
                int disA = sc.nextInt();
                int disB = sc.nextInt();
                pq.offer(new Node(n, disA, disB));
            }
            int ans = 0;
            while(!pq.isEmpty()) {
                Node now = pq.poll();
                // System.out.println( now.gap + " 현재 : " + ans);
                int n = now.n;
                if(now.a > now.b) { // B에서 풍선을 가져옴
                    if(n <= B) {
                        B -= n; // 옮긴 만큼 빼고
                        ans += n * now.b;
                    } else {
                        ans += B * now.b;
                        n -= B;
                        B = 0;
                        ans += n * now.a;
                        A -= n;
                    }
                } else { //  A에서 풍선을 가져옴
                    if(n <= A) {
                        A -= n;
                        ans += n * now.a;
                    } else {
                        ans += A * now.a;
                        n -= A;
                        A = 0;
                        ans += n * now.b;
                        B -= n;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
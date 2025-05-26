import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        N = scan.nextInt();
    }

    static void solve() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int n = 1; n <= N; n++) {
            int num = scan.nextInt();
            if (maxHeap.size() <= minHeap.size()) {
                if (!minHeap.isEmpty() && minHeap.peek() < num) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(num);
                } else {
                    maxHeap.offer(num);
                }
            } else {
                if (!maxHeap.isEmpty() && maxHeap.peek() > num) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(num);
                } else {
                    minHeap.offer(num);
                }
            }

            sb.append(maxHeap.peek()).append('\n');
        }
    }

    static void output() {
        System.out.print(sb.toString());
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
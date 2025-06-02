import java.util.*;
import java.io.*;

class Main {

    static class Problem implements Comparable<Problem> {
        int deadline;
        int prize;

        Problem(int deadline, int prize) {
            this.deadline = deadline;
            this.prize = prize;
        }

        @Override
        public int compareTo (Problem o) {
            return this.deadline - o.deadline;
        }
    }

    static FastReader scan = new FastReader();
    static int N, ans;
    static List<Problem> problems;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        N = scan.nextInt();
        problems = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int deadline = scan.nextInt();
            int prize = scan.nextInt();
            problems.add(new Problem(deadline, prize));
        }
    }

    static void solve() {
        ans = 0;
        Collections.sort(problems);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Problem p : problems) {
            minHeap.add(p.prize);

            if (minHeap.size() > p.deadline) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            ans += minHeap.poll();
        }
    }

    static void output() {
        System.out.print(ans);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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
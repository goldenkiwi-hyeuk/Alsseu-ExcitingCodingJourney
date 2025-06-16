import java.util.*;
import java.io.*;

class Main {

    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int idx = 0, size = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int read() {
            if (idx >= size) {
                try {
                    size = in.read(buffer);
                    idx = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return size == -1 ? -1 : buffer[idx++];
        }

        String next() {
            StringBuilder sb = new StringBuilder();
            int c = 0;

            while ((c = read()) != -1 && c <= ' ');
            do {
                sb.append((char) c);
            } while ((c = read()) != -1 && c > ' ');

            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static class Choice implements Comparable<Choice> {
        int start, end;

        Choice(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Choice o) {
            if (end != o.end) {
                return end - o.end;
            }
            return start - o.start;
        }
    }

    static FastScanner scan = new FastScanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static int T;

    public static void main(String[] args) {
        input();
        for (int t = 1; t <= T; t++) {
            solve();
        }
        output();
    }

    static void input() {
        T = scan.nextInt();
    }

    static void solve() {
        int N = scan.nextInt();
        int M = scan.nextInt();
        int ans = 0;
        boolean[] given = new boolean[N + 1];
        List<Choice> choices = new ArrayList<>();
        for (int i = 1; i <= M; i++) {
            choices.add(new Choice(scan.nextInt(), scan.nextInt()));
        }
        Collections.sort(choices);

        for (Choice c : choices) {
            int start = c. start;
            int end = c.end;
            while (start <= end) {
                if (!given[start]) {
                    given[start] = true;
                    ans++;
                    break;
                }
                start++;
            }
        }

        sb.append(ans).append('\n');
    }

    static void output() {
        System.out.print(sb.toString());
    }
}
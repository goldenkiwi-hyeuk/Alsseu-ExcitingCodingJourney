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

            while((c = read()) != -1 && c <= ' ');
            do {
                sb.append((char) c);
            } while ((c = read()) != -1 && c > ' ');

            return sb.toString();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static class CutResult {
        boolean valid;
        int firstCut;

        CutResult(boolean valid, int firstCut) {
            this.valid = valid;
            this.firstCut = firstCut;
        }
    }


    static FastScanner scan = new FastScanner(System.in);
    static int L, K, C, answerLength, answerFirstCut;
    static int[] positions;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        L = scan.nextInt();
        K = scan.nextInt();
        C = scan.nextInt();
        positions = new int[K + 2];

        positions[0] = 0;
        positions[K + 1] = L;
        for (int i = 1; i <= K; i++) {
            positions[i] = scan.nextInt();
        }
    }

    static void solve() {
        answerLength = L;
        answerFirstCut = 0;
        Arrays.sort(positions);

        int left = 1, right= L;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            CutResult result = check(mid);

            if (result.valid) {
                answerLength = mid;
                answerFirstCut = result.firstCut;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }

    static CutResult check(int maxLen) {
        int cnt = 0, last = L, firstCut = 0;
        List<Integer> cuts = new ArrayList<>();

        for (int i = K; i >= 0; i--) {
            int segment = last - positions[i];

            if (segment > maxLen) {
                if (i == K) return new CutResult(false, 0);
                last = positions[i + 1];
                cuts.add(positions[i + 1]);
                cnt++;

                if (last - positions[i] > maxLen) {
                    return new CutResult(false, 0);
                }
            }
        }
        if (cnt > C) return new CutResult(false, 0);
        while (cuts.size() < C && cuts.size() < K) {
            cuts.add(positions[1]);
        }

        int first = cuts.isEmpty() ? positions[1] : cuts.get(cuts.size() - 1);
        return new CutResult(true, first);
    }

    static void output() {
        System.out.println(answerLength + " " + answerFirstCut);
    }
}
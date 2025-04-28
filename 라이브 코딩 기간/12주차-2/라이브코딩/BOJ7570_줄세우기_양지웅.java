import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();

    static int N, answer;
    static int[] position;

    static void input() {
        N = scan.nextInt();
        position = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int student = scan.nextInt();
            position[student] = i;
        }
    }

    static void solve() {
        // LIS를 구해서 이 부분만 유지하고 나머지 학생들을 옮기면 가능
        // 전체 학생 수 - LIS의 길이가 정답
        int max = 1;
        int current = 1;

        for (int i = 2; i <= N; i++) {
            if (position[i-1] < position[i]) {
                current++;
            } else {
                current = 1;
            }
            max = Math.max(max, current);
        }

        answer = N - max;
    }

    static void output() {
        System.out.print(answer);
    }

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
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
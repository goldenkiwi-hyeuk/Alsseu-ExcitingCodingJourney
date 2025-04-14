import java.util.*;
import java.io.*;

class Main {

    static class Date implements Comparable<Date> {
        int month;
        int day;

        Date(int month, int day) {
            this.month = month;
            this.day = day;
        }

        @Override
        public int compareTo(Date o) {
            if (this.month != o.month) {
                return this.month - o.month;
            }
            return this.day - o.day;
        }
    }

    static class Flower implements Comparable<Flower> {
        Date start;
        Date end;

        Flower(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start != o.start) {
                return this.start.compareTo(o.start);
            }
            return this.end.compareTo(o.end);
        }
    }


    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static Flower[] flowers;

    static void input() {
        N = scan.nextInt();
        flowers = new Flower[N];

        for (int i = 0; i < N; i++) {
            Date start = new Date(scan.nextInt(), scan.nextInt());
            Date end = new Date(scan.nextInt(), scan.nextInt());
            Flower flower = new Flower(start, end);

            flowers[i] = flower;
        }
    }

    static void output() {
        System.out.print(sb.toString());
    }

    static void solve() {
        Arrays.sort(flowers);

        int count = 0;
        int idx = 0;
        Date current = new Date(3, 1);
        Date end = new Date(11, 30);

        // 11월 30일이 될때까지 확인
        while (current.compareTo(end) <= 0) {
            Date maxEnd = current;
            boolean found = false;

            // 현재 도달 가능한 날짜보다 이전에 피는 꽃 중에
            while (idx < N && flowers[idx].start.compareTo(current) <= 0) {
                // 더 많은 날 필 수 있는 꽃이 있다면 그 꽃을 선택
                if (flowers[idx].end.compareTo(maxEnd) > 0) {
                    maxEnd = flowers[idx].end;
                    found = true;
                }
                idx++;
            }

            // 현재 날짜 이후에 피는 꽃이 없음, 불가능
            if (!found) {
                sb.append(0);
                return;
            }

            // 최대 도달 가능한 날짜와 갯수 갱신
            current = maxEnd;
            count++;
        }

        sb.append(count);
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
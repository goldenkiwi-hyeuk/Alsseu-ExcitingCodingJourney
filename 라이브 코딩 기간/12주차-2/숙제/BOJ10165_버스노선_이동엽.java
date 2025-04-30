package boj;

import java.io.*;
import java.util.*;

public class Main10165 {
    static class Bus implements Comparable<Bus> {
        int start, end, idx;

        public Bus(int a, int b, int idx, int n) {
            if (a <= b) {
                this.start = a;
                this.end = b;
            } else {
                this.start = a;
                this.end = b + n;
            }
            this.idx = idx;
        }

        @Override
        public int compareTo(Bus o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<Bus> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Bus(a, b, i, n));
        }

        Collections.sort(list);

        List<Integer> result = new ArrayList<>();
        int max = -1;
        for (Bus b : list) {
            if (b.end > max) {
                result.add(b.idx);
                max = b.end;
            }
        }

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            sb.append(r).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}

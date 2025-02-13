package boj_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1202 {

    static class Dia implements Comparable<Dia> {
        int m; // 무게
        int v; // 가격

        Dia(int m, int v) {
            this.m = m;
            this.v = v;
        }

        @Override
        public int compareTo(Dia o) {
            if (this.m == o.m) {
                return Integer.compare(this.v, o.v);
            }
            return Integer.compare(this.m, o.m);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // N개의 보석
        int K = Integer.parseInt(st.nextToken()); // K개의 가방

        Dia[] dia = new Dia[N];
        int[] bag = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            dia[i] = new Dia(M, V);
        }

        Arrays.sort(dia);

        for (int i = 0; i < K; i++) {
            int C = Integer.parseInt(br.readLine());
            bag[i] = C;
        }

        Arrays.sort(bag);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long result = 0;
        int diaIdx = 0;

        for (int i = 0; i < K; i++) {

            while (diaIdx < N && dia[diaIdx].m <= bag[i]) {
                pq.add(dia[diaIdx].v);
                diaIdx++;
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }

        }

        System.out.println(result);

    }
}

// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.io.*;
class BOJ2457_공주님의정원_정해준 {
    static class Flower implements Comparable<Flower>{
        int start;
        int end;

        public Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if(this.start == o.start)
                return o.end - this.end; //내림차

            return this.start - o.start; //오름차 
        }
    }
    // static int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static void main(String[] args) throws IOException{
        // Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int Start = 100 * 3 + 1;
        int End = 100 * 12 + 1;
        Flower[] flowers = new Flower[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
             int m1 = Integer.parseInt(st.nextToken());
             int d1 = Integer.parseInt(st.nextToken());
             int m2 = Integer.parseInt(st.nextToken());
             int d2 = Integer.parseInt(st.nextToken());

             flowers[i] = new Flower(100 * m1 + d1, 100 * m2 + d2);
//            flowers[i] = new Flower(100 * 1 + 1, 100 * 12 + 12);
        }

        Arrays.sort(flowers);
        int max = 0;
        int answer = 0;
        int idx = 0;
        while(Start < End) {
            boolean flag = false; // 가장 최적을 찾았는지
            for(int i = idx; i < N; i++) { // 반복 횟수 최적화 
                if(flowers[i].start > Start)
                    break;

                if(flowers[i].end > max) {
                    max = flowers[i].end;
                    idx = i;
                    flag = true;
                }
            }

            if(flag) {
                answer++;
                Start = max;
                continue;
            }

            break;
        }

        System.out.println(max >= End ? answer : 0);



    }
}
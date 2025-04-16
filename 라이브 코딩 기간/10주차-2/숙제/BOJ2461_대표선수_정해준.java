import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2461_대표선수_정해준 {
    static class Student implements Comparable<Student> {
        int group;
        int idx;
        long power;

        public Student(int group, int idx, long power) {
            this.group = group;
            this.idx = idx;
            this.power = power;
        }

        public int compareTo(Student o) {
            return Long.compare(this.power, o.power); // 능력치 기준 오름차순
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[][] arr = new long[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++){
                arr[i][j] = Long.parseLong(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++) {
            Arrays.sort(arr[i]);
        }
        long answer = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        PriorityQueue<Student> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            pq.offer(new Student(i, 0, arr[i][0]));
            max = Math.max(max, arr[i][0]);
        }
        while(!pq.isEmpty()){
            Student now = pq.poll();
            answer = Math.min(answer, max - now.power); //최댓값과 비교
            if(now.idx + 1 == M)
                break;
            long next = arr[now.group][now.idx + 1];
            pq.offer(new Student(now.group, now.idx + 1, next));
            max = Math.max(max, next);
        }
        System.out.println(answer);
    }
}

// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class BOJ9576_책나눠주기_정해준 {
    static class Student implements Comparable<Student>{
        int start;
        int end;

        public Student(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Student o1) {
            if(o1.start == this.start) {
                return this.end - o1.end;
            }
            return o1.start - this.start; // 큰수를 내림차 순으로 
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while( T-- > 0) {
            int N  = sc.nextInt();
            int M = sc.nextInt();
            List<Student> list = new ArrayList<>();
            for(int i = 0; i < M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                list.add(new Student(a, b));
            }

            Collections.sort(list);
            int ans = 0;
            boolean[] check = new boolean[N + 1];
            for(int i = 0; i < M; i++){
                Student s = list.get(i);
                for(int j = s.end; j >= s.start; j--){
                    if(!check[j]){
                        ans++;
                        check[j] = true;
                        break;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
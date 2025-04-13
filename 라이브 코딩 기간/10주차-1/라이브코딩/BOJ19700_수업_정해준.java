// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ19700_수업_정해준 {
    static int N;
    static class Person implements Comparable<Person>{
        int high;
        int line;

        public Person(int high, int line) {
            this.high = high;
            this.line = line;
        }


        public int compareTo(Person o) {
            return o.high - this.high;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Person[] people = new Person[N];
        for(int i = 0; i < N; i++) {
            int h = sc.nextInt();
            int l = sc.nextInt();
            people[i] = new Person(h,l);
        }
        Arrays.sort(people);
        TreeSet<Integer> ts = new TreeSet<>();
        int[] cnt = new int[N+1];
        int answer = 0;
        for(int i = 0; i < N; i++) {
            Integer lower = ts.lower(people[i].line);
            if(lower == null) {
                ts.add(1);
                cnt[1]++;
                answer++;
            } else {
                cnt[lower]--;
                if(cnt[lower] == 0)
                    ts.remove(lower);
                if(cnt[lower + 1] == 0)
                    ts.add(lower+1);
                cnt[lower + 1]++;
            }
        }

        System.out.println(answer);
    }
}
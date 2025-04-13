import java.io.*;
import java.util.*;


public class Main {

    public static class Student{
        int h;
        int k;

        public Student(int h, int k){
            this.h = h;
            this.k = k;
        }
    }


    public static void main(String args[]) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<Student> list = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken()); // 키
            int k = Integer.parseInt(st.nextToken()); // 등수

            list.add(new Student(h,k));
        }

        Collections.sort(list, (o1,o2) -> {return o2.h - o1.h;});



        int result = 1;

        TreeSet<Integer> set = new TreeSet<>();
        int[] peopleNum = new int[N+1];


        set.add(1);
        peopleNum[1]++;

        for(int i=1; i<N; i++){
            Student cur = list.get(i);
            Integer val = set.lower(cur.k);

            if(val != null){
                peopleNum[val]--;
                if(peopleNum[val]==0) {
                    set.remove(val);
                }
                peopleNum[val+1]++;
                if(peopleNum[val+1]==1) set.add(val+1);


            } else{
                set.add(1);
                peopleNum[1]++;
                result++;
            }

        }

        System.out.println(result);
    }
}
import java.io.*;
import java.util.*;

// 시작일 기준 오름차순 정렬 -> 종료일 기준 내림차순 정렬
class Main {
    static int N;
    static Flower[] flowers;
    static class Flower implements Comparable<Flower>{
        int start;
        int end;

        public Flower(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o){
            if(this.start==o.start) return Integer.compare(o.end, this.end);
            return Integer.compare(this.start, o.start);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        flowers = new Flower[N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int startDay = a*100+b;
            int endDay = c*100+d;
            flowers[i] = new Flower(startDay,endDay);

        }

        Arrays.sort(flowers);

        int result = 0; // 꽃의 개수
        int idx = 0;

        int target = 301;
        while(target<=1130){
            boolean isFound = false;
            int maxEnd = 0;

            while(idx<N && flowers[idx].start <= target){
                if(flowers[idx].end > maxEnd){
                    maxEnd = flowers[idx].end;
                    isFound = true;
                }
                idx++;
            }

            if(isFound){
                result++;
                target = maxEnd;
            } else {
                result = 0;
                break;
            }

        }


        System.out.println(result);



    }
}
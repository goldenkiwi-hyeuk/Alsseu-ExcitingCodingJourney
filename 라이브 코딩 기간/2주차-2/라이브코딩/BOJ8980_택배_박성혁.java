import java.io.*;
import java.util.*;

public class Main {

    // 기본 아이디어
    // 택배라는 객체 생성이후 도착점이 작은 순서로 정렬, 만약 도착점이 동일하다면 출발점이 작은 순서로 정렬
    // 택배가 용량이하라면 더해서 배송

    private static class TackBae implements Comparable<TackBae>{
        int start, end, amount;

        public TackBae(){}

        public TackBae(int start, int end, int amount){
            this.start = start;
            this.end = end;
            this.amount = amount;
        }

        @Override
        public int compareTo(TackBae o){
            if(this.end == o.end){
                return o.start - this.start;
            }
            return this.end - o.end;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1]; // 계획 상 각 지점에서 가지고 있는 택배량을 계산한 값
        int M = Integer.parseInt(br.readLine());
        PriorityQueue<TackBae> pq = new PriorityQueue<>();
        for(int i = 0; i<M ; ++i){
            str = br.readLine();
            st = new StringTokenizer(str);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());
            pq.add(new TackBae(start, end, amount));
        }
        int ans = 0;
        while(!pq.isEmpty()){
            TackBae tackbae = pq.poll();
            int amount = tackbae.amount; // 기본 택배 용량
            for(int i = tackbae.start; i<tackbae.end ; ++i){ // 택배 이동 범위에서 전달 가능한 용량 고려
                amount = Math.min(amount,C-arr[i]);
            }
            if(amount>0){ // 전달 가능하다면 각 마을마다 택배를 더해줌
                for(int i = tackbae.start; i<tackbae.end ; ++i){
                    arr[i]+= amount;
                }
                ans += amount;
            }
        }
        System.out.println(ans);
    }
}
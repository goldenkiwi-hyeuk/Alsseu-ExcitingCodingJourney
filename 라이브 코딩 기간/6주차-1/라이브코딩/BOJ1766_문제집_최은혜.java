import java.util.*;
import java.io.*;


public class Main {

    static int N,M; // 문제의 수    
    static int[] indegree; // 진입차수
    static List<Integer>[] list;

    public static void main(String args[]) throws IOException{

        // 위상 정렬 (indgree == 0 일때를 확인)

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indegree = new int[N+1];

        list = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }


        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()); // 먼저
            int B = Integer.parseInt(st.nextToken()); // 나중에
            list[A].add(B);
            indegree[B]++;
        }


        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<N+1; i++){
            if(indegree[i]==0){
                pq.add(i);
            }
        }


        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for(int next : list[cur]){
                indegree[next]--;

                if(indegree[next]==0){
                    pq.add(next);
                }
            }
        }


        System.out.println(sb);
    }
}
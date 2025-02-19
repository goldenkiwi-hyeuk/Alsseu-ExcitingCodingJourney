import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] indegree; // i 앞에 두어야하는 가수의 개수 (진입 차수)
    static ArrayList<Integer>[] list;
    static ArrayList<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N+1];
        list = new ArrayList[N+1];

        result = new ArrayList<>();
        for(int i=0; i<N+1; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            for(int j=2; j<num+1; j++){
                int next = Integer.parseInt(st.nextToken());
                list[cur].add(next);
                indegree[next]++;
                cur = next;
            }
        }

        topology();

        if(result.size() == N){
            for(Integer i : result){
                sb.append(i).append("\n");
            }
            System.out.println(sb);
        } else {
            System.out.println(0);
        }



    }

    public static void topology(){
        Queue<Integer> que = new ArrayDeque<>();

        for(int i=1; i<N+1; i++){
            if(indegree[i] == 0){ // 진입차수가 0인 가수부터 que에 넣어 정렬.
                que.add(i);
            }
        }

        while(!que.isEmpty()){
            int cur = que.poll();
            result.add(cur);
            for(Integer i : list[cur]){
                indegree[i]--;
                if(indegree[i] == 0){
                    que.add(i);
                }
            }
        }

    }
}

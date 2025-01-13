import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[] result;
    static boolean[] visit;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        result = new int[M];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        getresult(0);


        System.out.println(sb);
    }

    public static void getresult(int idx){
        if(idx==M){
            for(int i=0; i<M; i++){
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for(int i=0; i<N; i++){
            if(visit[i] || prev == arr[i]) continue;
            result[idx] = arr[i];
            visit[i] = true;
            getresult(idx+1);
            visit[i] = false;
            prev = arr[i];
        }
    }
}

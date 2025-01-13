import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] result;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        result = new int[M];
        check = new boolean[N+1];

        getresult(0,1);

        System.out.println(sb);
    }

    public static void getresult(int idx, int start){
        if(idx==M){
            for(int i=0; i<M; i++){
                sb.append(result[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<N+1; i++){
            if(check[i]) continue;
            result[idx] = i;
            check[i] = true;
            getresult(idx+1, i+1);
            check[i] = false;
        }
    }
}

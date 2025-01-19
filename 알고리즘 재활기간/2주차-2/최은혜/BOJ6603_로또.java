import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int k;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            String input = br.readLine();
            if(input.equals("0")) break;


            st = new StringTokenizer(input);

            k = Integer.parseInt(st.nextToken());
            arr = new int[k];
            visited = new boolean[k];

            for(int i=0; i<k; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0,0);
            sb.append("\n");

        }

        System.out.println(sb);
    }

    public static void dfs(int start, int depth){
        if(depth==6){
            for(int i=0; i<k; i++){
                if(visited[i]){
                    sb.append(arr[i]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<k; i++){
            visited[i] = true;
            dfs(i+1, depth+1);
            visited[i] = false;
        }
    }
}

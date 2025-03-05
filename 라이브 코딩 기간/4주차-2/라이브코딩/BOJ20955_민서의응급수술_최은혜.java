import java.util.*;
import java.io.*;


public class Main {
    static int[] parent;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];

        for(int i=0; i<N+1; i++){
            parent[i] = i;
        }

        int result = 0;

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!union(a,b)){
                result++;
            }
        }

        int cnt = 0;
        for(int i=1; i<N+1; i++){
            if(find(i) == i){
                cnt++;
            }
        }

        result += (cnt-1);

        System.out.println(result);
    }


    public static int find (int a){
        if(parent[a]==a) return a;
        return parent[a] = find(parent[a]);
    }

    public static boolean union(int a, int b){
        int x = find(a);
        int y = find(b);
        if(x==y) return false;

        parent[x] = y;
        return true;
    }
}
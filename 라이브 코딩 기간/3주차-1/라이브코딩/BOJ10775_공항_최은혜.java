import java.io.*;
import java.util.*;

class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int G = Integer.parseInt(br.readLine()); // 게이트
        int P = Integer.parseInt(br.readLine()); // 비행기
        int[] flight = new int[P];
        for(int i=0; i<P; i++){
            flight[i] = Integer.parseInt(br.readLine());
        }

        parent = new int[G+1];
        for(int i=1;i<G+1; i++){
            parent[i] = i;
        }

        int result = 0;

        for(int i=0; i<P; i++){
            int gate = flight[i];
            int next = find(gate);
            // 그 다음 갈 수 있는 곳이 0번 게이트라면
            if(next==0) break;
            result++;
            union(next, next-1);
        }

        System.out.println(result);

    }

    public static void union(int a, int b){
        int x = find(a);
        int y = find(b);
        parent[x] = y;
    }

    public static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
}
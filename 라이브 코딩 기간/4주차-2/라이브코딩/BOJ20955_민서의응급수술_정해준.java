// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
import java.io.*;

class BOJ20955_민서의응급수술_정해준 {
    static int[] parent;
    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        // int N = Integer.parseInt(st.nextToken());
        // int M = Integer.parseInt(st.nextToken());
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int cnt = 0;
        parent = new int[N+1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < M; i++) {
            // st = new StringTokenizer(br.readLine());

            // int a = Integer.parseInt(st.nextToken());

            // int b = Integer.parseInt(st.nextToken());
            int a = sc.nextInt();
            int b = sc.nextInt();

            if(union(a,b)) {
                cnt++;
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 1; i <= N; i++) {
            int group = find(i);
            set.add(group);
        }

        cnt += set.size() - 1;

        System.out.println(cnt);

    }

    public static boolean union(int a, int b){
        int a1 = find(a);
        int b1 = find(b);
        if(a1 == b1)
            return true;
        if(a1 < b1){
            parent[a1] = b1;
        } else {
            parent[b1] = a1;
        }
        return false;

    }

    public static int find(int n) {
        if(n == parent[n])
            return n;

        return parent[n] = find(parent[n]);
    }
}
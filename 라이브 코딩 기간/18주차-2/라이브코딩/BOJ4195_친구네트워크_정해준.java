// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;


class BOJ4195_친구네트워크_정해준 {
    static int[] parent;
    static int[] size;

    static int union(int a, int b){
        // System.out.println(a + " : " + size[a] + ", " + b + " : " + size[b]);
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
            size[a] += size[b];
            size[b] = 1;
            // System.out.println("통과");
        }
        //  System.out.println(a + " : " + size[a] + ", " + b + " : " + size[b]);
        return size[a];
    }

    static int find(int x){
        if(x == parent[x])
            return x;

        return parent[x] = find(parent[x]);

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while(T-- > 0) {
            int N = sc.nextInt();
            HashMap<String, Integer> map = new HashMap();
            parent = new int[N * 2];
            size = new int[N * 2];
            int idx = 0;
            for(int i = 0; i < N; i++) {
                String str1 = sc.next();
                String str2 = sc.next();

                if(!map.containsKey(str1)) {
                    size[idx]++;
                    parent[idx] = idx;
                    map.put(str1, idx++);
                }
                if(!map.containsKey(str2)) {
                    size[idx]++;
                    parent[idx] = idx;
                    map.put(str2, idx++);
                }

                int ans = union(map.get(str1), map.get(str2));
                sb.append(ans).append("\n");
            }
        }
        System.out.println(sb);
    }
}
import java.util.*;
import java.io.*;

class Main {
    static FastScanner fs = new FastScanner();
    static StringBuilder sb = new StringBuilder();
    
    static int n;
    static List<Integer>[] adj;
    static int[] color;
    static List<Integer> blue;
    static List<Integer> white;
    
    public static void main(String[] args) {
        n = fs.nextInt();
        buildAdj();
        color();
        distributeTeam();
        printResult();
    }
    
    private static void buildAdj() {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            int m = fs.nextInt();
            for (int j = 1; j <= m; j++) {
                int p = fs.nextInt();
                adj[i].add(p);
                adj[p].add(i);
            }
        }
    }
    
    private static void color() {
        color = new int[n + 1]; // 1 : 청, -1 : 백, 0 : 미정
        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                bfs(i);
            }
        }
    }
    
    private static void bfs(int start) {
        Deque<Integer> q = new ArrayDeque<>();
        color[start] = 1;
        q.add(start);
                
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : adj[cur]) {
                if (color[next] == 0) {
                    color[next] = color[cur] * -1;
                    q.add(next);
                }
            }
        }
    }
    
    private static void distributeTeam() {
        blue = new ArrayList<>();
        white = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (color[i] == 1) blue.add(i);
            else white.add(i);
        }
    }
    
    private static void printResult() {
        sb.append(blue.size()).append('\n');
        for (int x : blue) sb.append(x).append(' ');
        sb.append('\n').append(white.size()).append('\n');
        for (int x : white) sb.append(x).append(' ');

        System.out.println(sb.toString());
    }
    
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
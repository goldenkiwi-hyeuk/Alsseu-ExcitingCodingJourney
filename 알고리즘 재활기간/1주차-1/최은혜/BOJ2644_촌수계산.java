import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2644_촌수계산 {
    static List<Integer>[] graph;
    static int a, b;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // n명의 사람

        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        visit = new boolean[n + 1];

        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int line = Integer.parseInt(br.readLine());
        for (int i = 0; i < line; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        System.out.println(getResult(a,0));

    }


    public static int getResult(int node, int depth) {
        visit[node] = true;
        if (node == b) {
            return depth;
        }

        for (int next : graph[node]) {
            if (visit[next]) continue;
            int result = getResult(next, depth + 1);
            if (result > 0) return result;
        }
        return -1;
    }
}

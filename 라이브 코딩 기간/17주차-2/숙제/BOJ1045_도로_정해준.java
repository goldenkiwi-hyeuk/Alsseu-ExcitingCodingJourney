import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1045_도로_정해준 {
    static int N,M;
    static int[] parent;
    static char[][] map;

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b){
            return false;
        }
        if(a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        parent = new int[N];
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < N; i++){
            parent[i] = i;
        }
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < N - 1; i++){ // 앞에 도시부터 탐색
            for(int j = i + 1; j < N; j++){
                if(map[i][j] == 'Y'){
                    q.offer(new int[]{i, j});
                }
            }
        }

        if(M > q.size()) { // 만약 도로의 갯수가 M개에 미치치 못할 경우 -1를 반환
            System.out.println(-1);
            return;
        }

        Queue<int[]> q2 = new LinkedList<>();
        int cnt = 0;
        int[] ans = new int[N];
        while(!q.isEmpty()){
            int[] now = q.poll();
            if(union(now[0], now[1])){
                cnt++;
                ans[now[0]]++;
                ans[now[1]]++;
            } else {
                q2.offer(now);
            }
        }

        if(cnt < N - 1){
            System.out.println(-1);
            return;
        }

        while(cnt++ < M) {
            int[] node = q2.poll();
            ans[node[0]]++;
            ans[node[1]]++;
        }
        for(int i = 0; i < N; i++) {
            sb.append(ans[i] + " ");
        }

        System.out.println(sb);


    }
}

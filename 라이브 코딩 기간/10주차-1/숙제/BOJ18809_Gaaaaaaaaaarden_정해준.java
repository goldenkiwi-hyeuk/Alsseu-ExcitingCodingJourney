import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ18809_Gaaaaaaaaaarden_정해준 {
    static class Node {
        int x;
        int y;
        int t;
        int color;

        public Node(int x, int y, int t, int color) {
            this.x = x;
            this.y = y;
            this.t = t;
            this.color = color;
        }
    }


    static int N, M, G, R;
    static int[][] map;
    static List<int[]> canDo = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int max;
    // 0은 호수,1은 뿌릴 수 없음, 2는 뿌릴 수 있음
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                //만약 배양액을 넣을 수 있으면
                if(map[i][j] ==  2) {
                    canDo.add(new int[] {i,j});
                }
            }
        }
        dfs(new int[canDo.size()],0,0,0);
        System.out.println(max);

    }

    static void dfs(int[] color, int green, int red, int idx) { // 약품을 쓰는 위치를 찾음
        if(green == G && red == R) { // 다 뿌렸을 경우
            max = Math.max(max, bfs(color));
            return;
        }
        if(idx == canDo.size()) { // 놓을 수 있는 배양곳이 없다면
            return;
        }
        // 1이면 초록색, 2이면 빨간색
        if(green < G) { // 초록을 사용할 경우
            color[idx] = 1;
            dfs(color, green + 1, red, idx + 1);
            color[idx] = 0;
        }

        if(red < R) { // 빨강을 사용할 경우
            color[idx] = 2;
            dfs(color, green, red + 1, idx + 1);
            color[idx] = 0;
        }
        //둘다 선택을 안함
        dfs(color,green, red, idx + 1);

    }

    static int bfs(int[] color) { // 약품이 흘러감
        int[][] visited = new int[N][M];
        int[][] timeCheck = new int[N][M];
        Queue<Node> q = new LinkedList<>();

        for(int i = 0; i < canDo.size(); i++) {
            if(color[i] != 0) {
                int x = canDo.get(i)[0];
                int y = canDo.get(i)[1];
                visited[x][y] = color[i];
                q.offer(new Node(x,y,0,color[i]));
            }
        }
        int cnt = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            if(visited[cur.x][cur.y] == 3) { //꽃이 있으면
                continue;
            }

            for(int i = 0; i < 4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                if(x < 0 || x >= N || y < 0 || y >= M)
                    continue;
                if(map[x][y] == 0 || visited[x][y] == 3) //호수거나 갈 수 없으면 다음
                    continue;

                if(visited[x][y] == 0) {
                    visited[x][y] = cur.color;
                    timeCheck[x][y] = cur.t + 1;
                    q.offer(new Node(x,y,cur.t + 1,cur.color));
                } else if(visited[x][y] == 1 && cur.color == 2 && timeCheck[x][y] == cur.t + 1) {
                    visited[x][y] = 3;
                    cnt++;
                } else if(visited[x][y] == 2 && cur.color == 1 && timeCheck[x][y] == cur.t + 1) {
                    visited[x][y] = 3;
                    cnt++;
                }
            }
        }


        return cnt;
    }
}

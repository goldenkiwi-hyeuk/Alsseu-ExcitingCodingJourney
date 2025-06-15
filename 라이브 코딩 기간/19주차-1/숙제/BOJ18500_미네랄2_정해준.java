import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18500_미네랄2_정해준 {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            int target = R - h;
            boolean flag = (i % 2 == 0); // 왼쪽 오른쪽 판단
            throwBar(target, flag);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.print(sb);

    }

    public static void throwBar(int r, boolean flag) {
        int start = flag ? 0 : C - 1;
        int end = flag ? C : - 1;
        int step = flag ? 1 : -1;

        for(int i = start; i != end; i += step) {
            if(map[r][i] == 'x') {
                map[r][i] = '.';
                checkFloor();
                return;
            }
        }
    }

    public static void checkFloor() {
        visited = new boolean[R][C];
        for(int i = 0; i < C; i++) {
            if (map[R - 1][i] == 'x' && !visited[R - 1][i]) {
                dfs(R - 1, i);
            }
        }

        List<int[]> floating = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'x' && !visited[i][j]) {
                    floating.add(new int[]{i, j});
                }
            }
        }

        if (floating.isEmpty()) return;

        // 모든 부유 클러스터를 . 처리
        for (int[] p : floating) {
            map[p[0]][p[1]] = '.';
        }

        int minDrop = R;
        for (int[] p : floating) {
            int r = p[0], c = p[1];
            int dist = 0;
            while (true) {
                r++;
                if (r >= R || (map[r][c] == 'x' && visited[r][c])) break;
                dist++;
            }
            minDrop = Math.min(minDrop, dist);
        }

        for (int[] p : floating) {
            map[p[0] + minDrop][p[1]] = 'x';
        }
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (!visited[nr][nc] && map[nr][nc] == 'x') {
                dfs(nr, nc);
            }
        }
    }
}

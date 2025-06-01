import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109_빵집_정해준 {
    static int R,C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1};
    static int[] dc = {1, 1, 1}; // 동, 남동, 북동 방향
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int cnt = 0;
        for(int i = 0; i < R; i++) {
            visited[i][0] = true;
            if(dfs(i, 0))
                cnt++;
        }

        System.out.println(cnt);
    }


    public static boolean dfs(int r, int c) {
        if(c == C - 1)
            return true;

        for(int i = 0; i < 3; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nc < 0 || nr >= R || nc >= C)
                continue;
            if(visited[nr][nc] || map[nr][nc] != '.')
                continue;
            visited[nr][nc] = true;
            if(dfs(nr,nc)) // 계속 이어지는지 확인
                return true;
        }


        return false;
    }
}

import java.util.*;
import java.io.*;

public class Main {

    static FastReader scan = new FastReader();

    static int N, M, ans;
    static char[][] map;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Map<Character, Integer> dirMap = new HashMap<>();
    static {
        dirMap.put('U', 0);
        dirMap.put('D', 1);
        dirMap.put('L', 2);
        dirMap.put('R', 3);
    }

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = scan.nextLine().toCharArray();
        }
    }

    static void solve() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 'S' && map[r][c] != 'C') {
                    dfs(r, c);
                }
            }
        }
    }

    static void output() {
        System.out.println(ans);
    }

    static void dfs(int r, int c) {
        int d = dirMap.get(map[r][c]);
        int nr = r + dr[d];
        int nc = c + dc[d];
        map[r][c] = 'S'; // 탐색 중

        if (map[nr][nc] == 'S') {
            ans++;
        } else if (map[nr][nc] != 'C') {
            dfs(nr, nc);
        }

        map[r][c] = 'C'; // 사이클 확인 완료
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
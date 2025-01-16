import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static List<Integer> cleaner;
    public static int[][] map;
    public static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        cleaner = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    cleaner.add(i);
                }
            }
        }
        for (int i = 0; i < T; i++) {
            spread();
            cleaning();
        }
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += map[i][j];
            }
        }
        System.out.println(sum + 2);
    }

    private static void spread() {
        Map<Integer,Integer> dust = new HashMap<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if ((map[i][j] != -1) && (map[i][j] != 0)) {
                    dust.put((i * 100 + j),map[i][j]);
                }
            }
        }
        for (Integer dustloc : dust.keySet()) {
            int dustr = dustloc / 100;
            int dustc = dustloc % 100;
            int cnt = 0;
            int dustMount = dust.get(dustloc)/5;
            for (int dir = 0; dir < 4; dir++) {
                int nr = dustr + delta[0][dir];
                int nc = dustc + delta[1][dir];
                if ((nr >= 0) && (nr < R) && (nc >= 0) && (nc < C) && (map[nr][nc] != -1)) {
                    map[nr][nc] += dustMount;
                    ++cnt;
                }
            }
            map[dustr][dustc] = map[dustr][dustc] - dustMount * cnt;
        }
    }

    private static void cleaning() {
        int r1 = cleaner.get(0);
        for (int i = r1 - 2; i >= 0; i--) {
            map[i + 1][0] = map[i][0];
        }
        for (int i = 1; i < C; ++i) {
            map[0][i - 1] = map[0][i];
        }
        for (int i = 1; i <= r1; i++) {
            map[i - 1][C - 1] = map[i][C - 1];
        }
        for (int i = C - 1; i >= 2; i--) {
            map[r1][i] = map[r1][i - 1];
        }
        map[r1][1] = 0;
        int r2 = cleaner.get(1);
        for (int i = r2 + 2; i < R; i++) {
            map[i - 1][0] = map[i][0];
        }
        for (int i = 1; i < C; i++) {
            map[R - 1][i - 1] = map[R - 1][i];
        }
        for (int i = R - 2; i >= r2; i--) {
            map[i + 1][C - 1] = map[i][C - 1];
        }
        for (int i = C - 1; i >= 2; i--) {
            map[r2][i] = map[r2][i - 1];
        }
        map[r2][1] = 0;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static int N, M, cheese, time;
    public static int[][] map;
    public static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) ++cheese;
            }
        }
        time = 0;
        while (cheese != 0) {
            melt();
        }
        System.out.println(time);
    }

    private static void melt() {
        ++time;
        int[][] temp = new int[N][M];
        map[0][0] = 2;
        Deque<Integer> que = new ArrayDeque<>();
        que.add(0);
        while (!que.isEmpty()) {
            int now = que.poll();
            int row = now / 1000;
            int col = now % 1000;
            for (int dir = 0; dir < 4; dir++) {
                int nr = row + delta[0][dir];
                int nc = col + delta[1][dir];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                    map[nr][nc] = 2;
                    que.add(nr * 1000 + nc);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) {
                    temp[i][j] = 0;
                } else if (map[i][j] == 0) {
                    temp[i][j] = 0;
                } else {
                    int cnt = 0;
                    for (int dir = 0; dir < 4; dir++) {
                        int nr = i + delta[0][dir];
                        int nc = j + delta[1][dir];
                        if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 2) {
                            ++cnt;
                        }
                    }
                    if (cnt >= 2) {
                        --cheese;
                        temp[i][j] = 0;
                    } else {
                        temp[i][j] = 1;
                    }
                }
            }
        }
        map = temp;
    }
}

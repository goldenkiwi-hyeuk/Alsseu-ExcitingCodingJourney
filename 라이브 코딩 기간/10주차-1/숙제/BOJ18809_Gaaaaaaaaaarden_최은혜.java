import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q18809 {
    static int N, M;
    static int G, R;
    static int[][] map;
    static List<Point> list = new ArrayList<>();
    static int listSize;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static int result = 0;

    static class State {
        int x;
        int y;
        int time;
        int color;

        public State(int x, int y, int time, int color) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.color = color;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) list.add(new Point(i, j));
            }
        }

        listSize = list.size();

        getComb(0, 0, 0, new int[listSize]);
        System.out.println(result);
    }


    public static void getComb(int idx, int g, int r, int[] temp) {
        if (g > G || r > R) return;
        if (idx == listSize) {
            if (g == G && r == R) {
                checkMap(temp);
            }
            return;
        }
        temp[idx] = 0;
        getComb(idx + 1, g, r, temp);

        temp[idx] = 1;
        getComb(idx + 1, g + 1, r, temp);

        temp[idx] = 2;
        getComb(idx + 1, g, r + 1, temp);
    }

    public static void checkMap(int[] temp) {
        int[][] time = new int[N][M];
        int[][] color = new int[N][M];
        Queue<State> que = new ArrayDeque<>();

        for (int i = 0; i < listSize; i++) {
            Point cur = list.get(i);
            if (temp[i] > 0) {
                color[cur.x][cur.y] = temp[i];
                time[cur.x][cur.y] = 0;
                que.add(new State(cur.x, cur.y, 0, temp[i]));
            }
        }

        int flower = 0;

        while (!que.isEmpty()) {
            Queue<State> next = new ArrayDeque<>();

            while (!que.isEmpty()) {
                State cur = que.poll();
                if (color[cur.x][cur.y] == 3) continue;

                for (int dir = 0; dir < 4; dir++) {
                    int nr = cur.x + dr[dir];
                    int nc = cur.y + dc[dir];

                    if (nr < 0 || nc < 0 || nr > N - 1 || nc > M - 1 || map[nr][nc] ==0) continue;

                    if(color[nr][nc]==0){
                        color[nr][nc] = cur.color;
                        time[nr][nc] = cur.time+1;
                        next.add(new State(nr,nc,cur.time+1, cur.color));
                    } else if((color[nr][nc] == 1 || color[nr][nc] ==2 ) && color[nr][nc] != cur.color && time[nr][nc] == cur.time+1){
                        color[nr][nc] = 3;
                        flower++;
                    }
                }
            }

            que = next;
        }

        result = Math.max(result, flower);
    }
}

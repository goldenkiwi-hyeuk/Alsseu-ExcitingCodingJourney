import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012_유기농배추 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static class Point {
        int x;
        int y;

        public Point() {
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine()); // 테스트 케이스
        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken()); // 가로길이
            int N = Integer.parseInt(st.nextToken()); // 세로 길이
            int K = Integer.parseInt(st.nextToken()); // 배추 개수

            int[][] map = new int[M][N];
            for (int num = 0; num < K; num++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[x][y] = 1;
            }

            int count = 0;

            Queue<Point> que = new ArrayDeque<>();
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) {
                        map[i][j] = -1;
                        count++;
                        que.add(new Point(i, j));
                        while (!que.isEmpty()) {
                            Point now = que.poll();

                            int nr, nc;
                            for (int dir = 0; dir < 4; dir++) {
                                nr = now.x + dr[dir];
                                nc = now.y + dc[dir];

                                if (nr < 0 || nc < 0 || nr > M - 1 || nc > N - 1) continue;
                                if (map[nr][nc] == 1) {
                                    que.add(new Point(nr, nc));
                                    map[nr][nc] = -1;
                                }
                            }

                        }
                    }
                }
            }

            System.out.println(count);
        }

    }

    public static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

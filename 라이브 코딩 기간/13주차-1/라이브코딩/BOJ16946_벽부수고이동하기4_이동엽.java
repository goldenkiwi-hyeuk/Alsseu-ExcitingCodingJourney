package boj;

import java.util.*;
import java.io.*;

class Main16946 {

    static int n, m, idx, move[][];
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static List<Integer> size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        move = new int[n][m];
        visited = new boolean[n][m];
        idx = 2;
        size = new ArrayList<>();
        size.add(0);
        size.add(0);

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        br.close();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '0' && move[i][j] == 0) {
                    int temp = bfs(i, j);
                    size.add(temp);
                    idx++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '1') {
                    Set<Integer> set = new HashSet<>();
                    int sum = 1;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (!isIn(nx, ny)) continue;
                        int region = move[nx][ny];
                        if (region >= 2 && !set.contains(region)) {
                            set.add(region);
                            sum += size.get(region);
                        }
                    }
                    sb.append(sum % 10);
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        move[x][y] = idx;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (!isIn(nx, ny)) continue;
                if (map[nx][ny] == '0' && move[nx][ny] == 0) {
                    move[nx][ny] = idx;
                    q.offer(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}

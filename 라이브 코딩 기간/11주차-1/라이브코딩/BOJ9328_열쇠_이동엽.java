package boj;

import java.util.*;

class Main9328 {

    static int h, w;
    static char[][] building;
    static boolean[][] visited;
    static Set<Character> keys;
    static Queue<int[]> q;
    static List<int[]>[] list = new ArrayList[26];
    static final int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            h = sc.nextInt();
            w = sc.nextInt();
            building = new char[h + 2][w + 2];
            visited = new boolean[h + 2][w + 2];
            keys = new HashSet<>();
            q = new LinkedList<>();
            for (int i = 0; i < 26; i++) {
                list[i] = new ArrayList<>();
            }

            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(building[i], '.');
            }
            for (int i = 1; i <= h; i++) {
                String line = sc.next();
                for (int j = 1; j <= w; j++) {
                    building[i][j] = line.charAt(j - 1);
                }
            }

            String str = sc.next();
            if (!str.equals("0")) {
                for (char c : str.toCharArray()) {
                    keys.add(Character.toUpperCase(c));
                }
            }

            sb.append(bfs()).append('\n');
        }

        System.out.print(sb);
        sc.close();
    }

    static int bfs() {
        int cnt = 0;
        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= h + 2 || ny < 0 || ny >= w + 2) continue;
                if (visited[nx][ny]) continue;

                char c = building[nx][ny];
                if (c == '*') continue;

                visited[nx][ny] = true;

                if (c == '.') {
                    q.offer(new int[]{nx, ny});
                } else if (c == '$') {
                    cnt++;
                    q.offer(new int[]{nx, ny});
                } else if ('a' <= c && c <= 'z') {
                    char key = Character.toUpperCase(c);
                    building[nx][ny] = '.';
                    if (!keys.contains(key)) {
                        keys.add(key);
                        int idx = key - 'A';
                        for (int[] doorPos : list[idx]) {
                            q.offer(doorPos);
                        }
                        list[idx].clear();
                    }
                    q.offer(new int[]{nx, ny});
                } else { // 'A' <= c <= 'Z'
                    if (keys.contains(c)) {
                        building[nx][ny] = '.';
                        q.offer(new int[]{nx, ny});
                    } else {
                        list[c - 'A'].add(new int[]{nx, ny});
                    }
                }
            }
        }

        return cnt;
    }
}

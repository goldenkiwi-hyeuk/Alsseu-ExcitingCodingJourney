import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static final int[][] DIRS = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M;
    static int[][] map;
    static int[][] group;
    static Map<Integer, Integer> groupSize = new HashMap<>();

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        map = new int[N][M];
        group = new int[N][M];

        for (int r = 0; r < N; r++) {
            String line = scan.nextLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = line.charAt(c) - '0';
            }
        }
    }

    static void output() {
        System.out.print(sb.toString());
    }

    static void solve() {
        int groupId = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!isCheck(r, c)) bfs(r, c, groupId++);
            }
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] != 1) sb.append(0);
                else {
                    Set<Integer> groups = new HashSet<>();
                    int cnt = 1; // 자기 자신
                    for (int[] d : DIRS) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (isRange(nr, nc)) {
                            int id = group[nr][nc];
                            if (id > 0 && groups.add(id)) {
                                cnt += groupSize.get(id);
                            }
                        }
                    }
                    sb.append(cnt % 10);
                }
            }
            sb.append('\n');
        }
    }

    static void bfs(int r, int c, int groupId) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> cells = new ArrayList<>();
        q.offer(new int[] {r, c});
        group[r][c] = groupId;
        int size = 1;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cr = cur[0];
            int cc = cur[1];
            cells.add(new int[]{cr, cc});

            for (int[] d : DIRS) {
                int nr = cur[0] + d[0];
                int nc = cur[1] + d[1];
                if (isRange(nr, nc) && !isCheck(nr, nc)) {
                    group[nr][nc] = groupId;
                    q.offer(new int[]{nr, nc});
                    size++;
                }
            }
        }

        groupSize.put(groupId, size);
    }

    static boolean isRange(int r, int c) {
        return (0 <= r && r < N && 0 <= c && c < M);
    }

    static boolean isCheck(int r, int c) {
        return (map[r][c] != 0 || group[r][c] != 0);
    }

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
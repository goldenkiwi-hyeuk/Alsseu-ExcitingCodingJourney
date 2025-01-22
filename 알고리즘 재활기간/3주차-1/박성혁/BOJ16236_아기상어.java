import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int[][] map;
    public static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    public static Map<Integer, Set<Integer>> fishList;
    public static int ans, N;

    private static class Shark {
        int level, row, col, stack;

        Shark(int level, int row, int col, int stack) {
            this.level = level;
            this.row = row;
            this.col = col;
            this.stack = stack;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "level=" + level +
                    ", row=" + row +
                    ", col=" + col +
                    ", stack=" + stack +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        fishList = new HashMap<Integer, Set<Integer>>();
        ans = 0;
        Shark shark = null;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    if (map[i][j] == 9) {
                        shark = new Shark(2, i, j, 0);
                        map[i][j] = 0;
                    } else {
                        if (fishList.containsKey(map[i][j])) {
                            fishList.get(map[i][j]).add(i * 100 + j);
                        } else {
                            Set<Integer> set = new HashSet<>();
                            set.add(i * 100 + j);
                            fishList.put(map[i][j], set);
                        }
                    }
                }
            }
        }

        while (true) {
            int[] num = findfish(shark);
            if (num[0] == Integer.MAX_VALUE) {
                break;
            } else {
                ans += num[0];
                shark.row = num[1];
                shark.col = num[2];
                shark.stack += 1;
                if (shark.stack == shark.level) {
                    shark.stack = 0;
                    shark.level += 1;
                }
                map[shark.row][shark.col] = 0;
                fishList.get(num[3]).remove(num[1] * 100 + num[2]);
                if (fishList.get(num[3]).size() == 0) {
                    fishList.remove(num[3]);
                }
            }
        }
        System.out.println(ans);
    }

    private static int[] findfish(Shark shark) {
        int min = Integer.MAX_VALUE;
        int minr = -1;
        int minc = -1;
        int fishSize = -1;
        for (int i = 1; i < shark.level; i++) {
            if (fishList.containsKey(i)) {
                for (int fishLoc : fishList.get(i)) {
                    if (map[fishLoc / 100][fishLoc % 100] == 0) continue;
                    int cnt = findroute(shark, fishLoc / 100, fishLoc % 100);
                    if (cnt < min) {
                        min = cnt;
                        minr = fishLoc / 100;
                        minc = fishLoc % 100;
                        fishSize = i;
                    } else if (cnt == min) {
                        if (fishLoc / 100 < minr) {
                            minr = fishLoc / 100;
                            minc = fishLoc % 100;
                        } else if (fishLoc / 100 == minr) {
                            if (fishLoc % 100 < minc) {
                                minr = fishLoc / 100;
                                minc = fishLoc % 100;
                            }
                        }
                    }
                }
            }
        }
        return new int[]{min, minr, minc, fishSize};
    }

    private static int findroute(Shark shark, int fishR, int fishC) {
        int min = Integer.MAX_VALUE;
        Set<Integer> set = new HashSet<>();
        Deque<int[]> que = new ArrayDeque<>();
        set.add(shark.row * 100 + shark.col);
        que.addLast(new int[]{shark.row, shark.col, 0});
        out:
        while (!que.isEmpty()) {
            int[] cur = que.pollFirst();
            for (int dir = 0; dir < 4; ++dir) {
                int nr = cur[0] + delta[0][dir];
                int nc = cur[1] + delta[1][dir];
                if (set.contains(nr * 100 + nc)) continue;
                if ((nr >= 0) && (nr < N) && (nc >= 0) && (nc < N) && (map[nr][nc] <= shark.level)) {
                    if ((nr == fishR) && (nc == fishC) && (min > cur[2] + 1)) {
                        min = cur[2] + 1;
                        break out;
                    }
                    que.addLast(new int[]{nr, nc, cur[2] + 1});
                    set.add(nr * 100 + nc);
                }
            }
        }
        return min;
    }
}

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int score = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static final int empty = -2;
    static final int rainbow = 0;

    static class BlockGroup implements Comparable<BlockGroup> {
        List<int[]> blocks;
        int rainbowCount;
        int basicR, basicC;

        BlockGroup(List<int[]> blocks, int rainbowCount, int basicR, int basicC) {
            this.blocks = blocks;
            this.rainbowCount = rainbowCount;
            this.basicR = basicR;
            this.basicC = basicC;
        }

        int size() {
            return blocks.size();
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.size() != o.size()) return o.size() - this.size();
            if (this.rainbowCount != o.rainbowCount) return o.rainbowCount - this.rainbowCount;
            if (this.basicR != o.basicR) return o.basicR - this.basicR;
            return o.basicC - this.basicC;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            BlockGroup bestGroup = findBestGroup();
            if (bestGroup == null || bestGroup.size() < 2) break;

            removeGroup(bestGroup);
            score += bestGroup.size() * bestGroup.size();

            applyGravity();

            rotateMap();

            applyGravity();
        }

        System.out.println(score);
    }

    static BlockGroup findBestGroup() {
        visited = new boolean[N][N];
        PriorityQueue<BlockGroup> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    BlockGroup group = bfs(i, j);
                    if (group != null && group.size() >= 2) {
                        pq.offer(group);
                    }
                }
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }

    static BlockGroup bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> blocks = new ArrayList<>();
        boolean[][] tempVisited = new boolean[N][N];
        int color = map[x][y];
        int rainbowCount = 0;

        q.offer(new int[]{x, y});
        tempVisited[x][y] = true;
        visited[x][y] = true;
        blocks.add(new int[]{x, y});
        int standardRow = x, standardCol = y;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dr[dir];
                int ny = cur[1] + dc[dir];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || tempVisited[nx][ny]) continue;
                if (map[nx][ny] == rainbow || map[nx][ny] == color) {
                    q.offer(new int[]{nx, ny});
                    tempVisited[nx][ny] = true;
                    blocks.add(new int[]{nx, ny});

                    if (map[nx][ny] == rainbow) {
                        rainbowCount++;
                    } else {
                        visited[nx][ny] = true;
                        if (nx < standardRow || (nx == standardRow && ny < standardCol)) {
                            standardRow = nx;
                            standardCol = ny;
                        }
                    }
                }
            }
        }

        return new BlockGroup(blocks, rainbowCount, standardRow, standardCol);
    }

    static void removeGroup(BlockGroup group) {
        for (int[] b : group.blocks) {
            map[b[0]][b[1]] = empty;
        }
    }

    static void applyGravity() {
        for (int j = 0; j < N; j++) {
            for (int i = N - 2; i >= 0; i--) {
                if (map[i][j] >= 0 || map[i][j] == rainbow) {
                    int nx = i;
                    while (true) {
                        if (nx + 1 >= N || map[nx + 1][j] != empty) break;
                        map[nx + 1][j] = map[nx][j];
                        map[nx][j] = empty;
                        nx++;
                    }
                }
            }
        }
    }

    static void rotateMap() {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[N - 1 - j][i] = map[i][j];
            }
        }
        map = newMap;
    }
}


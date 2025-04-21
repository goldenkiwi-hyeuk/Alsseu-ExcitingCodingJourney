import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static final int[][] DIRS = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int R, C, M;
    static Shark[][] map;
    static List<Shark> sharks;

    // 상어 객체
    static class Shark {
        int r, c, s, d, z;
        boolean isAlive;

        Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.isAlive = true;
        }

        // 방향 전환
        public void reversDir() {
            switch (d) {
                case 1 : d = 2;
                    break;
                case 2 : d = 1;
                    break;
                case 3 : d = 4;
                    break;
                case 4 : d = 3;
                    break;
            }
        }

        // 이동
        public void move() {
            int distance = s;
            // 1. 이동에 필요한 정도로만 이동 거리 줄여주기
            switch (d) {
                case 1:
                case 2:
                    distance %= 2 * (R - 1);
                    break;
                case 3:
                case 4:
                    distance %= 2 * (C - 1);
                    break;
            }

            for (int i = 0; i < distance; i++) {
                int nr = r + DIRS[d][0];
                int nc = c + DIRS[d][1];

                // 범위를 벗어나면 방향 전환 후 이동
                if (nr < 1 || nr > R || nc < 1 || nc > C) {
                    reversDir();
                    nr = r + DIRS[d][0];
                    nc = c + DIRS[d][1];
                }

                r = nr;
                c = nc;
            }
        }
    }

    static void input() {
        R = scan.nextInt();
        C = scan.nextInt();
        M = scan.nextInt();
        map = new Shark[R + 1][C + 1];
        sharks = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            int r = scan.nextInt();
            int c = scan.nextInt();
            int s = scan.nextInt();
            int d = scan.nextInt();
            int z = scan.nextInt();

            Shark shark = new Shark(r, c, s, d, z);
            map[r][c] = shark;
            sharks.add(shark);
        }
    }

    static void output() {
        System.out.print(sb.toString());
    }

    static void solve() {
        int res = 0;
        int time = 0;

        while (time < C) {
            time++;
            // 1. 잡을 상어 찾기
            for (int r = 1; r <= R; r++) {
                if (map[r][time] != null) {
                    Shark shark = map[r][time];
                    res += shark.z;
                    shark.isAlive = false;
                    map[r][time] = null;
                    break;
                }
            }

            // 2. 상어 이동
            // 이동 후 맵
            Shark[][] newMap = new Shark[R + 1][C + 1];

            for (Shark shark : sharks) {
                // 죽은 상어는 통과
                if (!shark.isAlive) continue;

                // 현재 위치의 상어와 같은 상어가 아니라면 이미 죽은 상어
                if (map[shark.r][shark.c] == shark) {
                    map[shark.r][shark.c] = null;
                }

                shark.move();

                // 새 위치에 이미 상어가 있다면 크기 비교 후 제거
                if (newMap[shark.r][shark.c] != null) {
                    Shark existing = newMap[shark.r][shark.c];

                    if (shark.z > existing.z) {
                        existing.isAlive = false;
                        newMap[shark.r][shark.c] = shark;
                    } else {
                        shark.isAlive = false;
                    }
                } else {
                    newMap[shark.r][shark.c] = shark;
                }
            }

            // 맵 교환
            map = newMap;
        }

        sb.append(res);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
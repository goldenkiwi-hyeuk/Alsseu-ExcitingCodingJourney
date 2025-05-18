import java.util.*;

public class BOJ23290_마법사상어와복제_정해준 {
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int[] sdx = {-1, 0, 1, 0};
    static int[] sdy = {0, -1, 0, 1};

    static List<Integer>[][] fishMap = new ArrayList[4][4];
    static List<Integer>[][] copyMap = new ArrayList[4][4];
    static int[][] smell = new int[4][4];

    static int sharkX, sharkY;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();  // 물고기
        int S = sc.nextInt();  // 턴 수

        // 초기화
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            int fx = sc.nextInt() - 1;
            int fy = sc.nextInt() - 1;
            int d = sc.nextInt() - 1;
            fishMap[fx][fy].add(d);
        }

        sharkX = sc.nextInt() - 1;
        sharkY = sc.nextInt() - 1;

        while (S-- > 0) {
            copyFish();           // 복제
            moveFish();           // 물고기 이동
            moveShark();          // 상어 이동
            decreaseSmell();      // 냄새 감소
            applyCopiedFish();    // 복제된 물고기 반영
        }

        System.out.println(countFish());  // 최종 물고기 수
    }

    static void copyFish() {
        copyMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyMap[i][j] = new ArrayList<>(fishMap[i][j]);
            }
        }
    }

    static void moveFish() {
        List<Integer>[][] newMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int d : fishMap[x][y]) {
                    boolean moved = false;
                    for (int i = 0; i < 8; i++) {
                        int nd = (d - i + 8) % 8;
                        int nx = x + dx[nd];
                        int ny = y + dy[nd];

                        if (inRange(nx, ny) && !(nx == sharkX && ny == sharkY) && smell[nx][ny] == 0) {
                            newMap[nx][ny].add(nd);
                            moved = true;
                            break;
                        }
                    }
                    if (!moved) newMap[x][y].add(d);
                }
            }
        }
        fishMap = newMap;
    }

    static void moveShark() {
        int max = -1;
        int bestDir[] = new int[3];
        List<int[]> candidates = new ArrayList<>();

        // 3번 이동
        for (int d1 = 0; d1 < 4; d1++) {
            for (int d2 = 0; d2 < 4; d2++) {
                for (int d3 = 0; d3 < 4; d3++) {
                    boolean[][] visited = new boolean[4][4];
                    int count = 0;
                    int nx = sharkX;
                    int ny = sharkY;
                    boolean valid = true;

                    int[] dirs = {d1, d2, d3};
                    for (int d : dirs) {
                        nx += sdx[d];
                        ny += sdy[d];
                        if (!inRange(nx, ny)) {
                            valid = false;
                            break;
                        }
                        if (!visited[nx][ny]) {
                            count += fishMap[nx][ny].size();
                            visited[nx][ny] = true;
                        }
                    }
                    if (!valid) continue;
                    if (count > max) {
                        max = count;
                        bestDir = new int[]{d1, d2, d3};
                    } else if (count == max) {
                        int[] cur = {d1, d2, d3};
                        if (compareDirs(cur, bestDir) < 0) {
                            bestDir = cur;
                        }
                    }
                }
            }
        }

        // 이동 , 물고기 제거 , 냄새 남기기
        for (int d : bestDir) {
            sharkX += sdx[d];
            sharkY += sdy[d];
            if (fishMap[sharkX][sharkY].size() > 0) {
                fishMap[sharkX][sharkY].clear();
                smell[sharkX][sharkY] = 3;  // 현재 턴 포함 3 → 다음턴에 -1
            }
        }
    }

    static void decreaseSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smell[i][j] > 0) smell[i][j]--;
            }
        }
    }

    static void applyCopiedFish() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishMap[i][j].addAll(copyMap[i][j]);
            }
        }
    }

    static int countFish() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                count += fishMap[i][j].size();
            }
        }
        return count;
    }

    static boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 4 && y < 4;
    }

    // 비교
    static int compareDirs(int[] a, int[] b) {
        for (int i = 0; i < 3; i++) {
            if (a[i] != b[i])
                return a[i] - b[i];
        }
        return 0;
    }
}

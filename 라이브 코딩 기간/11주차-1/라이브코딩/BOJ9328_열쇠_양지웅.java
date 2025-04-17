import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static final int[][] DIRS =  new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int T, H, W, ans;
    static char[][] building;
    static boolean[][] visited;
    static List<int[]> entrance;
    static Set<Character> keys, newKeys;
    static Map<Character, List<int[]>> doorToPos;

    static void input() {
        H = scan.nextInt();
        W = scan.nextInt();
        building = new char[H][W];
        visited = new boolean[H][W];
        entrance = new ArrayList<>();
        keys = new HashSet<>();

        // 빌딩 입력
        for (int h = 0; h < H; h++) {
            building[h] = scan.nextLine().toCharArray();
        }

        // 키 입력
        String keyInput = scan.nextLine();
        if (!keyInput.equals("0")) {
            for (char key : keyInput.toCharArray()) {
                keys.add(key);
            }
        }
    }

    static void output() {
        System.out.println(sb.toString());
    }

    static void solve() {
        ans = 0;
        doorToPos = new HashMap<>();
        newKeys = new HashSet<>();

        // 입구 확인
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                if (h == 0 || h == H - 1 || w == 0 || w == W - 1) {
                    if (building[h][w] != '*') {
                        entrance.add(new int[]{h, w});
                    }
                }
            }
        }

        // 현재 갈 수 있는 모든 곳 DFS 탐색
        for (int[] e : entrance) {
            if (visited[e[0]][e[1]]) continue;
            dfs(e[0], e[1]);
        }

        // 새로운 열쇠가 있는 경우, 해당 문을 열고 갈 수 있는 곳 확인
        while (!newKeys.isEmpty()) {
            Set<Character> currentKeys = new HashSet<>(newKeys);
            newKeys.clear();

            for (char k : currentKeys) {
                char door = Character.toUpperCase(k);
                if (doorToPos.containsKey(door)) {
                    for (int[] pos : doorToPos.get(door)) {
                        dfs(pos[0], pos[1]);
                    }
                }
            }
        }

        sb.append(ans).append('\n');
    }

    static void dfs(int h, int w) {
        visited[h][w] = true;
        char current = building[h][w];

        // 문서
        if (current == '$') {
            ans++;
            // 열쇠
        } else if (Character.isLowerCase(current)) {
            if (!keys.contains(current)) {
                keys.add(current);
                newKeys.add(current);
            }
            // 문
        } else if (Character.isUpperCase(current)) {
            // 키가 없는 경우, 문 위치만 저장 -> 나중에 키 있으면 들어옴
            if (!keys.contains(Character.toLowerCase(current))) {
                doorToPos.putIfAbsent(current, new ArrayList<>());
                doorToPos.get(current).add(new int[]{h, w});
                return;
            }
        }

        // 다음 갈 수 있는 곳 확인
        for (int[] d : DIRS) {
            int nh = h + d[0];
            int nw = w + d[1];
            if (isRange(nh, nw) && !visited[nh][nw] && building[nh][nw] != '*') {
                dfs(nh, nw);
            }
        }
    }

    // 빌딩 범위 확인
    static boolean isRange(int h, int w) {
        return (0 <= h && h < H && 0 <= w && w < W);
    }

    public static void main(String[] args) {
        T = scan.nextInt();
        for (int t = 1; t <= T; t++) {
            input();
            solve();
        }
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

        String nextLine() {
            String str = "";

            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return str;
        }
    }
}
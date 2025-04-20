import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int h, w, ans;
    static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static char[][] map;
    static boolean[][] visited;
    static Set<Integer> haveKeys;
    static Map<Integer, List<Integer>> blockList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; ++tc) {
            ans = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            for (int r = 0; r < h; ++r) {
                String str = br.readLine();
                for (int c = 0; c < w; ++c) {
                    map[r][c] = str.charAt(c);
                }
            }

            visited = new boolean[h][w];
            blockList = new HashMap<>();
            haveKeys = new HashSet<>();
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                haveKeys.add((int) str.charAt(i));
            }
            for (int r = 0; r < h; ++r) { // 왼쪽 변 탐색
                checkMap(r, 0);
            }
            for (int c = 0; c < w; ++c) { // 위쪽 변 탐색
                checkMap(0, c);
            }
            for (int r = 0; r < h; ++r) { // 오른쪽 변 탐색
                checkMap(r, w-1);
            }
            for (int c = 0; c < w; ++c) { // 아래쪽 변 탐색
                checkMap(h-1, c);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void checkMap(int r, int c) {
        if (inRange(r, c)) {
            if (map[r][c] == '.') {
                visited[r][c] = true;
                for (int dir = 0; dir < 4; ++dir) {
                    int nr = r + delta[0][dir];
                    int nc = c + delta[1][dir];
                    checkMap(nr, nc);
                }
            } else if (map[r][c] == '$') { // 문서를 만났을떄
                ++ans;
                visited[r][c] = true;
                for (int dir = 0; dir < 4; ++dir) {
                    int nr = r + delta[0][dir];
                    int nc = c + delta[1][dir];
                    checkMap(nr, nc);
                }
            } else if ((int) map[r][c] < 97) { // 문을 만난 케이스
                if (haveKeys.contains((int) map[r][c] + 32)) { // 문의 열쇠를 갖고 있을때
                    visited[r][c] = true;
                    for (int dir = 0; dir < 4; ++dir) {
                        int nr = r + delta[0][dir];
                        int nc = c + delta[1][dir];
                        checkMap(nr, nc);
                    }
                } else { // 문의 열쇠가 없을 때
                    if (blockList.get((int) map[r][c]) == null || blockList.get((int) map[r][c]).isEmpty()) {
                        List<Integer> list = new ArrayList<>();
                        list.add(r * 1000 + c);
                        blockList.put((int) map[r][c], list);
                    } else {
                        List<Integer> list = blockList.get((int) map[r][c]);
                        list.add(r * 1000 + c);
                        blockList.put((int) map[r][c], list);
                    }
                }
            } else if ((int) map[r][c] >= 97) { // 키를 만났을때
                visited[r][c] = true;
                haveKeys.add((int) map[r][c]); // 키 목록 추가
                if (blockList.get((int) map[r][c] - 32) != null && !blockList.get((int) map[r][c] - 32).isEmpty()) { // 이미 문을 만난적이 있는 경우
                    List<Integer> list = blockList.get((int) map[r][c] - 32);
                    haveKeys.add((int) map[r][c]);
                    for (int loc : list) {
                        int nr = loc / 1000;
                        int nc = loc % 1000;
                        checkMap(nr, nc);
                    }
                    blockList.put((int) map[r][c] - 32, new ArrayList<>());
                }
                for (int dir = 0; dir < 4; ++dir) {
                    int nr = r + delta[0][dir];
                    int nc = c + delta[1][dir];
                    checkMap(nr, nc);
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        if ((r >= 0) && (r < h) && (c >= 0) && (c < w) && (map[r][c] != '*') && (!visited[r][c])) {
            return true;
        }
        return false;
    }

}

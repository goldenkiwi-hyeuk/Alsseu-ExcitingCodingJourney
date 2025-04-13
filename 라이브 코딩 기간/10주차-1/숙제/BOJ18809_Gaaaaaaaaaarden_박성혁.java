import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본아이디어 : 구현
    
    static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static int N, M, Green, Red;
    static List<Integer> candidate;
    static int max;
    static int[][] map;
    static Set<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Green = Integer.parseInt(st.nextToken());
        Red = Integer.parseInt(st.nextToken());
        candidate = new ArrayList<Integer>();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    candidate.add(i * 100 + j);
                }
            }
        }
        max = 0;
        int[] select = new int[Green + Red];
        set = new HashSet<Integer>();
        go(select, 0, -1);
        System.out.println(max);
    }

    private static void go(int[] select, int idx, int beforeidx) { // 배양액 초기 위치 후보 선정
        if (idx == Green + Red) {
            int[][] temp = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = map[i][j];
                }
            }
            go2(select, temp);
            return;
        }
        if (idx == 0) { // 빨강 배양액 처음 뽑기 or 초록 배양액 처음 뽑기
            for (int i = 0; i < candidate.size(); i++) {
                select[idx] = candidate.get(i);
                set.add(candidate.get(i));
                go(select, idx + 1, i);
                set.remove(candidate.get(i));
            }
        } else if (idx == Green) {
            for (int i = 0; i < candidate.size(); i++) {
                if (!set.contains(candidate.get(i))) {
                    select[idx] = candidate.get(i);
                    set.add(candidate.get(i));
                    go(select, idx + 1, i);
                    set.remove(candidate.get(i));
                }
            }
        } else if (idx > 0 && idx < Green) {
            for (int i = beforeidx + 1; i < candidate.size(); i++) {
                select[idx] = candidate.get(i);
                set.add(candidate.get(i));
                go(select, idx + 1, i);
                set.remove(candidate.get(i));
            }
        } else {
            for (int i = beforeidx + 1; i < candidate.size(); i++) {
                if (!set.contains(candidate.get(i))) {
                    select[idx] = candidate.get(i);
                    set.add(candidate.get(i));
                    go(select, idx + 1, i);
                    set.remove(candidate.get(i));
                }
            }
        }

    }

    private static void go2(int[] select, int[][] tempMap) { // 배양액 퍼트리기
        // 각 칸에 도달하는 시간을 저장하는 배열 (초기값 -1: 아직 도달 X)
        int[][] timeMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(timeMap[i], -1);
        }

        Deque<Integer> deq = new ArrayDeque<>();

        // 초기 배양액 배치: 초록색은 3, 빨간색은 4 (시간 0으로 설정)
        for (int i = 0; i < Green; i++) {
            int r = select[i] / 100;
            int c = select[i] % 100;
            tempMap[r][c] = 3;
            timeMap[r][c] = 0;
            deq.addLast(r * 100 + c);
        }
        for (int i = Green; i < Green + Red; i++) {
            int r = select[i] / 100;
            int c = select[i] % 100;
            tempMap[r][c] = 4;
            timeMap[r][c] = 0;
            deq.addLast(r * 100 + c);
        }

        int flower = 0;
        while (!deq.isEmpty()) {
            int loc = deq.pollFirst();
            int r = loc / 100;
            int c = loc % 100;
            // 꽃이 핀 칸에서는 더 이상 확산하지 않음
            if (tempMap[r][c] == 5)
                continue;
            int currentColor = tempMap[r][c];
            int curTime = timeMap[r][c];


            for (int dir = 0; dir < 4; dir++) {
                int nr = r + delta[0][dir];
                int nc = c + delta[1][dir];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) // 범위 밖이면 패스
                    continue;
                if (tempMap[nr][nc] == 0) // 호수
                    continue;
                if (tempMap[nr][nc] == 5) // 이미 꽃
                    continue;
                if (timeMap[nr][nc] == -1) { // 아직 도달하지 않은 칸이면
                    timeMap[nr][nc] = curTime + 1;
                    tempMap[nr][nc] = currentColor;
                    deq.addLast(nr * 100 + nc);
                } else if (timeMap[nr][nc] == curTime + 1 && tempMap[nr][nc] != currentColor) { // 이미 도달했으면서, 현재 도달 시간(curTime+1)과 동일하고 색이 다른 경우 -> 꽃 발생
                    tempMap[nr][nc] = 5; // 꽃으로 변경
                    flower++;
                }
                // 도달 시간이 다르거나 같은 색이면 아무것도 하지 않음
            }


        }
        max = Math.max(max, flower);
    }
}

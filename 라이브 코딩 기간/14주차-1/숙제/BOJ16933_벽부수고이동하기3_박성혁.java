import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    
    // 기본 아이디어 : 구현
    
    static boolean[][][][] visited;
    static int[][] map;
    static int N, M, K;
    static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};

    private static class Loc {
        int row, col, movePoint, brokeCnt;

        Loc() {
        }

        public Loc(int row, int col, int movePoint, int brokeCnt) {
            this.row = row;
            this.col = col;
            this.movePoint = movePoint;
            this.brokeCnt = brokeCnt;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M][2][K+1]; // 행, 열, 밤낮, K번째 벽부수기 사용여부
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        Deque<Loc> deq = new ArrayDeque<>();
        deq.addLast(new Loc(0, 0, 1, K));
        boolean isok = true;
        while (!deq.isEmpty()) {
            Loc now = deq.pollFirst();
            if (visited[now.row][now.col][now.movePoint % 2][now.brokeCnt]) { // 이미 방문했다면 건너뛰기
                continue;
            }

            if (now.row == N - 1 && now.col == M - 1) { // 도착지점이라면 movePoint 출력후 종료
                System.out.println(now.movePoint);
                isok = false;
                break;
            }
            
            visited[now.row][now.col][now.movePoint % 2][now.brokeCnt] = true; // 방문 체크

            if (!visited[now.row][now.col][(now.movePoint+1)%2][now.brokeCnt]) {
                deq.addLast(new Loc(now.row, now.col, now.movePoint + 1, now.brokeCnt));
            }

            for (int dir = 0; dir < 4; dir++) {
                int nr = now.row + delta[0][dir];
                int nc = now.col + delta[1][dir];
                if (inRange(nr, nc)){
                    if (map[nr][nc] == 0){ // 벽이 아닌경우
                        deq.addLast(new Loc(nr, nc, now.movePoint+1, now.brokeCnt));
                    } else {
                        if ((now.movePoint%2==1) && now.brokeCnt>0){ // 벽이라면 낮일때만 뿌시고 움직이는게 가능
                            deq.addLast(new Loc(nr, nc, now.movePoint+1, now.brokeCnt-1));
                        }
                    }
                }
            }
        }

        if (isok){ // 갈수 없다면 -1 출력
            System.out.println(-1);
        }
    }

    private static boolean inRange(int row, int col) {
        if ((row >= 0) && (row < N) && (col >= 0) && (col < M)) {
            return true;
        }
        return false;
    }
}

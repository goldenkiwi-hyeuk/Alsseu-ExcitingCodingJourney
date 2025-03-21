import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, black, white;
    static int[][] map;
    static int[][] delta = {{-1, -1,1,1}, {-1, 1,1,-1}}; // 좌상, 우상, 우하, 좌하

    // 기본아이디어 : 백트레킹 및 dfs
    // 체스판을 흑과백으로 분리 (흑의 자리에 있는 비숍은 절대 백의 자리에 있는 비숍을 잡지 못함) <- 혼자서 못해서 아이디어 질문게시판에서 떼옴

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        white = 0; // 백의 자리에서 놓을 수 있는 최대 비숍 수
        black = 0; // 흑의 자리에서 놓을 수 있는 최대 비숍 수
        checkBishop(0, 0,0 ,2); // 백의 자리 탐색
        checkBishop(0, 1,1 ,2); // 흑의 자리 탐색
        System.out.println(black+white); // 최대 놓을 수 있는 비숍 수
    }

    private static void checkBishop(int r, int c, int color,int bishopnum) {
        // 현재 자리의 위치에 대한 탐색 범위 경계 체크
        if(r>=N){ // 탐색 끝난 케이스 최대값 비교 후 return;
            if (color == 0){
                white = Math.max(white, bishopnum-2);
            } else {
                black = Math.max(black, bishopnum-2);
            }
            return;
        }
        if (c >= N){ // 열에 대한 탐색 끝, 행을 한칸 늘리고 짝수칸을 탐색했다면 홀수칸 탐색, 홀수칸 탐색했을경우 새로운 행에서는 짝수칸 탐색
            if (c%2==0){
                checkBishop(r+1,1,color,bishopnum);
            } else {
                checkBishop(r+1,0,color,bishopnum);
            }
            return;
        }

        // 비숍을 둘 수 없는 경우
        if (map[r][c]==0){
            checkBishop(r,c+2,color,bishopnum);
            return;
        }

        // 비숍을 둘수 있는 경우
        boolean canBishop = canBishop(r,c); // 해당 위치에 비숍을 놓는다면 기존 비숍과 만나는지 체크
        if (canBishop){ // 완벽히 가능한 케이스여서 비숍을 두기
            map[r][c] = bishopnum;
            checkBishop(r,c+2,color,bishopnum+1);
            map[r][c] = 1;
        }
        checkBishop(r,c+2,color,bishopnum); // 비숍 두지 않고 다음자리 탐색
    }

    private static boolean canBishop(int r, int c) {
        boolean isok = true;
        out:
        for (int dir = 0; dir < 4; dir++) { // 비숍의 4방향을 Map의 끝가지 탐색하며 해당 방향에 이미 비숍을 둔적 있다면 불가능 전체 탐색이후에도 문제 없다면 비숍 둘수 있음
            for (int l=1; l<N;++l){
                int nr = r+delta[0][dir]*l;
                int nc = c+delta[1][dir]*l;
                if (inRange(nr,nc)){
                    if (map[nr][nc]>1){
                        isok = false;
                        break out;
                    }
                } else {
                    break;
                }
            }
        }
        return isok;
    }

    private static boolean inRange(int row, int col) {
        if ((row >= 0) && (col >= 0) && (row < N) && (col < N)) return true;
        return false;
    }
}

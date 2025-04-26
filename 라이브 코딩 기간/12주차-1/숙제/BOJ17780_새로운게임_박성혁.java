import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본 아이디어 구현
    // 구현 사항
    // 1. 각 말의 상태를 저장할 객체
    // 2. 하얀칸 도착 구현
    // 3. 빨간칸 도착 구현
    // 4. 파란칸에서 말의 움직임

    static int N, K;
    static int[][] map;
    static List<Integer>[][] board;

    static int[][] delta = {{0, 0, -1, 1}, {1, -1, 0, 0}};
    static List<Piece> pieceList;

    private static class Piece { // 말의 상태를 저장할 객체
        int row, col, dir;

        public Piece() {
        }

        public Piece(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        board = new List[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayList<>();
                map[i][j] = Integer.parseInt(st.nextToken()); // map[i][j]에는 체스판 상태 저장
            }
        }
        pieceList = new ArrayList<>();
        pieceList.add(new Piece()); // 말의 번호를 1부터 맞추기 위해서 0번에는 아무의미 없는 피스 하나 넣기
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            board[row][col].add(i); // board[i][j]에는 해당 체스판에 위치한 말들을 저장
            pieceList.add(new Piece(row, col, dir));
        }
        int time = 0;
        out:
        while (time <= 1000) {
            ++time;
            for (int i = 1; i <= K; i++) {
                Piece piece = pieceList.get(i);
                if (board[piece.row][piece.col].get(0) != i) {
                    continue;
                }
                int nr = piece.row + delta[0][piece.dir];
                int nc = piece.col + delta[1][piece.dir];
                int result = -1;
                if (inRange(nr, nc)) { // 이동 범위가 체스판 안인경우
                    result = chessMove(nr, nc, piece);
                } else { // 이동 장소가 체스판 바깥이라면
                    switch (piece.dir) {
                        case 0:
                            piece.dir = 1;
                            break;
                        case 1:
                            piece.dir = 0;
                            break;
                        case 2:
                            piece.dir = 3;
                            break;
                        case 3:
                            piece.dir = 2;
                            break;
                    }
                    nr = piece.row + delta[0][piece.dir];
                    nc = piece.col + delta[1][piece.dir];
                    result = chessMove2(nr, nc, piece);
                }
                if (board[piece.row][piece.col].size() >= 4) {
                    break out;
                }
            }
        }

        if (time > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(time);
        }
    }

    private static int chessMove2(int nr, int nc, Piece piece) {
        if (map[nr][nc] == 0) { // 해당 체스판이 흰색이라면?
            return meetWhite(nr, nc, piece);
        } else if (map[nr][nc] == 1) { // 해당 체스판이 빨간색이라면?
            return meetRed(nr, nc, piece);
        } else { // 해당 체스판이 파란색이라면?
            return -1;
        }
    }

    private static int chessMove(int nr, int nc, Piece piece) {
        if (map[nr][nc] == 0) { // 해당 체스판이 흰색이라면?
            return meetWhite(nr, nc, piece);
        } else if (map[nr][nc] == 1) { // 해당 체스판이 빨간색이라면?
            return meetRed(nr, nc, piece);
        } else { // 해당 체스판이 파란색이라면?
            switch (piece.dir) {
                case 0:
                    piece.dir = 1;
                    break;
                case 1:
                    piece.dir = 0;
                    break;
                case 2:
                    piece.dir = 3;
                    break;
                case 3:
                    piece.dir = 2;
                    break;
            }
            int newnr = piece.row + delta[0][piece.dir];
            int newnc = piece.col + delta[1][piece.dir];
            if (inRange(newnr, newnc)) {
                if (map[newnr][newnc] == 0) { // 해당 체스판이 흰색이라면?
                    return meetWhite(newnr, newnc, piece);
                } else if (map[newnr][newnc] == 1) { // 해당 체스판이 빨간색이라면?
                    return meetRed(newnr, newnc, piece);
                } else { // 또 파란색이라면
                    return -1;
                }
            } else { // 반대방향이 범위 밖이라면
                return -1;
            }
        }
    }

    private static int meetWhite(int nr, int nc, Piece piece) {
        int beforeRow = piece.row;
        int beforeCol = piece.col;
        List<Integer> beforeLoc = board[piece.row][piece.col];
        List<Integer> newLoc = board[nr][nc];
        for (int chessaPiece : beforeLoc) {
            pieceList.get(chessaPiece).row = nr;
            pieceList.get(chessaPiece).col = nc;
            newLoc.add(chessaPiece);
        }
        board[beforeRow][beforeCol] = new ArrayList<>();
        return newLoc.get(0);
    }

    private static int meetRed(int nr, int nc, Piece piece) {
        int beforeRow = piece.row;
        int beforeCol = piece.col;
        List<Integer> beforeLoc = board[piece.row][piece.col];
        List<Integer> newLoc = board[nr][nc];
        for (int i = beforeLoc.size() - 1; i >= 0; --i) {
            pieceList.get(beforeLoc.get(i)).row = nr;
            pieceList.get(beforeLoc.get(i)).col = nc;
            newLoc.add(beforeLoc.get(i));
        }
        board[beforeRow][beforeCol] = new ArrayList<>();
        return newLoc.get(0);
    }

    private static boolean inRange(int row, int col) {
        if ((row >= 0) && (row < N) && (col >= 0) && (col < N)) {
            return true;
        }
        return false;
    }
}
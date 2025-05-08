import java.util.*;
import java.io.*;

class Main {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int greenBoard, blueBoard, N, point;

    public static void main(String[] args) {
        input();
        solve();
        output();
    }

    static void input() {
        N = scan.nextInt();
    }

    static void output() {
        System.out.println(sb.toString());
    }

    static void solve() {
        point = 0;
        // 6 * 4, 4개씩 6층의 상태를 비트마스킹을 통해 int 숫자로 관리 가능

        //0000 0000(사용 안함) / 0000(인덱스 5, 1층) / 0000(인덱스 4, 2층) / 0000(인덱스 3, 3층) / 0000(인덱스 2, 4층) / 0000(인덱스 1, 5층) / 0000(인덱스 0, 6층)
        greenBoard = 0;
        blueBoard = 0;

        for (int c = 0; c < N; c++) {
            int t = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();

            putBlock(t, x, y, true);  // 초록 보드
            // 파랑 보드, t, x, y 위치를 바꾸면 초록 보드와 같은 방법으로 계산 가능
            putBlock(rotateType(t), y, x, false);
            clearLines();
            handleLightZones();
        }

        sb.append(point).append('\n').append(getBlockCnt());
    }

    // 블록의 타입을 회전하여 변환
    static int rotateType(int t) {
        if (t == 2) return 3;
        if (t == 3) return 2;
        return 1;
    }

    static void putBlock(int t, int x, int y, boolean isGreen) {
        int board = isGreen ? greenBoard : blueBoard;

        if (t == 1) {
            int pos = findPosition(board, 1 << y);
            // 행4(층)에 y열 만큼 1을 이동한 값 넣기
            board |= (1 << (pos * 4 + y));
        } else if (t == 2) {
            int pos = findPosition(board, 3 << y);
            // 행(층)에 y열 만큼 11을 이동한 값 넣기
            board |= 3 << (pos * 4 + y);
        } else {
            int pos = findPosition(board, 1 << y);
            // 행(층)과 (행-1)층에 y열 만큼 1을 이동한 값 넣기
            board |= (1 << (pos * 4 + y)) | (1 << ((pos - 1) * 4 + y));
        }

        if (isGreen) greenBoard = board;
        else blueBoard = board;
    }

    static int findPosition(int board, int mask) {
        for (int i = 0; i < 6; i++) {
            if ((board & (mask << (i * 4))) != 0) {
                return i - 1;
            }
        }
        return 5;
    }

    static void clearLines() {
        greenBoard = clearLine(greenBoard);
        blueBoard = clearLine(blueBoard);
    }

    static int clearLine(int board) {
        for (int r = 5; r >= 0; r--) {
            int rowMask = (15 << (r * 4));  // 1111 마스크
            if ((board & rowMask) == rowMask) {
                point++;
                // 지워질 줄 위층 블록
                int upper = board & ((1 << (r * 4)) - 1);
                // 지워질 줄 아래층 블록
                int lower = board & (~((1 << ((r + 1) * 4)) - 1));
                // 위층 블록을 한칸 내리고 아래층 블록은 유지
                board = (upper << 4) | lower;
                r++;
            }
        }
        return board;
    }

    static void handleLightZones() {
        greenBoard = handleLightZone(greenBoard);
        blueBoard = handleLightZone(blueBoard);
    }

    static int handleLightZone(int board) {
        int shiftCount = 0;
        for (int i = 0; i < 2; i++) {
            int rowMask = (15 << (i * 4));
            if ((board & rowMask) != 0) shiftCount++;
        }

        if (shiftCount > 0) {
            // 이동해야 할 만큼 층을 아래로 이동
            board <<= (shiftCount * 4);
            // 24비트를 넘지 않도록 아래층 잘라 내기
            board &= 0xFFFFFF;
        }
        return board;
    }

    static int getBlockCnt() {
        return Integer.bitCount(greenBoard) + Integer.bitCount(blueBoard);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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

import java.io.*;
import java.util.*;


class Main {
    static int[][] blueBoard;
    static int[][] greenBoard;
    static int[] greenCol; // 행별 블럭이 있는 가장 작은 열
    static int[] blueRow; //  열별 블럭이 있는 가장 작은 행
    static int score = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        greenBoard = new int[6][4];
        blueBoard = new int[4][6];
        greenCol = new int[4];
        blueRow = new int[4];

        Arrays.fill(greenCol,5);
        Arrays.fill(blueRow,5);


        int N = Integer.parseInt(br.readLine()); // 블록을 놓은 횟수

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            getBlock(t, x, y);
        }
        System.out.println(score);
        System.out.println(countBlocks());


    }


    public static void getBlock(int blockType, int x, int y){
        //greenBoard
        if(blockType==2){ // 가로
            int idx = 5;
            for(int i=y; i<=y+1; i++){
                idx = Integer.min(idx,greenCol[i]);
            }
            greenBoard[idx][y] = 1;
            greenBoard[idx][y+1] = 1;
            greenCol[y] = idx-1;
            greenCol[y+1] = idx-1;
        } else {
            int idx = Integer.min(5, greenCol[y]);
            if(blockType==1){
                greenBoard[idx][y] = 1;
                greenCol[y] = idx-1;
            } else{
                greenBoard[idx-1][y] = 1;
                greenBoard[idx][y] = 1;
                greenCol[y] = idx-2;
            }
        }
        //blueBoard
        if(blockType==3){ // 세로
            int idx = 5;
            for(int i=x; i<=x+1; i++){
                idx = Integer.min(idx,blueRow[i]);
            }
            blueBoard[x][idx] = 1;
            blueBoard[x+1][idx] = 1;
            blueRow[x] = idx-1;
            blueRow[x+1] = idx-1;
        } else {
            int idx = Integer.min(5,blueRow[x]);
            if(blockType==1){
                blueBoard[x][idx] = 1;
                blueRow[x] = idx-1;
            } else {
                blueBoard[x][idx-1] = 1;
                blueBoard[x][idx] = 1;
                blueRow[x] = idx-2;
            }
        }


        checkAndClear();
        handleSpecialZone();
        updateState();
        // printBlock();
    }

    public static void checkAndClear() {
        // green
        for (int i = 5; i >= 2; i--) {
            boolean full = true;
            for (int j = 0; j < 4; j++) {
                if (greenBoard[i][j] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                score++;
                for (int r = i; r >= 1; r--) {
                    for (int c = 0; c < 4; c++) {
                        greenBoard[r][c] = greenBoard[r - 1][c];
                    }
                }
                for (int c = 0; c < 4; c++) greenBoard[0][c] = 0;
                i++;
            }
        }

        // blue
        for (int j = 5; j >= 2; j--) {
            boolean full = true;
            for (int i = 0; i < 4; i++) {
                if (blueBoard[i][j] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                score++;
                for (int c = j; c >= 1; c--) {
                    for (int r = 0; r < 4; r++) {
                        blueBoard[r][c] = blueBoard[r][c - 1];
                    }
                }
                for (int r = 0; r < 4; r++) blueBoard[r][0] = 0;
                j++;
            }
        }
    }

    public static void handleSpecialZone() {
        // green
        int shift = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (greenBoard[i][j] == 1) {
                    shift++;
                    break;
                }
            }
        }
        while (shift-- > 0) {
            for (int r = 5; r >= 1; r--) {
                for (int c = 0; c < 4; c++) {
                    greenBoard[r][c] = greenBoard[r - 1][c];
                }
            }
            for (int c = 0; c < 4; c++) greenBoard[0][c] = 0;
        }

        // blue
        shift = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (blueBoard[i][j] == 1) {
                    shift++;
                    break;
                }
            }
        }
        while (shift-- > 0) {
            for (int c = 5; c >= 1; c--) {
                for (int r = 0; r < 4; r++) {
                    blueBoard[r][c] = blueBoard[r][c - 1];
                }
            }
            for (int r = 0; r < 4; r++) blueBoard[r][0] = 0;
        }
    }

    public static int countBlocks() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (greenBoard[i][j] == 1) count++;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (blueBoard[i][j] == 1) count++;
            }
        }
        return count;
    }

    public static void updateState() {
        for (int j = 0; j < 4; j++) {
            greenCol[j] = 5;
            for (int i = 0; i < 6; i++) {
                if (greenBoard[i][j] == 1) {
                    greenCol[j] = i - 1;
                    break;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            blueRow[i] = 5;
            for (int j = 0; j < 6; j++) {
                if (blueBoard[i][j] == 1) {
                    blueRow[i] = j - 1;
                    break;
                }
            }
        }
    }
}
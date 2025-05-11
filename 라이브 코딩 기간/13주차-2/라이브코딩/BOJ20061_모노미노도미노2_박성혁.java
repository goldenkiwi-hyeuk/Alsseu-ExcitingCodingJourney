import java.util.*;
import java.io.*;

class Main {
    
    // 기본 아이디어 : 구현
    // 구현 사항
    // 1. 블록 놓기
    // 2. 점수 계산 및 블록 소멸, 블록 아래로 내리기
    // 3. 블록이 연한 부분에 존재 여부 확인, 블록 내리기
    static int[][] green;
    static int[][] blue;
    static int score;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        green = new int[6][4];
        blue = new int[4][6];
        score = 0;
        int N = Integer.parseInt(br.readLine());
        for(int i = 1; i<=N ; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            put(i, t, row, col);
            // System.out.println("green : ");
            // for(int j = 0; j<6; ++j){
            //     System.out.println(Arrays.toString(green[j]));    
            // }
            // System.out.println("blue : ");
            // for(int j = 0; j<4; ++j){
            //     System.out.println(Arrays.toString(blue[j]));    
            // }
            scoreCheck();
            overCheck();
        }
        // System.out.println("green : ");
        // for(int j = 0; j<6; ++j){
        //     System.out.println(Arrays.toString(green[j]));    
        // }
        // System.out.println("blue : ");
        // for(int j = 0; j<4; ++j){
        //     System.out.println(Arrays.toString(blue[j]));    
        // }
        int total = 0;
        for(int i = 0; i<6; ++i){
            for(int j = 0; j<4 ; ++j){
                if(green[i][j] != 0){
                    ++total;
                }
                if(blue[j][i] != 0){
                    ++total;
                }
            }
        }
        System.out.println(score);
        System.out.println(total);
    }
    
    private static void overCheck(){
        for(int i = 0; i<2; ++i){
            if(green[i][0] != 0 || green[i][1] != 0 || green[i][2] != 0 || green[i][3] != 0){
                for(int j = 4 ; j>=0; --j){
                    green[j+1][0] = green[j][0];
                    green[j+1][1] = green[j][1];
                    green[j+1][2] = green[j][2];
                    green[j+1][3] = green[j][3];
                }
                green[i][0] = 0;
                green[i][1] = 0;
                green[i][2] = 0;
                green[i][3] = 0;
            }
        }
        
        for(int i = 0; i<2; ++i){
            if(blue[0][i] != 0 || blue[1][i] != 0 || blue[2][i] != 0 || blue[3][i] != 0){
                for(int j = 4 ; j>=0; --j){
                    blue[0][j+1] = blue[0][j];
                    blue[1][j+1] = blue[1][j];
                    blue[2][j+1] = blue[2][j];
                    blue[3][j+1] = blue[3][j]; 
                }
                blue[0][i] = 0;
                blue[1][i] = 0;
                blue[2][i] = 0;
                blue[3][i] = 0;
            }
        }
    }
    
    private static void scoreCheck(){
        for(int i = 5; i>=0; --i){
            if(green[i][0] != 0 && green[i][1] != 0 && green[i][2] != 0 && green[i][3] != 0){
                ++score;
                for(int j = i-1 ; j>=0; --j){
                    green[j+1][0] = green[j][0];
                    green[j+1][1] = green[j][1];
                    green[j+1][2] = green[j][2];
                    green[j+1][3] = green[j][3];
                    green[j][0] = 0;
                    green[j][1] = 0;
                    green[j][2] = 0;
                    green[j][3] = 0;
                }
                ++i;
            }
        }
        for(int i = 5; i>=0; --i){
            if(blue[0][i] != 0 && blue[1][i] != 0 && blue[2][i] != 0 && blue[3][i] != 0){
                ++score;
                for(int j = i-1 ; j>=0; --j){
                    blue[0][j+1] = blue[0][j];
                    blue[1][j+1] = blue[1][j];
                    blue[2][j+1] = blue[2][j];
                    blue[3][j+1] = blue[3][j];
                    blue[0][j] = 0;
                    blue[1][j] = 0;
                    blue[2][j] = 0;
                    blue[3][j] = 0;
                }
                ++i;
            }
        }
    }
    
    private static void put(int idx, int type, int row, int col){
        if(type == 1){ // 1*1 블럭
            // green에 put
            int bRow = -1;
            for(int i = 0 ; i<6 ;++i){ 
                int nRow = i;
                if(inRangeGreen(nRow,col) && green[nRow][col] == 0){
                    bRow = nRow;
                } else {
                    break;
                }
            }
            green[bRow][col] = idx;
            // blue에 put
            int bCol = -1;
            for(int i = 0 ; i<6 ; ++i){
                int nCol = i;
                if(inRangeBlue(row,nCol) && blue[row][nCol] == 0){
                    bCol = nCol;
                } else {
                    break;
                }
            }
            blue[row][bCol] = idx;
        } else if(type == 2){ // 1*2 블럭
            // green에 put
            int bRow = -1;
            for(int i = 0 ; i<6 ; ++i){ 
                int nRow = i;
                if(inRangeGreen(nRow,col) && green[nRow][col] == 0 && green[nRow][col+1] == 0){
                    bRow = nRow;
                } else {
                    break;
                }
            }
            green[bRow][col] = idx;
            green[bRow][col+1] = idx;
            // blue에 put
            int bCol = 0;
            for(int i = 1 ; i<6 ; ++i){
                int nCol = i;
                if(inRangeBlue(row,nCol) && blue[row][nCol] == 0){
                    bCol = nCol;
                } else {
                    break;
                }
            }
            blue[row][bCol-1] = idx;
            blue[row][bCol] = idx;
        } else { // 2*1 블럭
            // green에 put
            int bRow = 0;
            for(int i = 0 ; i<6 ; ++i){ 
                int nRow = i;
                if(inRangeGreen(nRow,col) && green[nRow][col] == 0){
                    bRow = nRow;
                } else {
                    break;
                }
            }
            green[bRow-1][col] = idx;
            green[bRow][col] = idx;
            // blue에 put
            int bCol = -1;
            for(int i = 1 ; i<6 ; ++i){
                int nCol = i;
                if(inRangeBlue(row,nCol) && blue[row][nCol] == 0 && blue[row+1][nCol] == 0){
                    bCol = nCol;
                } else {
                    break;
                }
            }
            blue[row][bCol] = idx;
            blue[row+1][bCol] = idx;
        }
    }
    
    private static boolean inRangeGreen(int row, int col){
        if((row>=0)&&(row<6)&&(col>=0)&&(col<4)){
            return true;
        }
        return false;
    }
    
    private static boolean inRangeBlue(int row, int col){
        if((row>=0)&&(row<4)&&(col>=0)&&(col<6)){
            return true;
        }
        return false;
    }
}
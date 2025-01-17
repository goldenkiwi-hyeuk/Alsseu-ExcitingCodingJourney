import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0,0);


    }

    public static boolean sudoku(int r, int c) {

        // 행 먼저 탐색 -> 그다음 행으로 이동
        if(c==9){
            return sudoku(r+1,0);
        }

        // 모든 행 다 채웠으면 종료
        if(r==9) {
            printMap();
            return true;
        }

        // 빈 칸 이라면
        if (map[r][c] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (checkMap(r, c, i)) {
                    map[r][c] = i;
                    if(sudoku(r,c+1)){
                        return true;
                    }
                }
            }
            map[r][c] = 0; // 백트래킹!!!!!!!!
            return false;
        }

        return sudoku(r,c+1);
    }

    public static boolean checkMap(int r, int c, int num) {
        for (int i = 0; i < 9; i++) {
            if (map[r][i] == num) return false;
        }

        for (int i = 0; i < 9; i++) {
            if (map[i][c] == num) return false;
        }

        for (int i = (r / 3) * 3; i < (r / 3) * 3 + 3; i++) {
            for (int j = (c / 3) * 3; j < (c / 3) * 3 + 3; j++) {
                if (map[i][j] == num) return false;
            }
        }
        return true;
    }

    public static void printMap(){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

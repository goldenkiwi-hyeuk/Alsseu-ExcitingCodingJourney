import java.util.Scanner;

public class BOJ2580_스도쿠 {

    static int[][] sudoku = new int[9][9];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = sc.nextInt();
            }
        }
        sc.close();

        fillSudoku(0, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(sudoku[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean fillSudoku(int r, int c) {
        if (c == 9) {
            r++;
            c = 0;
        }
        if (r == 9) return true;

        if (sudoku[r][c] != 0) return fillSudoku(r, c + 1);

        for (int i = 1; i <= 9; i++) {
            if (isAble(r, c, i)) {
                sudoku[r][c] = i;
                if (fillSudoku(r, c + 1)) return true;
                sudoku[r][c] = 0;
            }
        }
        return false;
    }

    static boolean isAble(int r, int c, int n) {
        for (int i = 0; i < 9; i++) {
            if (sudoku[r][i] == n || sudoku[i][c] == n) return false;
        }

        int nowR = (r / 3) * 3, nowC = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (sudoku[nowR + i][nowC + j] == n) return false;
            }
        }
        return true;
    }
}

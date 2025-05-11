package boj;

import java.util.*;

class Main20061 {

    static int n, point;
    static int[][] green = new int[6][4];
    static int[][] blue = new int[4][6];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            pushGreen(t, x, y);
            pushBlue(t, x, y);

            while (true) {
                if (!checkGreen() && !checkBlue()) break;
            }
            checkSkyLime();
        }
        sc.close();

        System.out.println(point);
        System.out.println(countTiles());
    }

    static void pushGreen(int t, int x, int y) {
        int r = 0;
        if (t == 1) {
            while (r < 6 && green[r][y] == 0) r++;
            green[r - 1][y] = 1;
        } else if (t == 2) {
            while (r < 6 && green[r][y] == 0 && green[r][y + 1] == 0) r++;
            green[r - 1][y] = green[r - 1][y + 1] = 1;
        } else {
            while (r + 1 < 6 && green[r + 1][y] == 0) r++;
            green[r][y] = green[r - 1][y] = 1;
        }
    }

    static void pushBlue(int t, int x, int y) {
        int c = 0;
        if (t == 1) {
            while (c < 6 && blue[x][c] == 0) c++;
            blue[x][c - 1] = 1;
        } else if (t == 2) {
            while (c + 1 < 6 && blue[x][c + 1] == 0) c++;
            blue[x][c] = blue[x][c - 1] = 1;
        } else {
            while (c < 6 && blue[x][c] == 0 && blue[x + 1][c] == 0) c++;
            blue[x][c - 1] = blue[x + 1][c - 1] = 1;
        }
    }

    static boolean checkGreen() {
        boolean flag = false;
        for (int i = 2; i < 6; i++) {
            boolean full = true;
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                point++;
                flag = true;
                removeGreen(i);
            }
        }
        return flag;
    }

    static boolean checkBlue() {
        boolean flag = false;
        for (int j = 2; j < 6; j++) {
            boolean full = true;
            for (int i = 0; i < 4; i++) {
                if (blue[i][j] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                point++;
                flag = true;
                removeBlue(j);
            }
        }
        return flag;
    }

    static void removeGreen(int n) {
        for (int i = n; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                green[i][j] = green[i - 1][j];
            }
        }
        Arrays.fill(green[0], 0);
    }

    static void removeBlue(int c) {
        for (int n = c; n > 0; n--) {
            for (int i = 0; i < 4; i++) {
                blue[i][n] = blue[i][n - 1];
            }
        }
        for (int i = 0; i < 4; i++) {
            blue[i][0] = 0;
        }
    }

    static void checkSkyLime() {
        int g = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j] == 1) {
                    g++;
                    break;
                }
            }
        }
        while (g-- > 0) {
            for (int i = 5; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    green[i][j] = green[i - 1][j];
                }
            }
            Arrays.fill(green[0], 0);
        }

        int b = 0;
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 4; i++) {
                if (blue[i][j] == 1) {
                    b++;
                    break;
                }
            }
        }
        while (b-- > 0) {
            for (int j = 5; j > 0; j--) {
                for (int i = 0; i < 4; i++) {
                    blue[i][j] = blue[i][j - 1];
                }
            }
            for (int i = 0; i < 4; i++) {
                blue[i][0] = 0;
            }
        }
    }

    static int countTiles() {
        int cnt = 0;
        for (int[] greens : green) {
            for (int g : greens) {
                if (g == 1) cnt++;
            }
        }
        for (int[] blues : blue) {
            for (int b : blues) {
                if (b == 1) cnt++;
            }
        }
        return cnt;
    }
}

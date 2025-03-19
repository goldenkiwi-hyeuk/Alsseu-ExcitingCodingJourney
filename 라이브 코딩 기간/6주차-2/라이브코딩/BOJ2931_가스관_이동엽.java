package boj;

import java.util.Scanner;

public class Main2931 {

    static int n, m, lostX, lostY;
    static char eu[][], lostPipe;
    final static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1}; // 하 우 상 좌

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        eu = new char[n][m];
        int[] moscow = new int[2];
        int[] zagreb = new int[2];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                eu[i][j] = str.charAt(j);
                if (eu[i][j] == 'M') {
                    moscow[0] = i;
                    moscow[1] = j;
                } else if (eu[i][j] == 'Z') {
                    zagreb[0] = i;
                    zagreb[1] = j;
                }
            }
        }
        sc.close();

        findLostPipe();
        // 연결 필요한 부분
        boolean[] need = new boolean[4]; // 하 우 상 좌
        for (int d = 0; d < 4; d++) {
            int nx = lostX + dx[d];
            int ny = lostY + dy[d];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
            char ch = eu[nx][ny];
            if (ch == 'M' || ch == 'Z') {
                need[d] = true;
            } else if (ch != '.') {
                if (isConnected(ch, (d + 2) % 4))
                    need[d] = true;
            }
        }

        char[] pipes = {'|', '-', '+', '1', '2', '3', '4'};
        for (char p : pipes) {
            boolean isOk = true;
            for (int d = 0; d < 4; d++) {
                if (need[d] && !isConnected(p, d)) {
                    isOk = false;
                    break;
                }
                if (isConnected(p, d)) {
                    int nx = lostX + dx[d];
                    int ny = lostY + dy[d];
                    if (!isIn(nx, ny)) {
                        isOk = false;
                        break;
                    }
                    char adj = eu[nx][ny];
                    if (adj == '.') {
                        isOk = false;
                        break;
                    }
                    if (adj != 'M' && adj != 'Z') {
                        if (!isConnected(adj, (d + 2) % 4)) {
                            isOk = false;
                            break;
                        }
                    }
                }
            }
            if (isOk) {
                lostPipe = p;
                break;
            }
        }

        System.out.println((lostX + 1) + " " + (lostY + 1) + " " + lostPipe);
    }

    // .이고 연결된 곳이 2개 이상
    private static void findLostPipe() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (eu[i][j] == '.') {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if (!isIn(nx, ny)) continue;
                        char ch = eu[nx][ny];
                        if (ch == 'M' || ch == 'Z') {
                            cnt++;
                        } else if (ch != '.') {
                            if (isConnected(ch, (d + 2) % 4))
                                cnt++;
                        }
                    }
                    if (cnt >= 2) {
                        lostX = i;
                        lostY = j;
                        return;
                    }
                }
            }
        }
    }

    private static boolean isConnected(char pipe, int d) {
        switch (pipe) {
            case '|':
                return (d == 0 || d == 2);
            case '-':
                return (d == 1 || d == 3);
            case '+':
                return true;
            case '1': // ┌
                return (d == 0 || d == 1);
            case '2': // └
                return (d == 1 || d == 2);
            case '3': // ┘
                return (d == 2 || d == 3);
            case '4': // ┐
                return (d == 0 || d == 3);
            default:
                return false;
        }
    }

    private static boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}

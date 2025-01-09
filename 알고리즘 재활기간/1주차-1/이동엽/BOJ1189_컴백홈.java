import java.util.Scanner;

public class BOJ1189_컴백홈 {

    static int r, c, k;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
    static int cnt = 0;
    static char map[][];
    static boolean visited[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        k = sc.nextInt();

        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            map[i] = sc.next().toCharArray();
        }
        sc.close();

        visited[r - 1][0] = true;
        comeBackHome(r - 1, 0, 1);

        System.out.println(cnt);
    }

    static void comeBackHome(int i, int j, int dist) {
        if (i == 0 && j == c - 1) {
            if (dist == k) cnt++;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int ni = i + dr[d];
            int nj = j + dc[d];

            if (isIn(ni, nj) && !visited[ni][nj] && map[ni][nj] == '.') {
                visited[ni][nj] = true;
                comeBackHome(ni, nj, dist + 1);
                visited[ni][nj] = false;
            }
        }
    }

    static boolean isIn(int i, int j) {
        return 0 <= i && i < r && 0 <= j && j < c;
    }
}

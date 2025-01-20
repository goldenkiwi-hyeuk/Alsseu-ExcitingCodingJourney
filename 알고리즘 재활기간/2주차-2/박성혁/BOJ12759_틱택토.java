import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Pn = Integer.parseInt(br.readLine());
        int victory = 0;
        map = new int[4][4];
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = Pn;
            Pn = 3 - Pn;
            victory = tictacto();
            if (victory != 0) {
                break;
            }
        }
        System.out.println(victory);
    }

    private static int tictacto() {
        for (int i = 1; i < 4; i++) {
            if (map[i][1] == map[i][2] && map[i][2] == map[i][3]) {
                return map[i][1];
            }
        }
        for (int i = 1; i < 4; i++) {
            if (map[1][i] == map[2][i] && map[2][i] == map[3][i]) {
                return map[1][i];
            }
        }
        if (map[1][1] == map[2][2] && map[2][2] == map[3][3]) {
            return map[1][1];
        }
        if (map[1][3] == map[2][2] && map[2][2] == map[3][1]) {
            return map[1][3];
        }
        return 0;
    }
}

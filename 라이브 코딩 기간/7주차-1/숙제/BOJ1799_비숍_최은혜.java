import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N;
    static int result;
    static boolean[] rightLine;
    static boolean[] leftLine;
    static int maxBlack = 0;
    static int maxWhite = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        rightLine = new boolean[2 * N];
        leftLine = new boolean[2 * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        getStart(0, 0, 0, 0);
        getStart(0, 1, 0, 1);


        System.out.println(maxBlack + maxWhite);
    }


    public static void getStart(int a, int b, int count, int color) {

        if (b >= N) {
            a++;
            b = (a % 2 == 0) ? color : 1 - color;
        }

        if (a >= N) {
            if (color == 0) maxBlack = Math.max(maxBlack, count);
            else maxWhite = Math.max(maxWhite, count);
            return;
        }

        if (map[a][b] == 1 && isPossible(a, b)) {
            rightLine[a - b + N - 1] = true;
            leftLine[a + b] = true;
            getStart(a, b + 2, count + 1, color);
            rightLine[a - b + N - 1] = false;
            leftLine[a + b] = false;
        }

        getStart(a, b + 2, count, color);


    }

    static boolean isPossible(int x, int y) {
        return !leftLine[x + y] && !rightLine[x - y + N - 1];
    }

}

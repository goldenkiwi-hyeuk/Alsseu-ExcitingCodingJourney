import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int result = Integer.MAX_VALUE;
    static int sum = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }

        getLine();
        System.out.println(result);
    }

    public static void getLine() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 < N && y - d1 >= 0 && y + d2 < N) {
                            getSum(x, y, d1, d2);
                        }
                    }
                }
            }
        }
    }

    public static void getSum(int x, int y, int d1, int d2) {
        boolean[][] line = new boolean[N][N];


        for (int i = 0; i <= d1; i++) {
            line[x + i][y - i] = true;
            line[x + d2 + i][y + d2 - i] = true;
        }
        for (int i = 0; i <= d2; i++) {
            line[x + i][y + i] = true;
            line[x + d1 + i][y - d1 + i] = true;
        }

        int[] people = new int[5];

        // 1구역
        for (int i = 0; i < x + d1; i++) {
            for (int j = 0; j <= y; j++) {
                if (line[i][j]) break;
                people[0] += map[i][j];
            }
        }

        // 2구역
        for (int i = 0; i <= x + d2; i++) {
            for (int j = N - 1; j > y; j--) {
                if (line[i][j]) break;
                people[1] += map[i][j];
            }
        }

        // 3구역
        for (int i = x + d1; i < N; i++) {
            for (int j = 0; j < y - d1 + d2; j++) {
                if (line[i][j]) break;
                people[2] += map[i][j];
            }
        }

        // 4구역
        for (int i = x + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= y - d1 + d2; j--) {
                if (line[i][j]) break;
                people[3] += map[i][j];
            }
        }

        // 5구역
        people[4] = sum;

        for (int i = 0; i < 4; i++) {
            people[4] -= people[i];
        }


        int minPeople = Integer.MAX_VALUE;
        int maxPeople = Integer.MIN_VALUE;
        for (int i = 0; i < 5; i++) {
            minPeople = Math.min(minPeople, people[i]);
            maxPeople = Math.max(maxPeople, people[i]);
        }

        result = Math.min(result, maxPeople - minPeople);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, min;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        min = 987654321;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int bit = 0; bit < (1 << N); bit++) {
            int totalTails = 0;
            for (int c = 0; c < N; c++) {
                int tails = 0;
                for (int r = 0; r < N; r++) {
                    char ch = board[r][c];
                    if ((bit & (1 << r)) != 0) {
                        ch = (ch == 'T') ? 'H' : 'T';
                    }
                    if (ch == 'T') {
                        tails++;
                    }
                }
                totalTails += Math.min(tails, N - tails);
            }
            ans = Math.min(ans, totalTails);
        }

        System.out.println(ans);
    }
}

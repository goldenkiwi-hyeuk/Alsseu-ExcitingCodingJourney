import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = makestar(N);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N*2-1; j++) {
                if (map[i][j] == 1) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] makestar(int n) {
        int[][] map = new int[n][n*2-1];
        if (n/3==1){
            map = new int[][]
                    {{0,0,1,0,0},
                    {0,1,0,1,0},
                    {1,1,1,1,1}};
        }else {
            int[][] temp = makestar(n/2);
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n-1; j++) {
                    map[i][j+n/2] = temp[i][j];
                }
            }
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n-1; j++) {
                    map[i+n/2][j] = temp[i][j];
                }
            }
            for (int i = 0; i < n/2; i++) {
                for (int j = 0; j < n-1; j++) {
                    map[i+n/2][j+n] = temp[i][j];
                }
            }
        }
        return map;
    }
}

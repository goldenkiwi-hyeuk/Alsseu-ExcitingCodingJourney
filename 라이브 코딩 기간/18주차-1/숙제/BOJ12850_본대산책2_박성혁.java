import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 기본아이디어 : 분할정복 + 행렬의 제곱

    // 정보과학관, 전산관, 미래관, 신양관, 한경직기념관, 진리관, 형남공학관, 학생회관
    // map**d에서 (0,0)은 d번만에 정보과학관에 돌아오는 가짓수를 의미한다.
    static long[][] map = {{0, 1, 1, 0, 0, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 1, 0, 0},
            {0, 0, 1, 1, 0, 1, 1, 0},
            {0, 0, 0, 1, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 1, 0}};
    static int mod = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());
        long[][] ans = checkRoad(D);
        System.out.println(ans[0][0]);
    }

    private static long[][] checkRoad(int d) {
        if (d == 1) {
            return map;
        }
        // 분할정복 지수가 홀수인 경우와 짝수인 경우 분리
        if (d % 2 == 0) {
            long[][] temp = checkRoad(d / 2);
            long[][] ans = new long[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    long sum = 0;
                    for (int k = 0; k < 8; k++) {
                        sum = (sum % mod + (temp[i][k] * temp[k][j]) % mod) % mod;
                    }
                    ans[i][j] = sum;
                }
            }
            return ans;
        } else {
            long[][] temp = checkRoad(d / 2);
            long[][] temp2 = new long[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    long sum = 0;
                    for (int k = 0; k < 8; k++) {
                        sum = (sum % mod + (temp[i][k] * temp[k][j]) % mod) % mod;
                    }
                    temp2[i][j] = sum;
                }
            }
            // 홀수인 경우에는 map을 한번 더 곱해주기
            long[][] ans = new long[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    long sum = 0;
                    for (int k = 0; k < 8; k++) {
                        sum = (sum % mod + (temp2[i][k] * map[k][j]) % mod) % mod;
                    }
                    ans[i][j] = sum;
                }
            }
            return ans;
        }
    }
}

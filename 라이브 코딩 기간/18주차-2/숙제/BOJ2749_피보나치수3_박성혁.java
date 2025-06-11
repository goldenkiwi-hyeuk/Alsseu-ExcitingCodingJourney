import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // 기본 아이디어: 행렬곱 + 분할정복
    static long[][] map = {{0, 1}, {1, 1}};
    static long mod = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        // 피보나치 0은 0
        if (n == 0) {
            System.out.println(0);
        } else {
            // 나머지 피보나치는 행렬곱을 통해서 구하기
            long[][] ans = checkPibo(n);
            System.out.println(ans[0][1]);
        }
    }

    private static long[][] checkPibo(long n) {
        // n==1이면 기본 피보나치 반환 
        if (n == 1) {
            return map;
        } else {
            // 짝수라면 n/2한 값을 제곱하여 반환
            if (n % 2 == 0) {
                long[][] temp = checkPibo(n / 2);
                long[][] ans = new long[2][2];
                for (int i = 0; i < 2; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        long sum = 0;
                        for (int k = 0; k < 2; ++k) {
                            sum = (sum % mod + (temp[i][k] * temp[k][j]) % mod) % mod;
                        }
                        ans[i][j] = sum % mod;
                    }
                }
                return ans;
            } else {
                // 홀수라면라면 n/2한 값을 제곱한다음 기본 map한번 더 곱하기
                long[][] temp = checkPibo(n / 2);
                long[][] temp2 = new long[2][2];
                for (int i = 0; i < 2; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        long sum = 0;
                        for (int k = 0; k < 2; ++k) {
                            sum = (sum % mod + (temp[i][k] * temp[k][j]) % mod) % mod;
                        }
                        temp2[i][j] = sum % mod;
                    }
                }

                long[][] ans = new long[2][2];
                for (int i = 0; i < 2; ++i) {
                    for (int j = 0; j < 2; ++j) {
                        long sum = 0;
                        for (int k = 0; k < 2; ++k) {
                            sum = (sum % mod + (temp2[i][k] * map[k][j]) % mod) % mod;
                        }
                        ans[i][j] = sum % mod;
                    }
                }

                return ans;
            }
        }
    }
}
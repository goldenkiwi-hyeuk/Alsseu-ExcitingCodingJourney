import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    // 기본 아이디어 : DP
    // 2진법 자릿수마다 1의 갯수 체크
    static long[] dp = new long[55];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        dp[0] = 1;
        for (int i = 1; i < 55; i++) {
            dp[i] = dp[i - 1] * 2L + (long) Math.pow(2, i);
        }
        long result = calculate(B) - calculate(A - 1);
        System.out.print(result);
    }

    private static long calculate(long num) {
        if (num <= 0) return 0;

        long total = 0;
        long N = num;
        String numStr = Long.toBinaryString(N);
        int len = numStr.length();

        for (int i = 0; i < len; i++) {
            if (numStr.charAt(i) == '1') {
                int k = len - i - 1;
                
                // 1) 풀 블록: 0 ~ (2^k - 1) 까지 1의 합
                if (k > 0) {
                    total += dp[k - 1];
                }

                // 2) 부분 블록: [2^k, N] 구간에서
                //    MSB(비트 k)가 1인 숫자의 개수 = (N - 2^k + 1)
                long pow2k = (long) Math.pow(2, k);
                total += (N - pow2k + 1);

                // 3) 이제 2^k 구간을 떼어 내고,
                // 남은 하위 비트(0 ~ 2^k - 1)만 처리하도록 N 갱신
                N -= pow2k;
            }
        }

        return total;
    }
}

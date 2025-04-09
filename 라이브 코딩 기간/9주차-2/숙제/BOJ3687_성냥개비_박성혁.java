import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    // 기본 아이디어 : DP + 그리디
    // 가장 작은값 : DP
    // 가장 큰값 : 그리디
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int[][] number = {{1, 2}, {2, 5}, {3, 5}, {4, 4}, {5, 5}, {6, 6}, {7, 3}, {8, 7}, {9, 6}, {0, 6}};
        long[] dp = new long[101];
        int [] arr= {1,7,4,2,0,8}; // 2,3,4,5,6,7 개를 사용했을때 만들수 있는 가장 작은 배열
        Arrays.fill(dp,Long.MAX_VALUE); // dp 배열은 해당 인덱스 만큼의 성냥개비를 사용할때 가장 작은 수를 의미함
        dp[2]=1;
        dp[3]=7;
        dp[4]=4;
        dp[5]=2;
        dp[6]=6; // 첫자리에 0이 올수 없으므로
        dp[7]=8;
        dp[8]=10;
        for(int i=9; i<=100; i++){ // 9번째 숫자부터 100까지
            for(int j=2; j<=7; j++){ // dp[i-j]에 + arr[j-2]를 더해서 Math.min으로 비교
                String temp = String.valueOf(dp[i-j])+String.valueOf(arr[j-2]);
                dp[i] = Math.min(Long.parseLong(temp),dp[i]);
            }
        }
        // 가장 작은수는 항상 동일하기에 반복문 밖에서 값을 찾고 이후에는 배열의 n인덱스에 접근하기만 하면 된다.
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append(" ");
            if (n % 2 == 0) { // 가장 큰수를 만드는 방법은 1을 최대한 활용하고
                for (int i = 0; i < n / 2; ++i) {
                    sb.append("1");
                }
            } else { // 2로 나눠떨어지지 않는다면 맨앞자리 수만 7로 변경하면 된다.
                sb.append("7");
                for (int i = 0; i < n / 2 - 1; ++i) {
                    sb.append("1");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

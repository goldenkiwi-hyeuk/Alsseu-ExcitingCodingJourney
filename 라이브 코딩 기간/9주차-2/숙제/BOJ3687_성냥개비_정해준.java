import java.util.Scanner;

public class BOJ3687_성냥개비_정해준 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Long[] dp = new Long[101];
        dp[2] = 1L;
        dp[3] = 7L;
        dp[4] = 4L;
        dp[5] = 2L;
        dp[6] = 6L;
        dp[7] = 8L;
        dp[8] = 10L;
        long[] arr = {1,7,4,2,0,8};
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < N; t++) {

            int n = sc.nextInt();
            // 최댓값 구하기
            String max = "";
            if(n % 2 == 0) {
                max = add(max,n/2);
            } else {
                max = "7";
                max = add(max,(n-3) / 2);
            }
            //최솟값 구하기
            for(int i = 9; i <= n; i++) {
                Long min = Long.MAX_VALUE;
                for(int j = 2; j <= 7; j++) {
                    String s = String.valueOf(dp[i - j]) + String.valueOf(arr[j - 2]);
                    min = Math.min(min, Long.parseLong(s));
                }
                dp[i] = min;
            }
            sb.append(dp[n]).append(" ").append(max).append("\n");

        }
        System.out.println(sb);

    }
    static String add(String str, int cnt) {
        StringBuilder res = new StringBuilder();
        res.append(str);
        for(int i = 0; i < cnt; i++) {
            res.append("1");
        }
        return res.toString();
    }


}

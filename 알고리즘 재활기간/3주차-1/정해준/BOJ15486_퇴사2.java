import java.util.Scanner;

public class BOJ15486_퇴사2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N+2][2]; // [0]은 시간 [1]은 비용

        for(int i = 1; i <= N; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        int[] dp = new int[N+2];
        int max = 0;
        for(int i = 1; i <= N+1; i++) { // 일수를 거치면서 최댓값을 찾아감

            max = Math.max(max, dp[i]);

            int day = i + arr[i][0]; // 현재일자에 더해서 몇일 뒤에 돈이 들어오는지 확인

            if(day <= N + 1) { //일이 끝난 다음날에돈이 정산된다고 생각하여 구현 예 : 1일에 T가 1이면 2일에 정산이 되어있음
                dp[day] = Math.max(dp[day], max + arr[i][1]); // 기존의 날과 , i일의 일 + i까지의 최대값을 비교
            }

        }
        System.out.println(max);
    }
}

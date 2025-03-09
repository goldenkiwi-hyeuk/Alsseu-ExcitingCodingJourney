// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ2342_DDR_정해준 {
    public static void main(String[] args) {
        //절댓값 2일 경우 +4, 1일 경우 + 3, 0일 경우 + 1, 시작이 0일 경우 + 2
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[100001];
        int N = 0;
        for(int i = 1; i < arr.length; i++) {
            int n = sc.nextInt();
            if(n == 0)
                break;
            N++;
            arr[i] = n;
        }

        int dp[][][] = new int[N+1][5][5]; // 명령어, 왼발 위치, 오른발 위치

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], 987654321);
            }
        }
        //init
        dp[0][0][0] = 0;
        // 오른발과 왼발의 위치에 따른 이동값의 경우를 모두 구함        
        for(int i = 1; i <= N; i++) {
            for(int l = 0; l < 5; l++) { // 오른쪽을 움직이는 경우의 수
                for(int r = 0; r < 5; r++) {
                    if(r == 0) {
                        dp[i][l][arr[i]] = Math.min(dp[i][l][arr[i]],dp[i-1][l][r] + 2);
                    }
                    else if(Math.abs(r - arr[i]) == 0) {
                        dp[i][l][arr[i]] = Math.min(dp[i][l][arr[i]],dp[i-1][l][r] + 1);
                    } else if(Math.abs(r - arr[i]) == 2) {
                        dp[i][l][arr[i]] = Math.min(dp[i][l][arr[i]],dp[i-1][l][r] + 4);
                    } else {
                        dp[i][l][arr[i]] = Math.min(dp[i][l][arr[i]],dp[i-1][l][r] + 3);
                    }
                }
            }

            for(int r = 0; r < 5; r++) { // 왼쪽을 움직이는 경우의 수
                for(int l = 0; l < 5; l++) {
                    if(l == 0) {
                        dp[i][arr[i]][r] = Math.min(dp[i][arr[i]][r],dp[i-1][l][r] + 2);
                    }
                    else if(Math.abs(l - arr[i]) == 0) {
                        dp[i][arr[i]][r] = Math.min(dp[i][arr[i]][r],dp[i-1][l][r] + 1);
                    } else if(Math.abs(l - arr[i]) == 2) {
                        dp[i][arr[i]][r] = Math.min(dp[i][arr[i]][r],dp[i-1][l][r] + 4);
                    } else {
                        dp[i][arr[i]][r] = Math.min(dp[i][arr[i]][r],dp[i-1][l][r] + 3);
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                min = Math.min(min, dp[N][i][j]);
            }
        }
        System.out.println(min);
    }
}
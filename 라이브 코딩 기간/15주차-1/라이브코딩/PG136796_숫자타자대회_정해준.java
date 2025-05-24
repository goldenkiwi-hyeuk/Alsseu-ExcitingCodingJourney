import java.util.Arrays;

public class PG136796_숫자타자대회_정해준 {
    class Solution {
        int[][][] dp;
        char[][] map = {{'1','2','3'},{'4','5','6'},{'7','8','9'},{'*','0','#'}};
        int[][] cost = new int[10][10]; // 0 ~ 10 까지의 어떻게 가는지 비용계산
        int size = 0;
        String str;
        public int solution(String numbers) {
            int answer = 0;
            str = numbers;
            size = numbers.length();
            dp = new int[size][10][10];
            for(int i = 0;i < size; i++) {
                for(int j = 0; j < 10; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            costCheck();
            // for(int i = 0; i < 10; i++) {
            //     for(int j = 0; j < 10; j++) {
            //         System.out.print(cost[i][j] + " ");
            //     }
            //     System.out.println();
            // }

            return simul(0,4,6);
        }

        public int simul(int idx, int l, int r) {
            if(idx == size) {
                return 0;
            }
            if(dp[idx][l][r] != -1)
                return dp[idx][l][r];

            int n = str.charAt(idx) - '0';
            int res = 987654321;

            if(l != n) { // 이미 다른 손이 그 위치라면 굳이
                res = Math.min(simul(idx + 1, l, n) + cost[r][n], res);
            }
            if(r != n) {
                res = Math.min(simul(idx + 1, n, r) + cost[l][n], res);
            }

            return dp[idx][l][r] = res;
        }

        public void costCheck() {
            // 4 * (n / 4)
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    int x1, y1, x2, y2;
                    if(i == j) {
                        cost[i][j] = 1; // 자기 버튼일 경우
                    } else { // 그 외
                        x1 = i != 0 ? (i-1) / 3 : 3;
                        y1 = i != 0 ? (i - 1) % 3 : 1;
                        x2 = j != 0 ? (j-1) / 3 : 3;
                        y2 = j != 0 ? (j - 1) % 3 : 1;

                        int dr = Math.abs(x1 - x2);
                        int dc = Math.abs(y1 - y2);
                        // System.out.println(dr + " " + dc);
                        int min = Math.min(dr, dc); // 대각선 이동하는 최소 수, 1, 1 일 경우 1번 2 1 일 경우 1번 2 2 일 경우 2번 1 0 일 경우 0번
                        int max = Math.max(dr, dc);
                        int dif = min + max;
                        if(dif == 1) { // 상하좌우 인접
                            cost[i][j] = 2;
                        } else if(dif > 1) {
                            cost[i][j] = min * 3 + (max - min) * 2; // 대각선 이동을 다 한 후는 상하좌우로만 이동가능;
                        }
                    }
                }
            }
        }
    }
}

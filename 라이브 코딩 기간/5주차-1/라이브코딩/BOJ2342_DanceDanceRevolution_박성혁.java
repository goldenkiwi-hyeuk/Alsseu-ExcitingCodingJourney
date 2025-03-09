import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // 기본아이디어 3차원 dp
    // dp[i][left][right]는 i번째 움직임에서 왼발과 오른발의 위치가 left와 right일때 가장 적은 움직임 횟수를 뜻한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] strs = str.split(" ");
        int[] arr = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            arr[i] = Integer.parseInt(strs[i]); // 버튼 순서 배열 저장
        }

        int INF = 987654321;

        int[][][] dp = new int[strs.length][5][5]; // 3차원 dp 생성 및 INF로 초기화
        for (int i = 0; i < arr.length; ++i) {
            for (int left = 0; left < 5; ++left) {
                for (int right = 0; right < 5; ++right) {
                    dp[i][left][right] = INF;
                }
            }
        }

        dp[0][0][0] = 0; // 처음에는 0칸에 왼발 오른발 다 있음

        for (int i = 1; i < arr.length; ++i) {
            int target = arr[i - 1]; // 가야하는 버튼 번호
            for (int left = 0; left < 5; ++left) {
                for (int right = 0; right < 5; ++right) {
                    if (dp[i - 1][left][right] != INF) {
                        if (left == target || right == target) { // 이미 발이 올라간 상태
                            dp[i][left][right] = Math.min(dp[i][left][right], dp[i-1][left][right]+1);
                        } else { // 발을 옮겨야 하는 상황
                            // 왼쪽발 움직이기
                            if (left == 0){ // 왼쪽발이 0에 있다면
                                dp[i][target][right] = Math.min(dp[i][target][right], dp[i-1][left][right]+2);
                            } else if (Math.abs(target-left)==2){ // 반대편으로 움직이는 상황
                                dp[i][target][right] = Math.min(dp[i][target][right], dp[i-1][left][right]+4);
                            } else { // 인접한 방향으로 움직이는 상황
                                dp[i][target][right] = Math.min(dp[i][target][right], dp[i-1][left][right]+3);
                            }

                            // 오른쪽발 움직이기
                            if (right == 0){ // 왼쪽발이 0에 있다면
                                dp[i][left][target] = Math.min(dp[i][left][target], dp[i-1][left][right]+2);
                            } else if (Math.abs(target-right)==2){ // 반대편으로 움직이는 상황
                                dp[i][left][target] = Math.min(dp[i][left][target], dp[i-1][left][right]+4);
                            } else { // 인접한 방향으로 움직이는 상황
                                dp[i][left][target] = Math.min(dp[i][left][target], dp[i-1][left][right]+3);
                            }
                        }
                    }
                }
            }
        }

        int min = 987654321;
        // 정답을 찾기 위해 탐색
        for (int left = 0; left < 5; ++left) {
            for (int right = 0; right < 5; ++right) {
                if (dp[arr.length-1][left][right] < min) {
                    min = dp[arr.length-1][left][right];
                }
            }
        }

        System.out.println(min);
    }
}

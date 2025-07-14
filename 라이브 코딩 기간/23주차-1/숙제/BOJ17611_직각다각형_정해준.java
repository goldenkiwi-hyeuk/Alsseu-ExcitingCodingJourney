import java.io.*;
import java.util.*;

public class BOJ17611_직각다각형_정해준 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        // 수평의 최대 겹치는 수를 구함
        int horizonMax = solve(points, n, 0); // 수평
        int verticalMax = solve(points, n, 1);   // 수직

        System.out.println(Math.max(horizonMax, verticalMax));
    }

    private static int solve(int[][] points, int n, int start) {
        int  mid = 500000;
        int  sum = mid * 2 + 1;

        int[] line = new int[sum];

        for (int cur = start; cur < n; cur += 2) {
            int next = (cur + 1) % n;

            // 변화하는 좌표를 찾기 위한 idx
            int changeIdx = 0;
            if (points[cur][0] == points[next][0]) {
                // x좌표가 같으면 y좌표가 변화
                changeIdx = 1;
            }

            // 변화하는 좌표의 최솟값과 최댓값
            int minVal = Math.min(points[cur][changeIdx], points[next][changeIdx]);
            int maxVal = Math.max(points[cur][changeIdx], points[next][changeIdx]);

            // 시작점에서 +1, 끝점에서 -1
            line[minVal + mid] += 1;
            line[maxVal + mid] -= 1;
        }

        // 차분 배열을 누적합으로 변환
        int maxOver = 0;
        int currentSum = 0;
        for (int i = 0; i < sum; i++) {
            currentSum += line[i];
            maxOver = Math.max(maxOver, currentSum);
        }

        return maxOver;
    }
}
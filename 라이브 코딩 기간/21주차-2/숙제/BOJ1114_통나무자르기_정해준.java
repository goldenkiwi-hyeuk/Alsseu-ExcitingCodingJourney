import java.util.Arrays;
import java.util.Scanner;

public class BOJ1114_통나무자르기_정해준 {
    static int L, K, C;
    static int[] pos;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        K = sc.nextInt();
        C = sc.nextInt();
        pos = new int[K+2];
        for (int i = 1; i <= K; i++) {
            pos[i] = sc.nextInt();
        }
        pos[0] = 0;
        pos[K + 1] = L;
        Arrays.sort(pos);

        int left = 1;
        int right = L;
        int answerLength = L;
        int answerFirst = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            int firstCut = check(mid);

            if (firstCut != -1) {
                answerLength = mid;
                answerFirst = firstCut;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answerLength + " " + answerFirst);
    }

    static int check(int maxLen) {
        // 각 가능한 첫 번째 자르기 위치에 대해 시도
        for (int firstCut = 1; firstCut < pos.length - 1; firstCut++) {
            if (pos[firstCut] > maxLen) {
                break;
            }

            if (canCut(maxLen, firstCut)) {
                return pos[firstCut];
            }
        }
        return -1;
    }

    static boolean canCut(int maxLen, int firstCutIdx) {
        int cutCount = 1;
        int start = firstCutIdx;

        // 첫 번째 조각이 maxLen 이하인지 확인
        if (pos[firstCutIdx] - pos[0] > maxLen) {
            return false;
        }

        // 나머지 부분을 그리디
        for (int i = start + 1; i < pos.length; i++) {
            if (pos[i] - pos[start] > maxLen) {

                if (i - 1 == start) {
                    return false;
                }
                cutCount++;
                start = i - 1;
                i--; // 현재 위치에서 다시 확인
            }
        }

        return cutCount <= C;
    }
}
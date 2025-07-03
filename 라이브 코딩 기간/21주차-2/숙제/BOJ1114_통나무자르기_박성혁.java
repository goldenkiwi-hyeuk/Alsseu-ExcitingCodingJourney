import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 기본아이다어 : 이분탐색
    static int L, K, C, firstCut;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        firstCut = -1;
        arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        // 가장 큰 통나무의 길이를 이분탐색으로 찾아낼 계획
        int beforeMid = -1;
        int ansfirstCnt = -1;
        int left = 1;
        int right = L;
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean flag = CanCut(mid);
            if (flag) {
                right = mid - 1;
                beforeMid = mid;
                ansfirstCnt = firstCut;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(beforeMid + " " + ansfirstCnt);
    }

    private static boolean CanCut(int size) {
        // 마지막으로 자른지점 처음에는 통나무의 길이인 L
        int lastcut = L;
        int cutCnt = 0;
        for (int i = arr.length-1; i >= 0; i--) {
            if (lastcut - arr[i] > size) {
                // 너무 멀다면 이전 위치에서 잘라야 한다.
                // 이전 위치에서 자르려면 당연히 i != arr.length-1 여야 한다.
                if (cutCnt >= C || i == arr.length-1) {
                    return false;
                } else {
                    cutCnt++;
                    lastcut = arr[i+1];
                    if (cutCnt == C){
                        firstCut = lastcut;
                    }
                    ++i;
                }
            }
        }

        // 마지막 조각이 size보다 크다면?
        if (lastcut > size){
            // 이미 자르기 기회를 다썼다면 불가능
            if (cutCnt >= C){
                return false;
            }
            // 가장 작은 위치에서 잘라도 사이즈보다 크다면 불가능
            if (arr[0]>size){
                return false;
            }
            ++cutCnt;
            firstCut = arr[0];
        }

        // 마지막 조각이 size보다 작거나 같은데 자른횟수가 덜하다면?
        if (cutCnt < C){
            firstCut = arr[0];
        }
        return true;
    }
}

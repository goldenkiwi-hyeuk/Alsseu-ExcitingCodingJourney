import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본 아이디어
    // 앞부터 탐색
    // 최소 : 1 최대 : 자신의 인덱스 를 범위로 하여 이분탐색을 실시하여 증가하는 부분수열의 최대 길이 mid를 찾는다.
    // 가능한 최댓수를 찾았는데 그 Map이 비어있다면 탐색한 숫자(arr[i])를 저장
    // 비어있지않다면 기존 숫자map.get(mid)와 탐색한 숫자(arr[i])를 비교하여 작은수를 저장.
    // 결과적으로 Map에는 각 최대 길이 부분증가수열의 길이에 해당하는 가장 작은 숫자가 저장된다.
    // Example
    // 6
    // 10 20 10 30 20 50
    // {1=10, 2=20, 3=30, 4=50}
    // 그렇다면 Map의 key중 최대값이 원하는 정답!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            int left = 1;
            int right = 1 + i;
            int mid = -1;
            while (left <= right) {
                mid = (left + right) / 2;
                if (map.containsKey(mid) && map.get(mid) < arr[i]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (map.containsKey(left)) {
                map.put(left, Math.min(map.get(left), arr[i]));
            } else {
                map.put(left, arr[i]);
            }
            if (left > max) {
                max = left;
            }
        }
        // System.out.println(map.toString());
        System.out.println(max);
    }
}

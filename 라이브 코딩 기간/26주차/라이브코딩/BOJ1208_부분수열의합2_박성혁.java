import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    // 기본아이디어 : 중간에서 만나기 + 이분 탐색
    static long N, S;
    // 두개의 부분수열 집합을 생성
    static List<Long> list1, list2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        S = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        // 절반씩 나눠 담으면서 동시에 부분수열을 전부 구해준다.
        list1 = new ArrayList<>();
        list1.add(0L); // 공집합을 미리 넣어주기
        for (int i = 0; i < (int) (N / 2); i++) {
            long num = Long.parseLong(st.nextToken());
            int size = list1.size();
            for (int j = 0; j < size; j++) {
                list1.add(list1.get(j) + num);
            }
        }
        list2 = new ArrayList<>();
        list2.add(0L); // 공집합을 미리 넣어주기
        for (int i = (int) (N / 2); i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            int size = list2.size();
            for (int j = 0; j < size; j++) {
                list2.add(list2.get(j) + num);
            }
        }

        Collections.sort(list2); // 2번째 리스트만 정렬 <= 이분탐색을 위해서
        long ans = 0;

        for (long num : list1) {
            // list1을 돌면서 타겟을 구하고 upperBound - lowerBound의 값을 정답에 더한다
            long target = S - num;
            int idx1 = upperBound(target);
            int idx2 = lowerBound(target);
            ans += idx1 - idx2;
        }

        // 목표가 0이라면 두개의 공집합이 더해지는 경우가 존재하므로 해당 경우는 제거한다.
        if (S == 0) {
            --ans;
        }
        System.out.println(ans);
    }

    private static int upperBound(long target) {
        int left = 0;
        int right = list2.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list2.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static int lowerBound(long target) {
        int left = 0;
        int right = list2.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list2.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 기본아이디어 : 이분탐색
    static int N, M, K;
    static int[] cardList;
    static boolean[] cardIsExist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cardList = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            cardList[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cardList);
        cardIsExist = new boolean[M];
        int[] cardList2 = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            cardList2[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int idx = binarySearch(cardList2[i]);
            while(cardIsExist[idx]){
                ++idx;
            }
            cardIsExist[idx] = true;
            sb.append(cardList[idx]).append("\n");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int target) {
        int left = 0;
        int right = M;
        while (left < right) {
            int mid = (left + right) / 2;
            if (target < cardList[mid]) {
                    right = mid;
            } else if (target >= cardList[mid]) {
                    left = mid + 1;
            }
        }
        return left;
    }
}

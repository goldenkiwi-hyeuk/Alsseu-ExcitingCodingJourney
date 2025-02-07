import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        long left = 0;
        long right = Math.min((long) N*N, 1000000000L);
        boolean isok = false;
        long mid = -1; // 이분 탐색할 수
        long beforemid = -1; // 이전 가능했던 수를 저장
        while (left <= right) {
            isok = false;
            mid = (left + right) / 2;
            long cnt = 0;
            for (long i = 1; i <= (long) N; ++i) {
                cnt += Math.min(mid/i,(long) N); // N과 mid/i중 작은수 선택
                if (cnt >= K) {
                    isok = true;
                    break;
                }
            }
//            System.out.println("left : " + left + ",right : " + right + ",mid : " + mid + ", beforemid : " + beforemid);
//            System.out.println("isok = " + isok + ", cnt : " + cnt);
            if (isok) {
                beforemid = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (isok) {
            System.out.println(mid);
        } else {
            System.out.println(beforemid);
        }
    }
}

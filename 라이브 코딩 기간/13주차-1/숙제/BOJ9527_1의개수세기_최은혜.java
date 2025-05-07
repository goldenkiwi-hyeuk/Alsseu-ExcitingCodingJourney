import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // n까지 이진수로 나타냈을 때 등장한 1의 개수 누적합 계산
    static long countOnes(long n) {
        long count = 0; // 최종적으로 반환할 1의 총합
        long bit = 1; // 현재 보고 있는 자리
        for (int i = 0; i < 55; i++) {
            long block = n / (bit << 1);              // 2^(i+1) 단위 블록 개수
            count += block * bit;                     // 블록 내 1의 개수
            long remain = n % (bit << 1);             // 남은 부분
            count += Math.max(0, remain - bit + 1);   // 남은 부분 중 추가로 1이 나오는 개수
            // remain - bit + 1 : 1이 나오는 영역과 겹치는 길이 계산
            bit <<= 1;                                // 다음 비트 자리로 이동
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long result = countOnes(B) - countOnes(A - 1);
        System.out.println(result);
    }
}

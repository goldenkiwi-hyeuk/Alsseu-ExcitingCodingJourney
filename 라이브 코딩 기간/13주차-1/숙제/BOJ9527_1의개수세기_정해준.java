import java.util.Scanner;

public class BOJ9527_1의개수세기_정해준 { // 비트마스킹, 누적합
    static long sum[] = new long[55]; // 각 자리수에 따른 갯수를 저장
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        sum[0] = 1;
        for(int i = 1; i < sum.length; i++) {
            sum[i] = sum[i-1] * 2 + (1L << i); // sum[i-1] * 2 + 2 ^ i
        }
    System.out.println(getCount(B) - getCount(A -1)); // 0 ~ B - 0 ~ A- 1 -> A ~ B
    }

    public static long getCount(long x) {
        long res = x & 1;
        for(int i = 54; i > 0; i--) {
            if((x & (1L << i)) > 0L) { // 왼쪽부터 비트가 1이지 확인
                // 이전 자리수의 갯수 + 왼쪽이 1이기 때문에 그 숫자가 되기 까지 몇개가 있는지 확인 후 +1
                // x 가 11일 때 1 0 1 1, i 가 3일 때 1 << 3 -> 1 0 0 0 일 경우
                // 1000 ~ 1011까지 총 몇개의 수가 존재하는지 확인 후 더하기
                res += sum[i-1] + (x - (1L << i) + 1);

                x -= (1L << i);
            }
        }
        return res;
    }
}

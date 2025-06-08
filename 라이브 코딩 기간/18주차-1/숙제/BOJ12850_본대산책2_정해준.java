import java.util.Scanner;

public class BOJ12850_본대산책2_정해준 {
    static long MOD = 1_000_000_007;
    // 각 위치에서의 이동 여부
    static long[][] v = {
            { 0, 1, 0, 1, 0, 0, 0, 0 },
            { 1, 0, 1, 1, 0, 0, 0, 0 },
            { 0, 1, 0, 1, 1, 1, 0, 0 },
            { 1, 1, 1, 0, 0, 1, 0, 0 },
            { 0, 0, 1, 0, 0, 1, 1, 0 },
            { 0, 0, 1, 1, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 1, 0, 0, 1 },
            { 0, 0, 0, 0, 0, 1, 1, 0 }
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[][] res = devide(N);

        System.out.println(res[0][0]);
    }

    public static long[][] devide(int n) {
        if(n == 1)
            return v;

        long[][] tmp = devide( n / 2);
        tmp = multiply(tmp, tmp);
        if(n % 2 == 1) {
            tmp = multiply(tmp, v);
        }

        return tmp;
    }

    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] tmp = new long[8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                for(int k = 0; k < 8; k++) {
                    tmp[i][j] = (tmp[i][j] + a[i][k] * b[k][j]) % MOD; // 행렬곱
                }
            }
        }

        return tmp;
    }
}

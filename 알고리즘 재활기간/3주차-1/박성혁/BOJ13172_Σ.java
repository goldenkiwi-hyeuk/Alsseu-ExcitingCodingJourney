import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int Mod = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        long sum = 0;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            long num = ((s % Mod) * pow(n, Mod - 2)) % Mod;
            sum = (sum + num) % Mod;
        }
        System.out.println(sum);
    }

    private static int getGcd(int n, int s) {
        int R = Math.max(n,s)%Math.min(n,s);
        if (R==0){
            return Math.min(n,s);
        } else {
            return getGcd(Math.min(n,s),R);
        }
    }

    private static long pow(int num, int idx) {
        if (idx == 1) {
            return num % Mod;
        }
        if (idx % 2 == 0) {
            long temp = pow(num, idx / 2);
            return (temp % Mod * temp % Mod) % Mod;
        } else {
            long temp = pow(num, idx / 2);
            return ((temp % Mod * temp % Mod) % Mod * (num % Mod)) % Mod;
        }
    }
}


// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class BOJ1493_박스채우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        int W = sc.nextInt();
        int H = sc.nextInt();

        int N = sc.nextInt();

        int box[] = new int[N];
        for(int i = 0; i < N; i++) {
            int s = sc.nextInt();
            int n = sc.nextInt();
            box[s] = n; // 박스 크기와 갯수를 저장 
        }

        long prev = 0;
        long answer = 0;

        for(int i = N - 1; i >= 0; i--) {
            prev <<= 3;

            long b = (long) (L >> i) * (H >> i) * (W >> i) - prev;
            long newb = Math.min((long) box[i], b);
            // System.out.println(b);
            prev += newb;
            answer += newb;
        }

        answer = prev == (long) L * H * W ? answer : -1;
        System.out.println(answer);
    }
}
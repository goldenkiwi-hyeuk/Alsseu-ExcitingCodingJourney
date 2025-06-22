import java.util.Scanner;
import java.util.Stack;

public class BOJ6549_히스토그램에서가장큰직사각형_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int N = sc.nextInt();
            if( N == 0)
                return;

            long[] arr = new long[N + 1];
            for(int i = 0; i < N; i++) {
                arr[i] = sc.nextLong();
            }

            arr[N] = 0;

            Stack<Integer> stack = new Stack<>();
            long max = 0;

            for(int i = 0; i <= N; i++) {
                while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                    int idx = stack.pop();
                    long h = arr[idx];
                    long w = stack.isEmpty() ? i : (i - stack.peek() - 1);
                    max = Math.max(max, h * w);
                }
                stack.push(i);
            }
            System.out.println(max);
        }
    }
}
